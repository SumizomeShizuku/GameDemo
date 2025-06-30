package org.demo.system;

import java.awt.Point;

import org.demo.dto.EnemyModelDto;
import org.demo.factory.Enemy;
import org.demo.factory.Player;
import org.demo.repository.EnemyRepository;
import org.demo.util.SimpleLogger;

public class RoomEventCheck {

    private final Map10x10 map;

    public RoomEventCheck(Map10x10 map) {
        this.map = map;
    }

    /**
     * 玩家每次移动后调用, 检测当前位置是否有敌人, 如果有则自动触发战斗。
     *
     * @param player 玩家对象
     */
    public boolean checkBattle(Player player) {
        // 1. 获取玩家当前位置
        Point pos = map.whereIam();
        if (pos == null) {
            return false;
        }

        Room room = map.whichRoom(pos.x, pos.y);
        boolean battleFlg = true;

        // 2. 检查是否有敌人
        if (room.getEnemyCount() > 0) {
            // 这里可以进一步获得敌人类型或直接生成敌人
            // String enemyId = room.getEnemyId(); // 推荐让Room对象支持getEnemyId()

            // 计算玩家与起点的曼哈顿距离
            Point startPos = map.getStartRoom();
            Point playerPos = map.whereIam();
            int distance = Map10x10.getDistance(startPos, playerPos);

            EnemyModelDto enemyDto = EnemyRepository.getRandomEnemy("森林", distance);
            // Enemy enemy = EnemyFactory.createEnemy(EnemyList.GOBLIN);
            if (enemyDto != null) {
                Enemy enemy = new Enemy(enemyDto);
                SimpleLogger.log.info(player.getFirstName() + " 遭遇了敌人 " + enemy.getName() + ", 进入战斗！");
                battleFlg = BattleSystem.startBattle(player, enemy);
                // 3. 战斗后, 敌人数量-1, 或你有更详细的处理方式
                if (battleFlg) {
                    room.setEnemyCount(0);
                    room.setClear(battleFlg);
                }
            }
        }
        //  else {
        //     battleFlg = true;
        // }
        return battleFlg;
    }

    /**
     * 计算两个点之间的曼哈顿距离
     */
    public static int getDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}
