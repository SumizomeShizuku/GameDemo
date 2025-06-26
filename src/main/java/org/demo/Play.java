package org.demo;

import org.demo.factory.Player;
import org.demo.factory.PlayerFactory;
import org.demo.system.Map10x10;
import org.demo.util.InputHelper;
import org.demo.util.PrintMap;
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
        player.gainExp(191);

        // 随机房间数量
        Map10x10 maze = new Map10x10(50, 60, player);
        PrintMap.printMap(System.lineSeparator() + maze.toString());

        while (true) {
            String input = InputHelper.getLowerCaseLine(
                    "选择前进方向 (w/a/s/d) " + System.lineSeparator()
                    + "若想退出游戏, 请输入exit: " + System.lineSeparator());

            switch (input) {
                case "w" ->
                    maze.playerMove("w");
                case "a" ->
                    maze.playerMove("a");
                case "s" ->
                    maze.playerMove("s");
                case "d" ->
                    maze.playerMove("d");
                case "exit" -> {
                    return;
                }
                default -> {
                    continue;
                }

            }
            PrintMap.printMap(System.lineSeparator() + maze.toString());
            player.showInventory();
        }

        // SimpleLogger.log.info(player.getModel().toString());
        // SimpleLogger.log.info(player.getModel().toString());
        // maze.visibleAllRoom();
        // PrintMap.printMap(System.lineSeparator() + maze.toString());
        // SimpleLogger.log.info(player.getModel().toString());
        // System.out.println(maze.whichRoom(1, 1).isRoom());
        // Point a = maze.whereIam();
        // System.out.println(a.toString());
        // System.err.println(maze.renderFancy());
        // Enemy enemy = EnemyFactory.createEnemy(EnemyList.GOBLIN);
        // SimpleLogger.log.info(player.getModel().getFirstName() + " 遇到了一只敌人: " +
        // enemy);
        // BattleSystem.startBattle(player, enemy);
        // player.setEquip("mainHand", 2);
        // player.showInventory();
        // // player.removeItemBySlot(2,1);
        // // SimpleLogger.log.info("道具删除");
        // player.showInventory();
        // enemy = EnemyFactory.createEnemy(EnemyList.GOBLIN);
        // SimpleLogger.log.info(player.getModel().getFirstName() + " 遇到了一只敌人: " +
        // enemy);
        // BattleSystem.startBattle(player, enemy);
        // player.setEquip("mainHand", 1);
        // player.showInventory();
        // // System.out.println(player.getBackpack().toString());
        // player.showEquipment();
        // player.putOffEquip("mainHand");
        // SimpleLogger.log.info("--------------分割");
        // player.showInventory();
        // player.showEquipment();
        // SimpleLogger.log.info("--------------分割-----------------");
        // player.setEquip("accessory", 2);
        // player.showInventory();
        // player.showEquipment();
        // player.setEquip("accessory4", 1);
        // player.showInventory();
        // player.showEquipment();
        // SimpleLogger.log.info(player.getModel().toString());
    }
}
