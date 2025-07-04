package org.demo.calculator;

import org.demo.factory.Enemy;
import org.demo.factory.Player;

/**
 * 敌人的普通物理攻击
 */
public class EnemyNormalSkillAttack extends EnemyAbstractSkillAttack {

    /**
     * 根据属性计算敌人技能造成伤害, 以及该伤害是否暴击
     *
     * @param enemy 敌人属性
     * @param PlayerModelDto 玩家属性
     * @param damagePower 技能威力
     * @return DamageResult类型 包含 玩家造成伤害, 以及该伤害是否暴击
     */
    @Override
    public DamageResult calculateDamage(Enemy enemy, Player player, int damagePower) {

        // 敌人攻击力, 根据力量变化
        int str = enemy.getStrength();
        // 固定威力
        // damagePower = 20;

        /* ==== 1. 伤害系数 p, 与现有 Normal 技能完全相同 ==== */
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

        /* ==== 2. 基础伤害 ==== */
        double rawDamage;
        if (damagePower == 1) {
            rawDamage = str * Math.pow(str, 0.08) / 2;
        } else {
            rawDamage = 0.6 * Math.pow(str, 1.3) * p;
        }

        /* ==== 3. 防御减伤, 与玩家 → 敌人公式对称 ==== */
        int pDef = player.getPhyDefense();
        double pDefRatio = (double) pDef / (pDef + 100.0);
        double finalDamage = rawDamage * (1.0 - pDefRatio);

        if (finalDamage < 1) {
            finalDamage = 1;          // 保底伤害
        }

        /* ==== 4. 暴击判定( 统一 5% ) ==== */
        boolean isCritical = Math.random() < 0.05;
        if (isCritical) {
            finalDamage *= 1.25;
        }

        /* ==== 5. 伤害浮动 ±20% ==== */
        double min = finalDamage * 0.80;
        double max = finalDamage * 1.20;
        int damage = (int) (min + Math.random() * (max - min));

        return new DamageResult(damage, isCritical);
    }
}
