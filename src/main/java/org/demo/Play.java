package org.demo;

import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;
import org.demo.factory.EnemyFactory;
import org.demo.factory.PlayerFactory;
import org.demo.list.EnemyList;
import org.demo.system.BattleSystem;
import org.demo.util.SimpleLogger;

public class Play {

    public static void playerInit() {
        // 模拟玩家输入的种族和职业
        // 1:空尾族, 2:人族 3:林语族, 4:亡影族
        int ethnicityNum = 4;
        // 1:战士, 2:法师, 3:游侠, 4:圣职者
        int jobNum = 1;
        // 模拟玩家输入的名字
        String playerFirstName = "Anneliese";
        String playerLastName = "Wallis";

        PlayerModelDto player = PlayerFactory.createPlayer(playerFirstName, playerLastName, ethnicityNum, jobNum);
        SimpleLogger.log.info("选择的职业: " + player.getJob().getNameZh());
        SimpleLogger.log.info("选择的种族: " + player.getEthnicity().getEthnicityZh());

        Enemy enemy = EnemyFactory.createEnemy(EnemyList.GOBLIN);
        SimpleLogger.log.info(player.getFirstName() + " 遇到了一只敌人:  " + enemy);

        BattleSystem.startBattle(player, enemy);

        // player.setEquip(1);
        player.showInventory();
        player.removeItemById(2);

        player.removeStackableItemById(1, 2);
        SimpleLogger.log.info("36行道具删除前");
        player.showInventory();
        SimpleLogger.log.info("36行道具删除后");
        enemy = EnemyFactory.createEnemy(EnemyList.GOBLIN);
        SimpleLogger.log.info(player.getFirstName() + " 遇到了一只敌人:  " + enemy);

        BattleSystem.startBattle(player, enemy);

        player.setEquip(3);
        player.showInventory();
        // System.out.println(player.getBackpack().toString());
    }
}
