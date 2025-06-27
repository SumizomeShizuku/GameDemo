package org.demo.interfaces;

import org.demo.dto.SkillModelDto;
import org.demo.factory.Enemy;
import org.demo.factory.Player;

public interface PlayerSkillAttack {

    /**
     * 技能主流程, 返回最终伤害值( 不包含结构信息 )
     *
     * @param PlayerModelDto 玩家属性
     * @param enemy 敌人属性
     * @param skillList 玩家所选择技能
     * @return 玩家造成伤害
     */
    int calculateSkill(Player player, Enemy enemy, SkillModelDto skill);
}
