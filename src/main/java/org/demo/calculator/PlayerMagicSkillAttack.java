package org.demo.calculator;

import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;
import org.demo.list.Action;
import org.demo.util.SimpleLogger;

public class PlayerMagicSkillAttack {

    /**
     * 根据职业属性，计算伤害
     *
     * @param player 玩家属性
     * @param damagePower 技能威力
     * @return 伤害
     */
    public static DamageResult calculateMagicDamage(PlayerModelDto player, Enemy enemy, int damagePower) {
        int intelli = player.getIntelligence();
        double p;
        if (damagePower < 20) {
            p = 0.5 + (double) damagePower / 300.0;
        } else if (damagePower < 60) {
            p = 0.75 + (double) (damagePower - 20) / 150.0;
        } else if (damagePower < 120) {
            p = 1.0 + (double) (damagePower - 60) / 250.0;
        } else {
            p = 1.25 + (double) (damagePower - 120) / 400.0;
        }
        double rawDamage;
        if (damagePower == 1) {
            rawDamage = intelli * Math.pow(intelli, 0.08) / 2;
        } else {
            rawDamage = 0.6 * Math.pow(intelli, 1.3) * p;
        }

        int enemyPDEF = enemy.getDefense();
        double defenseRatio = (double) enemyPDEF / (enemyPDEF + 100.0);
        double finalDamage = rawDamage * (1.0 - defenseRatio);

        if (finalDamage < 1) {
            finalDamage = 2; // 最小伤害为2
        }

        player.setBaseAttribute(Math.round(rawDamage * 100.0) / 100.0);

        boolean isCritical = Math.random() < player.getCriticalHitRate();
        if (isCritical) {
            finalDamage = finalDamage * 1.25;
        }

        double min = finalDamage * 0.60;
        double max = finalDamage * 1.40;

        int DamageResult = (int) (min + Math.random() * (max - min));
        return new DamageResult(DamageResult, isCritical);
    }

    /**
     * 计算玩家对敌人造成的伤害 技能物理攻击
     *
     * @param player 玩家属性
     * @param enemy 敌人对象
     * @param damagePower 技能威力
     * @return 伤害值
     */
    public static int calculateMagicSkill(PlayerModelDto player, Enemy enemy, Action validSkill) {
        int damagePower = validSkill.getSkillBaseDamage();
        DamageResult damageResult = calculateMagicDamage(player, enemy, damagePower);

        // logDamage(player.getFirstName(), enemy.getName(), damageResult);
        StringBuilder sb = new StringBuilder();
        sb.append(player.getFirstName()).append(" 对 ").append(enemy.getName()).append(" 造成了");
        if (damageResult.isCritical()) {
            sb.append("[暴击] ").append(damageResult.getDamage()).append("! 魔法伤害");
        } else {
            sb.append(" ").append(damageResult.getDamage()).append(" 魔法伤害");
        }
        SimpleLogger.log.info(sb.toString());
        return damageResult.getDamage();
    }
}
