package org.demo.list;

import org.demo.constants.Constants;

public enum JobList {
    /**
     * 战士
     */
    WARRIOR(Constants.DEFAULT_EXP, 1, 45, 12, 1, new double[]{0.5, 0.3, 0.2}, "战士"),
    /**
     * 法师
     */
    MAGE(Constants.DEFAULT_EXP, 1, 22, 30, 1, new double[]{0.25, 0.25, 0.5}, "法师"),
    /**
     * 游侠
     */
    ARCHER(Constants.DEFAULT_EXP, 1, 37, 16, 1, new double[]{0.3, 0.5, 0.2}, "游侠"),
    /**
     * 圣职者
     */
    CLERIC(Constants.DEFAULT_EXP, 1, 28, 26, 1, new double[]{0.33, 0.33, 0.34}, "圣职者");

    private final int exp;
    private final int level;
    private final int healthPoint;
    private final int manaPoint;
    private final double moveSpeed;
    // 成长权重,长度为3 力量, 敏捷, 智力
    private final double[] growthWeights;
    private final String nameZh;

    /**
     * 构造一个带有基础属性的职业类型。
     *
     * @param exp 初始等级( Exp )
     * @param level 初始等级( Level )
     * @param healthPoint 初始生命值( Health Points )
     * @param manaPoint 初始魔法值( Mana Points )
     * @param moveSpeed 初始移动速度( 单位: 格/秒 )
     * @param growthWeights 职业成长权重
     * @param nameZh 职业名(中文)
     */
    JobList(int exp, int level, int healthPoint, int manaPoint, double moveSpeed, double[] growthWeights, String nameZh) {
        this.exp = exp;
        this.level = level;
        this.healthPoint = healthPoint;
        this.manaPoint = manaPoint;
        this.moveSpeed = moveSpeed;
        this.growthWeights = growthWeights;
        this.nameZh = nameZh;
    }

    public int getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public String getNameZh() {
        return nameZh;
    }

    public double[] getGrowthWeights() {
        return growthWeights;
    }

    public static JobList getJob(int choice) {
        return switch (choice) {
            case 1 ->
                WARRIOR;
            case 2 ->
                MAGE;
            case 3 ->
                ARCHER;
            case 4 ->
                CLERIC;
            default ->
                throw new IllegalArgumentException("Invalid job choice: " + choice);
        };
    }

}
