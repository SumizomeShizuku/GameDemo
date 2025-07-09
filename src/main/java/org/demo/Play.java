package org.demo;

import org.demo.backpack.BackpackSlot;
import org.demo.constants.Constants;
import org.demo.factory.Player;
import org.demo.factory.PlayerFactory;
import org.demo.system.Map10x10;
import org.demo.util.InputHelper;
import org.demo.util.MapPrint;
import org.demo.util.SimpleLogger;
import org.demo.util.StatusPrint;

public class Play {

    public static void playerInit() throws Exception {

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
        player.gainExp(400);
        // player.syncBaseAttributesFromModel();
        System.out.println(player.getSkills());

        // EnemyModelDto goblin = EnemyRepository.getEnemyById("EN0001");
        // System.out.println(goblin.toString());
        // 随机房间数量
        Map10x10 maze = new Map10x10(18, 25, player, Constants.AREA1);
        // maze.visibleAllRoom();
        MapPrint.printMap(System.lineSeparator() + maze.toString());

        while (true) {
            // 打印玩家状态
            StatusPrint statusPrint = new StatusPrint();
            statusPrint.printStatus(player.toString());
            SimpleLogger.log.info(player.getModel().toString());
            // 打印地图
            MapPrint.printMap(maze.toString());
            player.showInventory();

            String input = InputHelper.getLowerCaseLine(
                    "选择前进方向 (w/a/s/d) " + System.lineSeparator()
                    + "若想使用道具, 请输入bag: " + System.lineSeparator()
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
                case "bag" -> {
                    // player.showEquipment();
                    // player.showInventory();
                    checkBackpack(player);
                }

                case "exit" -> {
                    return;
                }
                default -> {
                }

            }

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

    private static void checkBackpack(Player player) {
        int id;
        BackpackSlot bs;
        while (true) {
            StatusPrint statusPrint = new StatusPrint();
            statusPrint.printStatus(player.toString());
            player.showInventory();

            id = InputHelper.getInt(
                    "请选择背包中的物品编号: " + System.lineSeparator());
            bs = player.getSlot(id);

            if (id == 999) {
                return;
            }

            if (bs == null) {
                continue;
            }
            if (bs.isStackable()) {
                if (bs.getItem().getRestoreHp() != null) {
                    player.restoreHp(bs.getItem().getRestoreHp());
                    player.removeItemBySlot(id, 1);
                }
                if (bs.getItem().getRestoreMp() != null) {
                    player.restoreMp(bs.getItem().getRestoreMp());
                }
                if (bs.getItem().getSkillId() != null) {
                    // 非战斗中不可使用伤害类技能
                }
                if (bs.getItem().getLearnSkillId() != null) {
                    player.addSkill(bs.getItem().getLearnSkillId());
                }
            } else {
                break;
            }
        }
        if (bs.getInstance() != null) {
            String input = InputHelper.getLowerCaseLine(
                    "选择要装备的位置编号或是移除物品: " + System.lineSeparator()
                    + "若想退出背包, 请输入back: " + System.lineSeparator()
                    + "1.主手 2.副手 3.头部 4.上衣 5.裤子 6.鞋子 7.任意空首饰位" + System.lineSeparator()
                    + "8.首饰槽1 9.首饰槽2 10.首饰槽3 11.首饰槽4 12.移除该物品" + System.lineSeparator());
            switch (input) {
                case "1" -> {
                    player.setEquip("mainHand", id);
                }
                case "2" -> {
                    player.setEquip("offHand", id);
                }
                case "3" -> {
                    player.setEquip("helmet", id);
                }
                case "4" -> {
                    player.setEquip("armor", id);
                }
                case "5" -> {
                    player.setEquip("pants", id);
                }
                case "6" -> {
                    player.setEquip("shoes", id);
                }
                case "7" -> {
                    player.setEquip("accessory", id);
                }
                case "8" -> {
                    player.setEquip("accessory1", id);
                }
                case "9" -> {
                    player.setEquip("accessory2", id);
                }
                case "10" -> {
                    player.setEquip("accessory3", id);
                }
                case "11" -> {
                    player.setEquip("accessory4", id);
                }
                case "12" -> {
                    player.removeItemBySlot(id, 1);
                }
                case "back" -> {

                }
                default -> {
                }

            }
        }
    }
}
