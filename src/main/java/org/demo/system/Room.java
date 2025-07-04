package org.demo.system;

import java.awt.Point;

/**
 * 房间( 格子 )数据类。
 *
 * <p>
 * 只保存“是什么”的静态信息, 不包含迷宫算法。
 * </p>
 */
public class Room {

    /* ── 位置坐标 ── */
    private final int x;
    private final int y;

    /* ── 状态标记 ── */
    private boolean isRoom = false; // 是否已雕刻为房间
    private boolean isStart = false; // 是否起点 A
    private boolean isEnd = false; // 是否终点 B
    private int enemyCount = 0; // 敌人数
    private boolean hasPlayer = false; // 是否存在玩家
    private boolean isVisible = false; // 是否可见
    private boolean isClear = false; // 敌人是否被清理
    private boolean isStar = false; // 是否为宝箱房( 默认false )
    private boolean isEvent = false; // 是否包含事件
    private boolean isShop = false; // 是否为商店

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
        return isRoom;
    }

    public void setRoom(boolean room) {
        this.isRoom = room;
    }

    public boolean isStart() {
        return isStart;
    }

    // 若房间为起点, 则
    // 1.设置该格子被雕刻为房间
    // 2.设置该房间为起点
    // 3.设置玩家在此位置开始
    // 4.设置该房间在地图中可视
    // 5.设置该房间敌人被清空(后续不会刷新敌人)
    public void setStart(boolean start) {
        this.isRoom = start;
        this.isStart = start;
        this.hasPlayer = start;
        this.isVisible = start;
        this.isClear = start;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        this.isEnd = end;
        this.isRoom = end;
        this.isClear = end;
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

    public boolean isStar() {
        return isStar;
    }

    public void setStar(boolean isStar) {
        this.isStar = isStar;
        this.isRoom = isStar;
        this.isClear = isStar;
    }

    public boolean isEvent() {
        return isEvent;
    }

    public void setEvent(boolean isEvent) {
        this.isEvent = isEvent;
        this.isRoom = isEvent;
        this.isClear = isEvent;
    }

    public boolean isShop() {
        return isShop;
    }

    public void setShop(boolean isShop) {
        this.isShop = isShop;
        this.isRoom = isShop;
        this.isClear = isShop;
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
     * @return {@code true} 表示墙体( 不可通行 )；{@code false} 表示已开凿房间
     */
    public boolean isWall() {
        return !isRoom;
    }

    public void reset() {
        this.isRoom = false; // 不是可通行房间
        this.isStart = false; // 不是起点
        this.isEnd = false; // 不是终点
        this.enemyCount = 0; // 敌人数清零
        this.hasPlayer = false; // 重置玩家位置
        this.isVisible = false; // 重置房间可视性
        this.isClear = false; // 重置房间敌人为未清理
        this.isStar = false; // 重置房间为非宝箱房
        this.isEvent = false; // 重置房间为非事件
        this.isShop = false; // 重置房间为非商店
    }

    /**
     * 返回渲染用字符: '#' | '.' | 'A' | 'B'
     */
    @Override
    public String toString() {
        if (isVisible) {
            if (hasPlayer) {
                return "▣";
            }
            if (!isRoom) {
                return "▨";
            }
            if (isStart) {
                return "◇";
            }
            if (isEnd) {
                return "◆";
            }
            if (isStar) {
                return "★";
            }
            if (isShop) {
                return "⨝";
            }
            if (isEvent) {
                return "∀";
            }

            return "▢";
        } else {
            return "▨";
        }

    }

}
