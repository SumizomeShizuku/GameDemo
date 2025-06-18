package org.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.demo.backpack.Backpack;
import org.demo.calculator.PlayerAttackMain;
import org.demo.dto.ItemModelDto;
import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;
import org.demo.factory.EnemyFactory;
import org.demo.factory.PlayerFactory;
import org.demo.list.ActionType;
import org.demo.list.EnemyList;
import org.demo.list.SkillList;
import org.demo.util.LevelUpHandler;
import org.demo.util.SimpleLogger;

public class Play {

    public static void playerInit() {
        // 模拟玩家输入的种族和职业
        // 1:空尾族, 2:人族 3:林语族, 4:亡影族
        int ethnicityNum = 1;
        // 1:战士, 2:法师, 3:游侠, 4:圣职者
        int jobNum = 2;
        // 模拟玩家输入的名字
        String playerFirstName = "Anneliese";
        String playerLastName = "Wallis";

        PlayerModelDto player = PlayerFactory.createPlayer(playerFirstName, playerLastName, ethnicityNum, jobNum);
        SimpleLogger.log.info("选择的职业: " + player.getJob().getNameZh());
        SimpleLogger.log.info("选择的种族: " + player.getEthnicity().getEthnicityZh());

        LevelUpHandler levelUpHandler = new LevelUpHandler();

        // 模拟玩家获得经验
        levelUpHandler.handleExpGain(player, 6260);

        Map<String, ItemModelDto> bag = new HashMap<>();
        Backpack bp = new Backpack();

        Enemy enemy = EnemyFactory.createEnemy(EnemyList.GOBLIN);
        SimpleLogger.log.info(player.getFirstName() + " 遇到了一只敌人： " + enemy);

        // 1:普通攻击, 2:技能, 3:增益技能, 4:减益技能
        // 模拟玩家输入的技能类型
        int inputAct = 2;

        // 检查玩家操作(攻击、防御、使用道具等)
        ActionType actionType = ActionType.checkAct(inputAct);

        // 模拟玩家选择的技能
        SkillList skill = SkillList.Skill0001;

        // 如果玩家选择普通攻击以外的行动，但是技能为普通攻击，则调整行动为普通攻击
        // 仅限测试中才会出现的情况
        if (inputAct != 1 && skill == SkillList.Skill0001) {
            actionType = ActionType.checkAct(1);
        }

        for (; true;) {
            if (enemy.getCurrentHp() <= 0) {
                SimpleLogger.log.info(player.getFirstName() + " 击败了 " + enemy.getName() + "!");
                SimpleLogger.log.info("获得经验: " + enemy.getDropExp());
                levelUpHandler.handleExpGain(player, enemy.getDropExp());
                List<ItemModelDto> drops = enemy.generateDrops();
                if (drops.isEmpty()) {
                    SimpleLogger.log.info("没有掉落物品。");
                } else {
                    SimpleLogger.log.info("获得物品: " + enemy.formatDropItems(drops));
                    for (var item : drops) {
                        bag.put(item.getId(), item);
                        bp.addItem(item, 1);
                    }
                }

                SimpleLogger.log.info(player.toString());
                SimpleLogger.log.info(bp.showInventory(bag));
                break;
            }

            // 模拟玩家选择操作(攻击、防御、使用道具等)
            switch (actionType) {
                case NormalAttack -> {
                    SimpleLogger.log.info(player.getFirstName() + " 选择了普通攻击");
                    enemy = PlayerAttackMain.skillAttack(player, enemy, SkillList.Skill0001);
                }
                case Skill -> {
                    SimpleLogger.log.info(player.getFirstName() + " 选择了技能" + skill.getName()
                            + " [ " + actionType.name() + " - " + skill.getTypes() + " ]");
                    enemy = PlayerAttackMain.skillAttack(player, enemy, skill);
                }
                case UseItem -> {
                    SimpleLogger.log.info(player.getFirstName() + " 选择了使用道具");
                    return;
                }
                case Defend -> {
                    SimpleLogger.log.info(player.getFirstName() + " 选择了防御");
                    return;
                }
                default -> {
                    SimpleLogger.log.error("未知的技能类型: " + actionType);
                    return;
                }

            }
            if (enemy.getCurrentHp() > 0) {
                SimpleLogger.log.info("敌人 " + enemy.getName() + " 还活着, 当前血量: " + enemy.getCurrentHp());
            }
        }
    }
}
