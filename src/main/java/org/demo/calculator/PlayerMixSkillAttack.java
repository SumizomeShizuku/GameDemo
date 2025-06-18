package org.demo.calculator;

import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;

public class PlayerMixSkillAttack extends AbstractSkillAttack {

    @Override
    protected DamageResult calculateDamage(PlayerModelDto player, Enemy enemy, int damagePower) {
        int str = player.getStrength();
        int intel = player.getIntelligence();
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

        double rawPhysical = 0.6 * Math.pow(str, 1.3) * p;
        double rawMagic = 0.5 * Math.pow(intel, 1.2) * p;

        double pDef = enemy.getDefense();
        double mRes = 0.0;

        double physicalReduction = pDef / (pDef + 100.0);
        double magicReduction = mRes / (mRes + 100.0);

        double finalDamage
                = rawPhysical * (1 - physicalReduction) * 0.6
                + rawMagic * (1 - magicReduction) * 0.4;

        if (finalDamage < 1) {
            finalDamage = 2;
        }

        player.setBaseAttribute(Math.round((rawPhysical + rawMagic) * 100.0) / 100.0);

        boolean isCritical = Math.random() < player.getCriticalHitRate();
        if (isCritical) {
            finalDamage *= 1.25;
        }

        double min = finalDamage * 0.8;
        double max = finalDamage * 1.2;
        int damage = (int) (min + Math.random() * (max - min));

        return new DamageResult(damage, isCritical);
    }
}
