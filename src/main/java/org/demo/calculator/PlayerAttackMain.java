package org.demo.calculator;

import org.demo.dto.SkillModelDto;
import org.demo.factory.Enemy;
import org.demo.factory.Player;
import org.demo.list.SkillType;
import org.demo.util.SimpleLogger;

public class PlayerAttackMain {

    /**
     * 计算玩家使用的技能对敌人造成的伤害
     *
     * @param player 玩家状态
     * @param enemy 敌人状态
     * @param skill 玩家技能
     * @return 敌人状态
     */
    public static Enemy skillAttack(Player player, Enemy enemy, SkillModelDto skill) {
        if (skill == null || skill.hasType(SkillType.Error)) {
            SimpleLogger.log.error("技能列表为空或错误, 无法执行技能攻击。");
            return enemy;
        }

        // 计算技能伤害
        int damage = PlayerSkillAttackFactory.calculate(player, enemy, skill);
        // 扣除玩家魔力
        player.setCurrentManaPoint(player.getCurrentManaPoint() - skill.getCost());
        // 对敌人造成伤害
        enemy.takeDamage(damage);
        return enemy;
    }
}
