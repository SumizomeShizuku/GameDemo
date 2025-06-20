package org.demo.calculator;

import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;

/**
 * 敌人的普通物理攻击
 */
public class EnemyNormalSkillAttack extends AbstractEnemySkillAttack {

    /**
     * 敌人对玩家发动一次普通攻击, 返回 DamageResult 并自动扣血
     */
    @Override
    public DamageResult calculateDamage(Enemy enemy, PlayerModelDto player, int damagePower) {

        int atk = enemy.getAttack();       // 敌人攻击力  :contentReference[oaicite:1]{index=1}
        damagePower = 20;              // 固定威力, 与玩家普通技保持一致 :contentReference[oaicite:2]{index=2}

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
        double rawDamage = 0.6 * Math.pow(atk, 1.3) * p;

        /* ==== 3. 防御减伤, 与玩家 → 敌人公式对称 ==== */
        int playerDEF = player.getPhysicsDefenes();
        double defenseRatio = (double) playerDEF / (playerDEF + 100.0);
        double finalDamage = rawDamage * (1.0 - defenseRatio);

        if (finalDamage < 1) {
            finalDamage = 2;          // 保底伤害
        }

        /* ==== 4. 暴击判定（统一 5%） ==== */
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
