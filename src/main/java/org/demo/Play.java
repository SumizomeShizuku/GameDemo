package org.demo;

import java.util.HashMap;
import java.util.Map;

import org.demo.backpack.Backpack;
import org.demo.calculator.PlayerAttackCalculator;
import org.demo.dto.ItemModelDto;
import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;
import org.demo.factory.EnemyFactory;
import org.demo.factory.PlayerFactory;
import org.demo.list.Action;
import org.demo.list.ActionType;
import org.demo.list.EnemyList;
import org.demo.list.SkillList;
import org.demo.list.SkillType;
import org.demo.util.LevelUpHandler;
import org.demo.util.SimpleLogger;

public class Play {

    public static void playerInit() {
        // 模拟玩家输入的种族和职业
        // 1:空尾族, 2:人族 3:林语族, 4:亡影族
        int ethnicityNum = 1;
        // 1:战士, 2:法师, 3:游侠, 4:圣职者
        int jobNum = 1;
        // 模拟玩家输入的名字
        String playerFirstName = "Anneliese";
        String playerLastName = "Wallis";

        PlayerModelDto player = PlayerFactory.createPlayer(playerFirstName, playerLastName, ethnicityNum, jobNum);

        LevelUpHandler levelUpHandler = new LevelUpHandler();

        Map<String, ItemModelDto> config = new HashMap<>();
        Backpack bp = new Backpack();

        Enemy enemy = EnemyFactory.createEnemy(EnemyList.SLIME);

        SimpleLogger.log.info("你遇到了一只敌人：" + enemy);

        // 1:普通攻击, 2:技能, 3:增益技能, 4:减益技能
        int inputAct = 2; // 模拟玩家输入的技能类型
        SkillList skillList = SkillList.Skill0001; // 模拟玩家选择的技能

        ActionType actionType = checkAct(inputAct);

        for (; true;) {
            if (enemy.getCurrentHp() <= 0) {
                SimpleLogger.log.info(player.getFirstName() + " 击败了 " + enemy.getName() + "!");
                SimpleLogger.log.info("获得经验: " + enemy.getDropExp());
                levelUpHandler.handleExpGain(player, enemy.getDropExp());
                SimpleLogger.log.info("获得物品: " + enemy.getDropItems().toString());

                for (var item : enemy.getDropItems()) {
                    config.put(item.getId(), item);
                    bp.addItem(item, 1);
                }
                SimpleLogger.log.info(player.toString());
                SimpleLogger.log.info(bp.showInventory(config));
                break;
            }

            switch (actionType) {
                case NormalAttack -> {
                    SimpleLogger.log.info("你选择了普通攻击");
                    enemy = normalAttack(player, enemy);
                }
                case Skill -> {
                    SimpleLogger.log.info("你选择了技能" + skillList.getName());
                    Action validSkill = new Action(ActionType.Skill, skillList);
                    // SimpleLogger.log.info(validSkill.toString());
                    enemy = skillAttack(player, enemy, validSkill);
                }
                case Buff -> {
                    SimpleLogger.log.info("你选择了增益技能");
                    return;
                }
                case Debuff -> {
                    SimpleLogger.log.info("你选择了减益技能");
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
        // }

        // enemy = attPhy(player, enemy);
    }

    public static ActionType checkAct(int act) {
        return switch (act) {
            case 1 ->
                ActionType.NormalAttack;
            case 2 ->
                ActionType.Skill;
            case 3 ->
                ActionType.Buff;
            case 4 ->
                ActionType.Debuff;
            default ->
                ActionType.Error;
        };
    }

    public static Enemy normalAttack(PlayerModelDto player, Enemy enemy) {
        enemy.takeDamage(PlayerAttackCalculator.calculateNormalPhyAttack(player, enemy));
        return enemy;
    }

    public static Enemy skillAttack(PlayerModelDto player, Enemy enemy, Action validSkill) {
        if (validSkill == null || validSkill.getSkillList().hasType(SkillType.Error)) {
            SimpleLogger.log.error("技能列表为空或错误，无法执行技能攻击。");
            return enemy;
        }

        if (!validSkill.getSkillTypes().contains(SkillType.Physics)) {
            SimpleLogger.log.info("魔法攻击");
            enemy.takeDamage(PlayerAttackCalculator.calculateMagicSkill(player, enemy, validSkill));
            return enemy;
        } else if (!validSkill.getSkillTypes().contains(SkillType.Magic)) {
            SimpleLogger.log.info("物理攻击。");
            enemy.takeDamage(PlayerAttackCalculator.calculatePhysicsSkill(player, enemy, validSkill));
            return enemy;
        }

        return enemy;
    }
}
