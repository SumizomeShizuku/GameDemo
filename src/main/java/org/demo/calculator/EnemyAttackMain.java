package org.demo.calculator;

import org.demo.dto.SkillModelDto;
import org.demo.factory.Enemy;
import org.demo.factory.Player;
import org.demo.list.SkillType;
import org.demo.util.SimpleLogger;

public class EnemyAttackMain {

    /**
     * 敌人进行一次普通攻击；如你后面要做技能系统, 可在此处加分支
     */
    public static Player skillAttack(Enemy enemy, Player player, SkillModelDto skillLisl) {
        if (skillLisl == null || skillLisl.hasType(SkillType.Error)) {
            SimpleLogger.log.error("技能列表为空或错误, 无法执行技能攻击。");
            return player;
        }

        int damage = EnemySkillAttackFactory.calculate(enemy, player, skillLisl);
        player.takeDamage(damage);
        return player;
    }
}
