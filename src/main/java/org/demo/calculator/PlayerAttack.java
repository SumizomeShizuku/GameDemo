package org.demo.calculator;

import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;
import org.demo.list.Action;
import org.demo.list.SkillType;
import org.demo.util.SimpleLogger;

public class PlayerAttack {

    public static Enemy normalAttack(PlayerModelDto player, Enemy enemy) {
        enemy.takeDamage(PlayerAttackCalculator.calculateNormalPhyAttack(player, enemy));
        return enemy;
    }

    public static Enemy skillAttack(PlayerModelDto player, Enemy enemy, Action validSkill) {
        if (validSkill == null || validSkill.getSkillList().hasType(SkillType.Error)) {
            SimpleLogger.log.error("技能列表为空或错误，无法执行技能攻击。");
            return enemy;
        }

        boolean isMagic = validSkill.getSkillTypes().contains(SkillType.Magic);
        boolean isPhysics = validSkill.getSkillTypes().contains(SkillType.Physics);

        if (isMagic && !isPhysics) {
            SimpleLogger.log.info("魔法攻击");
            enemy.takeDamage(PlayerAttackCalculator.calculateMagicSkill(player, enemy, validSkill));
        } else if (isPhysics && !isMagic) {
            SimpleLogger.log.info("物理攻击");
            enemy.takeDamage(PlayerAttackCalculator.calculatePhysicsSkill(player, enemy, validSkill));
        } else if (isMagic && isPhysics) {
            SimpleLogger.log.info("混合攻击");
            int damage = PlayerAttackCalculator.calculateMagicSkill(player, enemy, validSkill);
            damage += PlayerAttackCalculator.calculatePhysicsSkill(player, enemy, validSkill);
            enemy.takeDamage(damage);
        } else {
            SimpleLogger.log.error("未知的技能类型: " + validSkill.getSkillTypes());
        }
        return enemy;
    }
}
