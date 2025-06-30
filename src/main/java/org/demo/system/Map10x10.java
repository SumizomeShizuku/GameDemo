package org.demo.system;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.demo.factory.Player;
import org.demo.util.SimpleLogger;

/**
 * MazeGrowingTree10x10 - 最终版
 * <p>
 * 特点汇总:
 * </p>
 * <ul>
 * <li>Growing-Tree( DFS × Prim 混合 )——分支丰富</li>
 * <li>3 × 3 稠密度限制: 周围 8 格房间数 &gt; 5 拒绝连通</li>
 * <li>禁止出现 2 × 3 / 3 × 2 及更大<strong>实心矩形</strong></li>
 * <li>起点 <b>A</b> 随机, 终点 <b>B</b> = 与 A 曼哈顿距离最远的格子, 但再加:</li>
 * <li>
 * <b>B 的连通度修正</b><br>
 * 若候选 B 与其他房间连通度 ≥ 2, 则尝试在 B 附近“打掉一面墙”<br>
 * 新位置满足连通度 = 1 后才真正设为 B。<br>
 * 若 B 位于边缘且无法修正, 则<strong>整张地图重生成</strong>。
 * </li>
 * </ul>
 *
 * <p>
 * 默认房间数区间: 8~40, 可由命令行参数覆盖。
 * </p>
 */
public class Map10x10 {

    /* ───────────── 外部传参区 ───────────── */
    private final Player player;
    private final RoomEventCheck eventCheck;

    /* ───────────── 参数调节区 ───────────── */
    private static final int SIZE = 10; // 固定 10×10
    private static final int DENSITY_LIMIT = 5; // 3×3 内 >5 拒绝
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final double STRAIGHT_BIAS = 0.4; // 直行概率
    private static final double LAST_PROB = 0.55; // DFS vs. Prim 比

    /* ───────────── 字段 ───────────── */
    private final Room[][] grid = new Room[SIZE][SIZE]; // "#",".","A","B"
    private final boolean[][] vis = new boolean[SIZE][SIZE];
    private final List<int[]> rooms = new ArrayList<>();
    private final Random rnd = new Random();
    private final int minRooms, maxRooms; // 用于多次重生

    /* ───────────── 构造 ───────────── */
    public Map10x10(int minRooms, int maxRooms, Player player) {
        this.player = player;
        this.eventCheck = new RoomEventCheck(this);

        if (minRooms < 2 || maxRooms > SIZE * SIZE) {
            System.out.println("房间下限与上限超过范围, 已设置为默认值: 最小房间数[8], 最大房间数[40]");
            minRooms = 8;
            maxRooms = 40;
        }
        if (minRooms > maxRooms) {
            this.minRooms = maxRooms;
            this.maxRooms = minRooms;
        } else {
            this.minRooms = minRooms;
            this.maxRooms = maxRooms;
        }
        generateUntilValid(); // 反复生成直至满足 B 规则
    }

