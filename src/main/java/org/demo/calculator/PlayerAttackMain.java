package org.demo.calculator;

import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;
import org.demo.list.SkillList;
import org.demo.list.SkillType;
import org.demo.util.SimpleLogger;

public class PlayerAttackMain {

    public static Enemy skillAttack(PlayerModelDto player, Enemy enemy, SkillList skillLisl) {
        if (skillLisl == null || skillLisl.hasType(SkillType.Error)) {
            SimpleLogger.log.error("技能列表为空或错误，无法执行技能攻击。");
            return enemy;
        }

        int damage = PlayerSkillAttackFactory.calculate(player, enemy, skillLisl);
        enemy.takeDamage(damage);
        return enemy;
    }
}
