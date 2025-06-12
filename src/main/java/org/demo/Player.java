package org.demo;

import org.demo.dto.PlayerModelDto;
import org.demo.list.Action;
import org.demo.list.ActionType;
import org.demo.list.EnemyType;
import org.demo.list.SkillList;
import org.demo.list.SkillType;
import org.demo.util.AttackCalculator;
import org.demo.util.Enemy;
import org.demo.util.EnemyFactory;
import org.demo.util.LevelUpHandler;
import org.demo.util.SettingParam;
import org.demo.util.SimpleLogger;

public class Player {

    public static void playerInit() {
        PlayerModelDto layer = new PlayerModelDto();

        // 1:战士, 2:法师, 3:游侠, 4:圣职者
        int jobNum = 1;
        // 1:空尾族, 2:人族 3:林语族, 4:亡影族
        int ethnicityNum = 1;
        layer = SettingParam.setPlayerEthnicity(ethnicityNum, layer);
        layer = SettingParam.setPlayerJob(jobNum, layer);
        // layer.setExp(240000);
        layer.setFirstName("Anneliese");
        layer.setLastName("Wallis");
        LevelUpHandler levelUpHandler = new LevelUpHandler();
        // levelUpHandler.handleExpGain(layer, 120000);

        layer.setStrength(70);

        SimpleLogger.log.info(layer.toString());
        // List<Enemy> enemyList = new List<Enemy>;

        Enemy enemy = EnemyFactory.createEnemy(EnemyType.ORC);

        SimpleLogger.log.info("你遇到了一只敌人：" + enemy);

        int inputAct = 2; // 模拟玩家输入的技能类型
        // 1:普通攻击, 2:技能, 3:增益技能, 4:减益技能

        ActionType actionType = checkAct(inputAct);

        for (; true;) {
            if (enemy.getCurrentHp() <= 0) {
                SimpleLogger.log.info(layer.getFirstName() + " 击败了 " + enemy.getName() + "!");
                break;
            }

            switch (actionType) {
                case NormalAttack -> {
                    SimpleLogger.log.info("你选择了普通攻击");
                    enemy = normalAttack(layer, enemy);
                }
                case Skill -> {
                    SimpleLogger.log.info("你选择了技能" + SkillList.Skill0001.getName());
                    Action validSkill = new Action(org.demo.list.ActionType.Skill, SkillList.Skill0001);
                    // SimpleLogger.log.info(validSkill.toString());
                    enemy = skillAttack(layer, enemy, validSkill);
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

        // enemy = attPhy(layer, enemy);
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
        enemy.takeDamage(AttackCalculator.result(player, enemy));
        return enemy;
    }

    public static Enemy skillAttack(PlayerModelDto player, Enemy enemy, Action validSkill) {
        if (validSkill == null || validSkill.getSkillList().hasType(SkillType.Error)) {
            SimpleLogger.log.error("技能列表为空或错误，无法执行技能攻击。");
            return enemy;
        }
        int damagePower = validSkill.getSkillBaseDamage(); // 简化的伤害计算
        enemy.takeDamage(AttackCalculator.result(player, enemy, damagePower));
        // SimpleLogger.log.info(player.getFirstName() + " 使用技能 " + validSkill.getSkillName() + " 对 " + enemy.getName() + " 造成了 " + damage + " 点伤害。");
        return enemy;
    }
}
