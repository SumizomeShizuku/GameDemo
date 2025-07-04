package org.demo.system;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import org.demo.dto.EnemyModelDto;
import org.demo.dto.ItemModelDto;
import org.demo.factory.Enemy;
import org.demo.factory.EnemyFactory;
import org.demo.factory.Player;
import org.demo.repository.EnemyRepository;
import org.demo.repository.ItemRepository;
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
    public boolean checkBattle(Player player, String area) {
        // 1. 获取玩家当前位置
        Point pos = map.whereIam();
        if (pos == null) {
            return false;
        }

        Room room = map.whichRoom(pos.x, pos.y);
        boolean battleFlg = true;

        // 2. 检查是否有敌人
        if (!room.isClear()) {
            // 这里可以进一步获得敌人类型或直接生成敌人
            // String enemyId = room.getEnemyId(); // 推荐让Room对象支持getEnemyId()

            // 计算玩家与起点的曼哈顿距离
            Point startPos = map.getStartRoom();
            Point playerPos = map.whereIam();
            int distance = Map10x10.getDistance(startPos, playerPos);

            EnemyModelDto enemyDto = EnemyRepository.getRandomEnemy(area, distance);
            SimpleLogger.log.info(enemyDto.toString());
            // Enemy enemy = EnemyFactory.createEnemy(EnemyList.GOBLIN);

            Enemy enemy = EnemyFactory.enemyFactory(enemyDto, player);
            SimpleLogger.log.info(enemy.toString());
            SimpleLogger.log.info(player.getFirstName() + " 遭遇了敌人 " + enemy.getName() + ", 进入战斗！");
            battleFlg = BattleSystem.startBattle(player, enemy);
            // 3. 战斗后, 敌人数量-1, 或你有更详细的处理方式
            if (battleFlg) {
                room.setEnemyCount(0);
                room.setClear(battleFlg);
            }

        }
        //  else {
        //     battleFlg = true;
        // }
        return battleFlg;
    }

    public void eventsCheck(Player player) {
        // 为物品json文件添加道具池字段
        // 首先在items列表中添加特定道具池中所有物品
        // 之后该道具若为可堆叠物品，随机一个数量int
        List<ItemModelDto> items = ItemRepository.getEnemiesByArea("group1");
        Random random = new Random();
        int ran = random.nextInt(items.size());
        ItemModelDto item = items.get(ran);
        player.addItem(item, 1);
    }
}
