package org.demo;

import org.demo.factory.Enemy;
import org.demo.factory.EnemyFactory;
import org.demo.factory.Player;
import org.demo.factory.PlayerFactory;
import org.demo.list.EnemyList;
import org.demo.system.BattleSystem;
import org.demo.util.LevelUpHandler;
import org.demo.util.SimpleLogger;

public class Play {

    public static void playerInit() {
        // 模拟玩家输入的种族和职业
        // 1:空尾族, 2:人族 3:林语族, 4:亡影族
        int ethnicityNum = 3;
        // 1:战士, 2:法师, 3:游侠, 4:圣职者
        int jobNum = 2;
        // 模拟玩家输入的名字
        String playerFirstName = "Anneliese";
        String playerLastName = "Wallis";

        Player player = PlayerFactory.createPlayer(playerFirstName, playerLastName, ethnicityNum, jobNum);
        SimpleLogger.log.info("选择的职业: " + player.getModel().getJob().getNameZh());
        SimpleLogger.log.info("选择的种族: " + player.getModel().getEthnicity().getEthnicityZh());
        LevelUpHandler.handleExpGain(player.getModel(), 40000);

        Enemy enemy = EnemyFactory.createEnemy(EnemyList.GOBLIN);
        SimpleLogger.log.info(player.getModel().getFirstName() + " 遇到了一只敌人:  " + enemy);

        BattleSystem.startBattle(player, enemy);

        player.setEquip("mainHand", 2);
        player.showInventory();
        // player.removeItemBySlot(2,1);

        // SimpleLogger.log.info("道具删除");
        player.showInventory();

        enemy = EnemyFactory.createEnemy(EnemyList.GOBLIN);
        SimpleLogger.log.info(player.getModel().getFirstName() + " 遇到了一只敌人:  " + enemy);

        BattleSystem.startBattle(player, enemy);

        player.setEquip("mainHand", 1);

        player.showInventory();
        // System.out.println(player.getBackpack().toString());
        player.showEquipment();
        player.putOffEquip("mainHand");
        SimpleLogger.log.info("--------------分割");
        player.showInventory();
        player.showEquipment();
        SimpleLogger.log.info("--------------分割-----------------");
        player.setEquip("accessory", 2);
        player.showInventory();
        player.showEquipment();
        player.setEquip("accessory4", 1);
        player.showInventory();
        player.showEquipment();
    }
}
