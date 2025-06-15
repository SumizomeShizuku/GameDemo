package org.demo.calculator;

import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;
import org.demo.list.Action;
import org.demo.util.SimpleLogger;

public class PlayerAttackCalculator {

    /**
     * 根据职业属性，计算伤害
     *
     * @param player 玩家属性
     * @param damagePower 技能威力
     * @return 伤害
     */
    public static DamageResult calculatePhyDamage(PlayerModelDto player, int damagePower) {
        int str = player.getStrength();
        double p;
        if (damagePower < 20) {
            p = 0.5 + (double) damagePower / 300.0;
        } else if (damagePower < 60) {
            p = 0.75 + (double) (damagePower - 20) / 150.0;
        } else if (damagePower < 120) {
            p = 1.0 + (double) (damagePower - 60) / 250.0;
        } else {
            p = 1.25 + (double) (damagePower - 120) / 400.0;
        }
        double rawDamage;
        if (damagePower == 1) {
            rawDamage = str * Math.pow(str, 0.08) / 2;
        } else {
            rawDamage = 0.6 * Math.pow(str, 1.3) * p;
        }

        if (rawDamage < 1) {
            rawDamage = 2; // 最小伤害为2
        }

        player.setBaseAttribute(Math.round(rawDamage * 100.0) / 100.0);

        boolean isCritical = Math.random() < player.getCriticalHitRate();
        if (isCritical) {
            rawDamage = rawDamage * 1.25;
        }

        double min = rawDamage * 0.80;
        double max = rawDamage * 1.20;

        int DamageResult = (int) (min + Math.random() * (max - min));
        return new DamageResult(DamageResult, isCritical);
    }

    /**
     * 计算玩家对敌人造成的伤害
     *
     * @param player 玩家属性
     * @param enemy 敌人对象
     * @return 伤害值
     */
    public static int result(PlayerModelDto player, Enemy enemy) {
        int damagePower = 20; // 默认伤害值
        DamageResult damageResult = calculatePhyDamage(player, damagePower);
        int damage = damageResult.getDamage();
        if (damageResult.isCritical()) {
            StringBuilder sb = new StringBuilder();
            sb.append(player.getFirstName()).append(" 对 ").append(enemy.getName()).append(" 造成了[暴击] ").append(damage).append("! 点伤害");
            // log.info(sb.toString());
            SimpleLogger.log.info(sb.toString());
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(player.getFirstName()).append(" 对 ").append(enemy.getName()).append(" 造成了 ").append(damage).append(" 点伤害");
            // log.info(sb.toString());
            SimpleLogger.log.info(sb.toString());
        }
        return damage;
    }

    /**
     * 计算玩家对敌人造成的伤害
     *
     * @param player 玩家属性
     * @param enemy 敌人对象
     * @param damagePower 技能威力
     * @return 伤害值
     */
    public static int result(PlayerModelDto player, Enemy enemy, Action validSkill) {
        int damagePower = validSkill.getSkillBaseDamage();
        DamageResult damageResult = calculatePhyDamage(player, damagePower);
        int damage = damageResult.getDamage();
        if (damageResult.isCritical()) {
            StringBuilder sb = new StringBuilder();
            sb.append(player.getFirstName()).append(" 对 ").append(enemy.getName()).append(" 造成了[暴击] ").append(damage).append("! 点伤害");
            // log.info(sb.toString());
            SimpleLogger.log.info(sb.toString());
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(player.getFirstName()).append(" 对 ").append(enemy.getName()).append(" 造成了 ").append(damage).append(" 点伤害");
            // log.info(sb.toString());
            SimpleLogger.log.info(sb.toString());
        }
        return damage;
    }

    public static class DamageResult {

        private final int damage;
        private final boolean isCritical;

        public DamageResult(int damage, boolean isCritical) {
            this.damage = damage;
            this.isCritical = isCritical;
        }

        public int getDamage() {
            return damage;
        }

        public boolean isCritical() {
            return isCritical;
        }
    }

}
