package org.demo.system;

import java.awt.Point;

/**
 * 房间（格子）数据类。
 *
 * <p>
 * 只保存“是什么”的静态信息，不包含迷宫算法。
 * </p>
 */
public class Room {

    /* ── 位置坐标 ── */
    private final int x;
    private final int y;

    /* ── 状态标记 ── */
    private boolean room = false; // 是否已雕刻为房间
    private boolean start = false; // 是否起点 A
    private boolean end = false; // 是否终点 B
    private int enemyCount = 0; // 敌人数
    private boolean hasPlayer = false; // 是否存在玩家
    private boolean isVisible = false;
    private boolean isClear = false;

    /**
     * @param x 格子横坐标
     * @param y 格子纵坐标
     */
    public Room(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isRoom() {
        return room;
    }

    public void setRoom(boolean room) {
        this.room = room;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
        this.room = start;
        this.hasPlayer = start;
        this.isVisible = start;
        this.isClear = start;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
        this.room = end;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public void setPlayer(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isClear() {
        return isClear;
    }

    public void setClear(boolean isClear) {
        this.isClear = isClear;
    }

    /**
     * 该房间位置
     *
     * @return 该房间位置
     */
    public Point whereIam() {
        return new Point(this.y, this.x);
    }

    /**
     * 判断玩家是否处于该房间
     *
     * @return 若玩家存在 true
     */
    public boolean isHere() {
        return hasPlayer;
    }

    /**
     * 判定此格子当前是否为墙。
     *
     * @return {@code true} 表示墙体（不可通行）；{@code false} 表示已开凿房间
     */
    public boolean isWall() {
        return !room;
    }

    public void reset() {
        this.room = false; // 不是可通行房间
        this.start = false; // 不是起点
        this.end = false; // 不是终点
        this.enemyCount = 0; // 敌人数清零
        this.hasPlayer = false; // 重置玩家位置
        this.isVisible = false;
        this.isClear = false;
    }

    /**
     * 返回渲染用字符：'#' | '.' | 'A' | 'B'
     */
    @Override
    public String toString() {
        if (isVisible) {
            if (hasPlayer) {
                return "▣";
            }
            if (!room) {
                return "▨";
            }
            if (start) {
                return "◇";
            }
            if (end) {
                return "◆";
            }
            return "▢";
        } else {
            return "▨";
        }

    }

}
