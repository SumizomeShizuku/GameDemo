package org.demo.calculator;

import org.demo.constants.Constants;
import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;
import org.demo.interfaces.EnemySkillAttack;
import org.demo.list.SkillList;
import org.demo.list.SkillType;
import org.demo.util.SimpleLogger;

public abstract class EnemyAbstractSkillAttack implements EnemySkillAttack {

    protected int finalPhysicalDamage;
    protected int finalMagicDamage;
    protected boolean isPhysicalDamageCritical;
    protected boolean isMagicDamageCritical;

    public int getFinalPhysicalDamage() {
        return finalPhysicalDamage;
    }

    public int getFinalMagicDamage() {
        return finalMagicDamage;
    }

    /**
     * 根据属性计算玩家技能造成伤害, 以及该伤害是否暴击
     *
     * @param PlayerModelDto 玩家属性
     * @param enemy 敌人属性
     * @param damagePower 玩家所选择技能威力
     * @return DamageResult类型 包含 玩家造成伤害, 以及该伤害是否暴击
     */
    protected abstract DamageResult calculateDamage(Enemy enemy, PlayerModelDto player, int baseDamage);

    /**
     * 统一技能调用入口, 供外部简洁调用。
     * <p>
     * 同时输出玩家造成伤害的详细信息。
     *
     * @param enemy 敌人属性
     * @param PlayerModelDto 玩家属性
     * @param skillList 敌人技能
     * @return 敌人造成伤害
     */
    @Override
    public int calculateSkill(Enemy enemy, PlayerModelDto player, SkillList skillList) {
        DamageResult result = calculateDamage(enemy, player, skillList.getBaseDamage());

        boolean isMagic = skillList.getTypes().contains(SkillType.Magic);
        boolean isPhysics = skillList.getTypes().contains(SkillType.Physics) || skillList.getTypes().contains(SkillType.Normal);
        boolean isMix = skillList.getTypes().contains(SkillType.Mix) || (skillList.getTypes().contains(SkillType.Magic) && skillList.getTypes().contains(SkillType.Physics));
        String skillType;

        if (isMagic && isPhysics) {
            // 物伤法伤混合
            skillType = Constants.MIX_DAMAGE;
        } else if (isMix) {
            // 混合伤害
            skillType = Constants.MIX_DAMAGE;
        } else if (isMagic) {
            skillType = Constants.MAGIC_DAMAGE;
        } else if (isPhysics) {
            skillType = Constants.PHYSICS_DAMAGE;
        } else {
            skillType = Constants.UNDEFINED_DAMAGE;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(enemy.getName()).append(" 对 ").append(player.getFirstName()).append(" 造成了");
        if (result.isCritical()) {
            sb.append("[暴击] ").append(result.getDamage()).append("! ").append(skillType);
        } else {
            sb.append(" ").append(result.getDamage()).append(" ").append((skillType));
        }

        if (isMix) {
            sb.append(" ( ").append(finalPhysicalDamage);
            if (isPhysicalDamageCritical) {
                sb.append("!");
            }
            sb.append(" 物理・ ");

            sb.append(finalMagicDamage);
            if (isMagicDamageCritical) {
                sb.append("!");
            }
            sb.append(" 魔法 )");
        }

        SimpleLogger.log.info(sb.toString());

        return result.getDamage();
    }

}
