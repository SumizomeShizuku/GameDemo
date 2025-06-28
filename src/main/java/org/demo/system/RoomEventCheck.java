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
        boolean battleFlg = false;

        // 2. 检查是否有敌人
        if (room.getEnemyCount() > 0) {
            // 这里可以进一步获得敌人类型或直接生成敌人
            // String enemyId = room.getEnemyId(); // 推荐让Room对象支持getEnemyId()
            EnemyModelDto enemyDto = EnemyRepository.getEnemyById("EN0001");

            // Enemy enemy = EnemyFactory.createEnemy(EnemyList.GOBLIN);
            if (enemyDto != null) {
                Enemy enemy = new Enemy(enemyDto);
                SimpleLogger.log.info("遇到敌人, 进入战斗！");
                battleFlg = BattleSystem.startBattle(player, enemy);
                // 3. 战斗后, 敌人数量-1, 或你有更详细的处理方式
                if (battleFlg) {
                    room.setEnemyCount(0);
                    room.setClear(battleFlg);
                }
            }
        }
        return battleFlg;
    }
}
