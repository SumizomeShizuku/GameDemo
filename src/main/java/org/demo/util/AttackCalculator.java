package org.demo.util;

import org.demo.dto.PlayerModelDto;

public class AttackCalculator {

    // @SuppressWarnings({"FieldMayBeFinal", "NonConstantLogger"})
    // private Logger log;
    // /**
    //  * このクラスの新しいインスタント生成し、必要なLoggerを初期化する ログパスは
    //  * {@link LoggerConfig#setLogPath(String)}で設定する必要がある。
    //  */
    // public AttackCalculator() {
    //     LoggerConfig loggerConfig = LoggerConfig.getInstance();
    //     this.log = loggerConfig.getLogger("Log");
    // }
    /**
     * 根据职业属性，计算伤害
     *
     * @param player 玩家属性
     * @return 伤害
     */
    public DamageResult calculatePhyDamage(PlayerModelDto player) {
        int str = player.getStrength();
        double rawDamage = str * Math.pow(str, 0.08) / 2;
        player.setBaseAttribute(Math.round(rawDamage * 100.0) / 100.0);

        boolean isCritical = Math.random() < player.getCriticalHitRate();
        if (isCritical) {
            rawDamage = rawDamage * 1.25;
        }

        double min = rawDamage * 0.80;
        double max = rawDamage * 1.20;

        int result = (int) (min + Math.random() * (max - min));
        return new DamageResult(result, isCritical);
    }

    public int printResult(AttackCalculator attackCalculator, PlayerModelDto player) {
        AttackCalculator.DamageResult damageResult = attackCalculator.calculatePhyDamage(player);
        int damage = damageResult.getDamage();
        if (damageResult.isCritical()) {
            StringBuilder sb = new StringBuilder();
            sb.append(player.getFirstName()).append(" 造成了[暴击] ").append(damage).append("! 点伤害");
            // log.info(sb.toString());
            SimpleLogger.log.info(sb.toString());
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(player.getFirstName()).append(" 造成了 ").append(damage).append(" 点伤害");
            // log.info(sb.toString());
            SimpleLogger.log.info(sb.toString());
        }
        return damage;
    }

    public int result(AttackCalculator attackCalculator, PlayerModelDto player, Enemy enemy) {
        AttackCalculator.DamageResult damageResult = attackCalculator.calculatePhyDamage(player);
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
