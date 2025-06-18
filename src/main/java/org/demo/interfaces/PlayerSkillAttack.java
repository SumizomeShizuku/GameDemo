package org.demo.interfaces;

import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;
import org.demo.list.Action;

public interface PlayerSkillAttack {

    /**
     * 技能主流程，返回最终伤害值（不包含结构信息）
     */
    int calculateSkill(PlayerModelDto player, Enemy enemy, Action action);
}
