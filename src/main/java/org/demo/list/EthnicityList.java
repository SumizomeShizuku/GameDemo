package org.demo.list;

public enum EthnicityList {
    /**
     * 空尾族
     */
    KITSARIEL(4, 7, 1, 2, 4, "空尾族"),
    /**
     * 人族
     */
    GRAVENTH(8, 3, 2, 2, 3, "人族"),
    /**
     * 林语族
     */
    SYLVARIN(5, 6, 1, 3, 3, "林语族"),
    /**
     * 亡影族
     */
    THARNYX(10, 1, 5, 1, 1, "亡影族");

    private final int healthPoint;
    private final int manaPoint;
    private final int strength;
    private final int agility;
    private final int intelligence;
    private final String ethnicityZh;

    /**
     * 构造一个带有基础属性的职业类型。
     *
     * @param healthPoint 初始生命值( Health Points )
     * @param manaPoint 初始魔法值( Mana Points )
     * @param strength 初始力量属性( 影响物理攻击 )
     * @param agility 初始敏捷属性( 影响闪避与速度 )
     * @param intelligence 初始智力属性( 影响魔法攻击与回复 )
     * @param ethnicityZh 种族名(中文)
     */
    private EthnicityList(int healthPoint, int manaPoint, int strength, int agility, int intelligence, String ethnicityZh) {
        this.healthPoint = healthPoint;
        this.manaPoint = manaPoint;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.ethnicityZh = ethnicityZh;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getManaPoint() {
        return manaPoint;
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

    public String getEthnicityZh() {
        return ethnicityZh;
    }

    public static EthnicityList getEthnicity(int choice) {
        return switch (choice) {
            case 1 ->
                KITSARIEL;
            case 2 ->
                GRAVENTH;
            case 3 ->
                SYLVARIN;
            case 4 ->
                THARNYX;
            default ->
                throw new IllegalArgumentException("Invalid job choice: " + choice);
        };
    }

}