    /*
     * ======================================================================
     * 顶层: 循环生成直至 B 修正成功
     * ======================================================================
     */
    private void generateUntilValid() {
        int attempts = 0;
        while (true) {
            attempts++;
            clear();
            generateMaze(rnd.nextInt(maxRooms - minRooms + 1) + minRooms);
            if (placeStartAndEnd()) {
                break; // 成功

            }
            // 极端罕见: 放宽限制
            if (attempts > 80) {
                for (Room[] row : grid) {
                    for (Room room : row) {
                        room.setStart(false);
                    }
                }

                if (rooms.size() < 2) {
                    continue;
                }

                // 2. 随机打乱房间列表
                Collections.shuffle(rooms, rnd);

                // 3. 取前两个不同的房间坐标
                int[] a = rooms.get(0);
                int[] b = rooms.get(1);

                // 4. 设定为起点终点
                grid[a[1]][a[0]].setStart(true); // 注意[y][x]顺序
                grid[b[1]][b[0]].setEnd(true);

                break;
            }
        }
        // --- 追加判断 ---
        boolean foundEnd = false;
        for (Room[] row : grid) {
            for (Room room : row) {
                if (room.isEnd()) {
                    foundEnd = true;
                    break;
                }
            }
            if (foundEnd) {
                break;
            }
        }
        if (!foundEnd) {
            // 遍历rooms, 随机选择一个作为终点
            if (!rooms.isEmpty()) {
                while (true) {
                    int[] b = rooms.get(rnd.nextInt(rooms.size()));
                    if (!grid[b[1]][b[0]].isStart()) {
                        grid[b[1]][b[0]].setEnd(true);
                        break;
                    }
                }

            }
        }

        // 在成功放置起点(A)和终点(B)之后，设置宝箱房间：
        List<int[]> candidates = new ArrayList<>(rooms);
        // 移除起点和终点房间坐标，仅保留可选为宝箱房的房间
        candidates.removeIf(coord -> grid[coord[1]][coord[0]].isStart() || grid[coord[1]][coord[0]].isEnd());

        // 设置宝箱房数量
        int starCount = 4;
        // 若数量大于0个
        if (starCount > 0) {
            // 如果空房间数 > 宝箱房数量
            // 房间总数太少会导致无法生成宝箱房
            if (candidates.size() >= starCount) {
                // 将候选房间随机排序
                Collections.shuffle(candidates, rnd);
                // 循环, 直至宝箱房数量等于设定值
                for (int i = 0; i < starCount; i++) {
                    int[] starPos = candidates.get(i);
                    grid[starPos[1]][starPos[0]].setStar(true);
                }
            }
        }
    }

