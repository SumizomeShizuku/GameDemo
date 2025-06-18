package org.demo.calculator;

import java.util.EnumSet;

import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;
import org.demo.interfaces.PlayerSkillAttack;
import org.demo.list.Action;
import org.demo.list.SkillType;

/**
 * 技能攻击工厂类：支持 EnumSet<SkillType>，可处理多类型技能。
 */
public class PlayerSkillAttackFactory {

    /**
     * 根据技能类型集合返回对应的攻击实现。 当包含 MAGIC 和 PHYSICS 时，归类为 MIX。 其余优先级顺序：MIX > MAGIC >
     * PHYSICS > NORMAL
     */
    public static PlayerSkillAttack getAttack(EnumSet<SkillType> types) {
        if (types.contains(SkillType.Magic) && types.contains(SkillType.Physics)) {
            return new PlayerMixSkillAttack();
        } else if (types.contains(SkillType.Mix)) {
            return new PlayerMixSkillAttack();
        } else if (types.contains(SkillType.Magic)) {
            return new PlayerMagicSkillAttack();
        } else if (types.contains(SkillType.Physics)) {
            return new PlayerPhysicsSkillAttack();
        } else if (types.contains(SkillType.Normal)) {
            return new PlayerNormalSkillAttack();
        } else {
            return new PlayerNormalSkillAttack();
        }
    }

    /**
     * 统一技能调用入口，供外部简洁调用。
     */
    public static int calculate(PlayerModelDto player, Enemy enemy, Action action) {
        EnumSet<SkillType> types = action.getSkillTypes();
        PlayerSkillAttack attack = getAttack(types);
        return attack.calculateSkill(player, enemy, action);
    }
}
