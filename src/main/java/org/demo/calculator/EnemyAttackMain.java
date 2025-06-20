package org.demo.calculator;

import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;
import org.demo.list.SkillList;
import org.demo.list.SkillType;
import org.demo.util.SimpleLogger;

public class EnemyAttackMain {

    /**
     * 敌人进行一次普通攻击；如你后面要做技能系统, 可在此处加分支
     */
    public static PlayerModelDto skillAttack(Enemy enemy, PlayerModelDto player, SkillList skillLisl) {
        if (skillLisl == null || skillLisl.hasType(SkillType.Error)) {
            SimpleLogger.log.error("技能列表为空或错误, 无法执行技能攻击。");
            return player;
        }

        int damage = EnemySkillAttackFactory.calculate(enemy, player, skillLisl);
        player.takeDamage(damage);
        return player;
    }
}