    /**
     * 重置网格与状态
     */
    private void clear() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                Room cell = grid[y][x];
                if (cell == null) { // 还没实例化？
                    cell = new Room(y, x); // 立刻 new 出来
                    grid[y][x] = cell;
                }
                cell.reset(); // 然后重置字段
            }
        }
        for (boolean[] row : vis) {
            Arrays.fill(row, false);
        }
        rooms.clear();
    }

    /*
     * ======================================================================
     * Growing-Tree 迷宫主体
     * ======================================================================
     */
    private void generateMaze(int target) {
        List<int[]> active = new ArrayList<>();
        int sx = rnd.nextInt(SIZE), sy = rnd.nextInt(SIZE);
        carve(sx, sy, active);

        while (!active.isEmpty() && rooms.size() < target) {

            // 选活跃单元( DFS 或随机 )
            int idx = (rnd.nextDouble() < LAST_PROB) ? active.size() - 1
                    : rnd.nextInt(active.size());
            int[] cur = active.get(idx);
            List<int[]> cand = collectCandidates(cur);

            if (cand.isEmpty()) {
                active.remove(idx);
                continue;
            }

            int[] next = chooseNext(cand, cur);
            carve(next[0], next[1], active);
        }
    }

    /**
     * 收集 (x,y) 四邻可雕刻房间
     */
    private List<int[]> collectCandidates(int[] cur) {
        int x = cur[0], y = cur[1];
        List<int[]> list = new ArrayList<>(4);
        for (int[] d : DIRS) {
            int nx = x + d[0], ny = y + d[1];
            if (!inside(nx, ny) || vis[ny][nx]) {
                continue;
            }
            if (countAround(nx, ny) > DENSITY_LIMIT) {
                continue;
            }
            if (formsProhibitedRect(nx, ny)) {
                continue;
            }
            list.add(new int[]{nx, ny, d[0], d[1]}); // 附带方向
        }
        return list;
    }

    /**
     * 带直行偏置选下一格
     */
    private int[] chooseNext(List<int[]> cand, int[] cur) {
        if (cand.size() == 1) {
            return cand.get(0);
        }
        if (rooms.size() >= 2 && rnd.nextDouble() < STRAIGHT_BIAS) {
            int[] prev = rooms.get(rooms.size() - 2);
            int dx = cur[0] - prev[0], dy = cur[1] - prev[1];
            for (int[] c : cand) {
                if (c[2] == dx && c[3] == dy) {
                    return c;
                }
            }
        }
        return cand.get(rnd.nextInt(cand.size()));
    }

    /**
     * 将 (x,y) 铺为房间并加入活跃/房间列表
     */
    private void carve(int x, int y, List<int[]> active) {
        vis[y][x] = true;
        grid[y][x].setRoom(true);
        rooms.add(new int[]{x, y});
        active.add(new int[]{x, y});
    }

    /*
     * ======================================================================
     * 起点 A & 终点 B (返回 true = 生成成功)
     * ======================================================================
     */
    private boolean placeStartAndEnd() {
        // ---- A ----
        int[] a = rooms.get(rnd.nextInt(rooms.size()));
        int ax = a[0], ay = a[1];
        grid[ay][ax].setStart(true);

        // ---- BFS 记录距离并收集最远房间 ----
        int[][] dist = new int[SIZE][SIZE];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(a);
        dist[ay][ax] = 0;

        int maxd = 0;
        List<int[]> farthest = new ArrayList<>();
        while (!q.isEmpty()) {
            int[] c = q.poll();
            int x = c[0], y = c[1];
            for (int[] d : DIRS) {
                int nx = x + d[0], ny = y + d[1];
                if (!inside(nx, ny) || grid[ny][nx].isWall() || dist[ny][nx] != -1) {
                    continue;
                }
                dist[ny][nx] = dist[y][x] + 1;
                if (dist[ny][nx] > maxd) {
                    maxd = dist[ny][nx];
                    farthest.clear();
                }
                if (dist[ny][nx] == maxd) {
                    farthest.add(new int[]{nx, ny});
                }
                q.offer(new int[]{nx, ny});
            }
        }
        Collections.shuffle(farthest, rnd);

        // ---- 依次尝试设置 B ----
        for (int[] cand : farthest) {
            if (trySetAsB(cand[0], cand[1])) {
                return true;
            }
        }
        // 未找到合规 B: 重生成
        return false;
    }

    /**
     * 尝试将 (x,y) 设为 B；若连接 ≥2, 则尝试在邻墙开新格当 B。
     *
     * @return 成功放置 B = true
     */
    private boolean trySetAsB(int x, int y) {
        int deg = adjacentRooms(x, y);
        if (deg <= 1) {
            grid[y][x].setEnd(true);
            return true;
        }

        // 尝试把邻墙变为新 B
        List<int[]> walls = new ArrayList<>(4);
        for (int[] d : DIRS) {
            int nx = x + d[0], ny = y + d[1];
            if (inside(nx, ny) && grid[ny][nx].isWall()) {
                walls.add(new int[]{nx, ny});
            }
        }
        Collections.shuffle(walls, rnd);
        for (int[] w : walls) {
            int wx = w[0], wy = w[1];
            if (countAround(wx, wy) > DENSITY_LIMIT) {
                continue;
            }
            if (formsProhibitedRect(wx, wy)) {
                continue;
            }
            if (adjacentRooms(wx, wy) == 1) { // 仅与原 cand 相连
                vis[wy][wx] = true;
                grid[wy][wx].setEnd(true);
                rooms.add(new int[]{wx, wy});
                return true;
            }
        }
        // cand 在边缘且无法修正 → 触发重生
        return !(x == 0 || y == 0 || x == SIZE - 1 || y == SIZE - 1);
    }

    /*
     * ======================================================================
     * 约束: 2×3 / 3×2 实心矩形禁止
     * ======================================================================
     */
    private boolean formsProhibitedRect(int x, int y) {
        return rectFull(x, y, 3, 2) || rectFull(x, y, 2, 3);
    }

    private boolean rectFull(int x, int y, int w, int h) {
        for (int dy = 0; dy < h; dy++) {
            for (int dx = 0; dx < w; dx++) {
                int sx = x - dx, sy = y - dy;
                if (!inside(sx, sy) || !inside(sx + w - 1, sy + h - 1)) {
                    continue;
                }
                boolean full = true;
                for (int yy = 0; yy < h && full; yy++) {
                    for (int xx = 0; xx < w; xx++) {
                        int tx = sx + xx, ty = sy + yy;
                        if (!(vis[ty][tx] || (tx == x && ty == y))) {
                            full = false;
                            break;
                        }
                    }
                }
                if (full) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * ======================================================================
     * 工具
     * ======================================================================
     */
    private static boolean inside(int x, int y) {
        return x >= 0 && y >= 0 && x < SIZE && y < SIZE;
    }

    private int countAround(int x, int y) {
        int c = 0;
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                int nx = x + dx, ny = y + dy;
                if (inside(nx, ny) && vis[ny][nx]) {
                    c++;
                }
            }
        }
        return c;
    }

    private int adjacentRooms(int x, int y) {
        int c = 0;
        for (int[] d : DIRS) {
            int nx = x + d[0], ny = y + d[1];
            if (inside(nx, ny) && vis[ny][nx]) {
                c++;
            }
        }
        return c;
    }

    /**
     * 计算两个点之间的曼哈顿距离
     */
    public static int getDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    /**
     * 取得整个地图
     *
     * @return
     */
    public Room[][] getGrid() {
        return grid;
    }

    /**
     * 查找玩家所在房间 Room的hasPlayer为true时
     *
     * @return 房间坐标
     */
    public Point whereIam() {
        for (Room[] row : grid) {
            for (Room room : row) {
                if (room.isHere()) {
                    return room.whereIam();
                }
            }
        }
        return null;
    }

    /**
     * 查找地图中的起点房间
     *
     * @return 房间坐标
     */
    public Point getStartRoom() {
        for (Room[] row : grid) {
            for (Room room : row) {
                if (room.isStart()) {
                    return room.whereIam();
                }
            }
        }
        return null;
    }

    /**
     * 打开全图视野
     */
    public void visibleAllRoom() {
        for (Room[] row : grid) {
            for (Room room : row) {
                if (!room.isVisible()) {
                    room.setVisible(true);
                }
            }
        }
        SimpleLogger.log.info("你能看到全世界!");
    }

    /**
     * 查看特定房间信息
     *
     * @param y
     * @param x
     * @return
     */
    public Room whichRoom(int y, int x) {
        Room room = grid[y][x];
        return room;
    }

    /**
     * 获取玩家周围4格视野
     */
    public void checkAroundPlayer() {
        Point p = whereIam(); // 获取玩家位置
        if (p == null) {
            SimpleLogger.log.info("找不到玩家");
            return;
        }
        for (int[] d : DIRS) {
            int ny = p.x + d[0];
            int nx = p.y + d[1];
            if (inside(ny, nx)) { // 推荐用你已有的inside方法！
                Room neighbor = grid[ny][nx]; // 注意[y][x]
                if (!neighbor.isVisible()) {
                    neighbor.setVisible(true);
                    roomEnemysSet(neighbor);
                }
            }
        }
    }

    /**
     * 玩家移动
     *
     * @param wasd 移动方向, w:上 | a:左 | s:下 | d:右
     */
    public void playerMove(String wasd) {
        if (wasd == null || wasd.length() == 0) {
            return;
        }
        char dir = Character.toLowerCase(wasd.charAt(0));
        int[] offset;
        switch (dir) {
            case 'w' -> {
                offset = new int[]{-1, 0};
                SimpleLogger.log.info("玩家尝试向上移动");
            }
            case 'a' -> {
                offset = new int[]{0, -1};
                SimpleLogger.log.info("玩家尝试向左移动");
            }
            case 's' -> {
                offset = new int[]{1, 0};
                SimpleLogger.log.info("玩家尝试向下移动");
            }
            case 'd' -> {
                offset = new int[]{0, 1};
                SimpleLogger.log.info("玩家尝试向右移动");
            }
            default -> {
                SimpleLogger.log.info("输入方向无效, 请输入w/a/s/d");
                return;
            }
        }
        Point p = whereIam();
        if (p == null) {
            return;
        }
        int ny = p.x + offset[0];
        int nx = p.y + offset[1];

        boolean battleFlg;

        if (inside(ny, nx) && grid[ny][nx].isRoom()) {
            grid[p.x][p.y].setPlayer(false);
            grid[ny][nx].setPlayer(true);

            if (!grid[ny][nx].isClear()) {

                if (player.isAlive()) {
                    battleFlg = eventCheck.checkBattle(player);
                    if (!battleFlg) {
                        grid[p.x][p.y].setPlayer(true);
                        grid[ny][nx].setPlayer(false);
                        checkAroundPlayer();
                        SimpleLogger.log.info("玩家战败, 回到原来房间");
                    }

                } else {
                    grid[p.x][p.y].setPlayer(true);
                    grid[ny][nx].setPlayer(false);
                    SimpleLogger.log.info("玩家无法移动, 回到原来房间");
                }
            } else {
                checkAroundPlayer();
                SimpleLogger.log.info("安全的房间");
            }

        } else {
            SimpleLogger.log.info("无法移动");
        }
    }

    /**
     * 当玩家进入房间时, 在房间内生成一定数量的敌人 详细的敌人类型由RoomEventCheck()处理
     *
     * @param room 玩家所在房间
     */
    public void roomEnemysSet(Room room) {
        if (!room.isStar()) {
            if (room.getEnemyCount() == 0) {
                int count = new Random().nextInt(4) + 1; // 1~4
                // 设置敌人数量
                room.setEnemyCount(count);
                SimpleLogger.log.debug("房间内生成敌人数: " + count);
            }
        }

    }

    /*
     * ======================================================================
     * 打印
     * ======================================================================
     */
    /**
     * 打印迷宫( 四周包一圈不可打通的边界墙 )。
     *
     * @return 带边界的迷宫字符串
     */
    @Override
    public String toString() {
        checkAroundPlayer();
        StringBuilder sb = new StringBuilder();
        String ln = System.lineSeparator();

        // 顶部边界
        sb.append("▨".repeat(SIZE + 2)).append(ln);

        // 中间行: 左右各加一面墙
        for (Room[] row : grid) {
            sb.append("▨"); // 左边界
            for (Room room : row) {
                sb.append(room.toString());
            }

            sb.append("▨").append(ln); // 右边界
        }

        // 底部边界
        sb.append("▨".repeat(SIZE + 2)).append(ln);
        sb.append(ln).append("▣ : 玩家当前位置").append(ln);
        sb.append("◇ : 起点").append("  ").append("◆ : 终点");
        sb.append("  ").append("★ : 宝箱房").append(ln);
        sb.append("▢ : 空房间").append("  ").append("▨ : 墙壁").append(ln);
        return sb.toString();
    }

    /**
     * 高级字符画渲染: 每个房间 3 × 3, 外围仍用 #### 包围。
     */
    public String renderFancy() {
        final StringBuilder sb = new StringBuilder();
        final int cellW = 3;

        /* 1 打印顶部边界 */
        sb.append("#".repeat((SIZE * cellW) + 2)).append('\n');

        /* 2 每行房间 → 3 行字符 */
        for (int y = 0; y < SIZE; y++) {

            // ── 顶边: ⌜──⌝
            sb.append('#'); // 左边界
            for (int x = 0; x < SIZE; x++) {
                Room r = grid[y][x];
                if (r.isWall()) {
                    sb.append("###");
                } else {
                    sb.append(" ").append("─".repeat(cellW - 2)).append(" ");
                }
            }
            sb.append('#').append('\n');

            // ── 内容: │ A│
            sb.append('#');
            for (int x = 0; x < SIZE; x++) {
                Room r = grid[y][x];
                if (r.isWall()) {
                    sb.append("###");
                } else {
                    char mark = ' ';
                    if (r.isStart()) {
                        mark = 'A';
                    } else if (r.isEnd()) {
                        mark = 'B';
                    }
                    if (r.isHere()) {
                        mark = '◉';
                    }
                    sb.append("│").append(mark).append("│");
                }
            }
            sb.append('#').append('\n');

            // ── 底边: ⌞──⌟
            sb.append('#');
            for (int x = 0; x < SIZE; x++) {
                Room r = grid[y][x];
                if (r.isWall()) {
                    sb.append("###");
                } else {
                    sb.append(" ").append("─".repeat(cellW - 2)).append(" ");
                }
            }
            sb.append('#').append('\n');
        }

        /* 3 底部边界 */
        sb.append("#".repeat((SIZE * cellW) + 2)).append('\n');
        return sb.toString();
    }
}
