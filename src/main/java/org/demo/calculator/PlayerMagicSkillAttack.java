package org.demo.calculator;

import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;

public class PlayerMagicSkillAttack extends AbstractSkillAttack {

    /**
     * 根据属性计算玩家技能造成伤害，以及该伤害是否暴击
     *
     * @param PlayerModelDto 玩家属性
     * @param enemy 敌人属性
     * @param damagePower 玩家所选择技能威力
     * @return DamageResult类型 包含 玩家造成伤害，以及该伤害是否暴击
     */
    @Override
    protected DamageResult calculateDamage(PlayerModelDto player, Enemy enemy, int damagePower) {
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

}
