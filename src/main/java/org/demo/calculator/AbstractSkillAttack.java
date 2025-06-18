package org.demo.calculator;

import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;
import org.demo.interfaces.PlayerSkillAttack;
import org.demo.list.Action;
import org.demo.util.SimpleLogger;

public abstract class AbstractSkillAttack implements PlayerSkillAttack {

    protected abstract DamageResult calculateDamage(PlayerModelDto player, Enemy enemy, int baseDamage);

    @Override
    public int calculateSkill(PlayerModelDto player, Enemy enemy, Action action) {
        DamageResult result = calculateDamage(player, enemy, action.getSkillBaseDamage());

        StringBuilder sb = new StringBuilder();
        sb.append(player.getFirstName()).append(" 对 ").append(enemy.getName()).append(" 造成了");
        if (result.isCritical()) {
            sb.append("[暴击] ").append(result.getDamage()).append("! 物理伤害");
        } else {
            sb.append(" ").append(result.getDamage()).append(" 物理伤害");
        }
        SimpleLogger.log.info(sb.toString());

        return result.getDamage();
    }

}
