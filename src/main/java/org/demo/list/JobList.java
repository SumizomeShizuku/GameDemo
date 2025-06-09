package org.demo.list;

public enum JobList {
    WARRIOR(0, 45, 12, 1, 7, 3, 1, new double[]{0.6, 0.2, 0.2}, "战士"),
    MAGE(0, 22, 30, 1, 2, 1, 8, new double[]{0.2, 0.2, 0.6,}, "法师"),
    ARCHER(0, 37, 16, 1, 4, 5, 2, new double[]{0.4, 0.5, 0.1,}, "游侠"),
    CLERIC(0, 28, 26, 1, 1, 3, 7, new double[]{0.3, 0.2, 0.5,}, "圣职者");

    private final int exp;
    private final int healthPoint;
    private final int manaPoint;
    private final double moveSpeed;
    private final int strength;
    private final int agility;
    private final int intelligence;
    // 成长权重,长度为3 力量, 敏捷, 智力
    private final double[] growthWeights;
    private final String nameZh;

    /**
     * 构造一个带有基础属性的职业类型。
     *
     * @param exp 初始等级（Exp）
     * @param healthPoint 初始生命值（Health Points）
     * @param manaPoint 初始魔法值（Mana Points）
     * @param moveSpeed 初始移动速度（单位：格/秒）
     * @param strength 初始力量属性（影响物理攻击）
     * @param agility 初始敏捷属性（影响闪避与速度）
     * @param intelligence 初始智力属性（影响魔法攻击与回复）
     * @param growthWeights 职业成长权重
     * @param nameZh 职业名(中文)
     */
    JobList(int exp, int healthPoint, int manaPoint, double moveSpeed, int strength, int agility,
            int intelligence, double[] growthWeights, String nameZh) {
        this.exp = exp;
        this.healthPoint = healthPoint;
        this.manaPoint = manaPoint;
        this.moveSpeed = moveSpeed;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.growthWeights = growthWeights;
        this.nameZh = nameZh;
    }

    public int getExp() {
        return exp;
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

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntelligence() {
        return intelligence;
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
