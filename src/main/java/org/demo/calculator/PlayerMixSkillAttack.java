package org.demo.calculator;

import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;
import org.demo.list.Action;
import org.demo.util.SimpleLogger;

public class PlayerMixSkillAttack {

    public static int calculateMixedSkill(PlayerModelDto player, Enemy enemy, Action validSkill) {
        int damagePower = validSkill.getSkillBaseDamage();

        DamageResult physical = PlayerPhysicsSkillAttack.calculatePhyDamage(player, enemy, damagePower);
        DamageResult magical = PlayerMagicSkillAttack.calculateMagicDamage(player, enemy, damagePower);

        // 混合比例
        double physicalRatio = 0.5;
        double magicalRatio = 0.5;

        int totalDamage = (int) (physical.getDamage() * physicalRatio + magical.getDamage() * magicalRatio);

        player.setBaseAttribute(Math.round(totalDamage * 100.0) / 100.0);

        boolean isCritical = physical.isCritical() || magical.isCritical(); // 任一暴击即算暴击

        StringBuilder sb = new StringBuilder();
        sb.append(player.getFirstName()).append(" 对 ").append(enemy.getName()).append(" 造成了");
        if (isCritical) {
            sb.append("[暴击] ").append(totalDamage).append("! 混合伤害");
        } else {
            sb.append(" ").append(totalDamage).append(" 混合伤害");
        }
        sb.append(" ( ").append(physical.getDamage()).append(" 物理・ ");
        sb.append(magical.getDamage()).append(" 魔法 )");

        SimpleLogger.log.info(sb.toString());

        return totalDamage;
    }
}
