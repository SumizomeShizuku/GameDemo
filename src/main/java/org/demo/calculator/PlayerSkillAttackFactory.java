package org.demo.calculator;

import java.util.EnumSet;

import org.demo.dto.SkillModelDto;
import org.demo.factory.Enemy;
import org.demo.factory.Player;
import org.demo.interfaces.PlayerSkillAttack;
import org.demo.list.SkillType;

/**
 * 技能攻击工厂类: 支持 EnumSet<SkillType>, 可处理多类型技能。
 */
public class PlayerSkillAttackFactory {

    /**
     * 根据技能类型集合返回对应的攻击实现。 当包含 MAGIC 和 PHYSICS 时, 归类为 MIX。
     * <p>
     * 其余优先级顺序: MIX > MAGIC > PHYSICS > NORMAL
     *
     * @param EnumSet<SkillType> 技能类型(物理、魔法、混合或者其他)
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
     * 统一技能调用入口, 供外部简洁调用。
     *
     * @param PlayerModelDto 玩家属性
     * @param enemy 敌人属性
     * @param skillList 玩家所选择技能
     * @return 玩家造成伤害
     */
    public static int calculate(Player player, Enemy enemy, SkillModelDto skill) {
        EnumSet<SkillType> types = skill.getTypes();
        PlayerSkillAttack attack = getAttack(types);
        return attack.calculateSkill(player, enemy, skill);
    }
}
