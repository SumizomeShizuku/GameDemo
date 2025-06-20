package org.demo.calculator;

import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;

public class PlayerMixSkillAttack extends PlayerAbstractSkillAttack {

    /**
     * 根据属性计算玩家技能造成伤害, 以及该伤害是否暴击
     *
     * @param PlayerModelDto 玩家属性
     * @param enemy 敌人属性
     * @param damagePower 技能威力
     * @return DamageResult类型 包含 玩家造成伤害, 以及该伤害是否暴击
     */
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

        double physicalDamage = rawPhysical * (1 - physicalReduction) * 0.4;
        double magicDamage = rawMagic * (1 - magicReduction) * 0.4;

        // double finalDamage = physicalDamage + magicDamage;
        player.setBaseAttribute(Math.round((rawPhysical + rawMagic) * 100.0) / 100.0);

        // 物理伤害浮动
        double minphysicalDamage = physicalDamage * 0.8;
        double maxphysicalDamage = physicalDamage * 1.2;

        // 魔法伤害浮动
        double minmagicDamage = magicDamage * 0.7;
        double maxmagicDamage = magicDamage * 1.3;

        // AbstractSkillAttack中的finalPhysicalDamage和finalMagicDamage
        this.finalPhysicalDamage = (int) Math.round((minphysicalDamage + Math.random() * (maxphysicalDamage - minphysicalDamage)));
        this.finalMagicDamage = (int) Math.round((minmagicDamage + Math.random() * (maxmagicDamage - minmagicDamage)));

        // AbstractSkillAttack中的isPhysicalDamageCritical和isMagicDamageCritical
        this.isPhysicalDamageCritical = Math.random() < player.getCriticalHitRate();
        this.isMagicDamageCritical = Math.random() < player.getCriticalHitRate();
        if (this.isPhysicalDamageCritical) {
            this.finalPhysicalDamage *= 1.25;
        }

        if (this.isMagicDamageCritical) {
            this.finalMagicDamage *= 1.4;
        }

        boolean isCritical = false;
        if (this.isPhysicalDamageCritical || this.isMagicDamageCritical) {
            isCritical = true;
        }

        int finalDamage = this.finalPhysicalDamage + this.finalMagicDamage;

        if (finalDamage < 1) {
            finalDamage = 1;
        }

        return new DamageResult(finalDamage, isCritical);
    }
}
