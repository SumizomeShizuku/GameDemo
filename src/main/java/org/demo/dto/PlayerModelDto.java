package org.demo.dto;

import org.demo.backpack.Backpack;
import org.demo.backpack.Equipment;
import org.demo.list.EthnicityList;
import org.demo.list.ExpList;
import org.demo.list.JobList;

public class PlayerModelDto {

    // 姓
    private String firstName;
    // 名
    private String lastName;
    // 种族
    private EthnicityList ethnicity;
    // 职业
    private JobList job;
    // 经验
    private int exp;
    // 等级
    private int level;
    // 最大HP
    private int maxHealthPoint;
    // 最大MP
    private int maxManaPoint;
    // 力量
    private int strength;
    // 敏捷
    private int agility;
    // 智力
    private int intelligence;
    // 防御力
    private int physicsDefense;
    // 魔法防御力
    private int magicDefense;
    // HP恢复速度
    private double recoverHP;
    // MP恢复速度
    private double recoverMP;
    // 暴击率
    private double criticalHitRate;
    // 玩家背包
    private final Backpack backpack = new Backpack();
    // 玩家装备
    private final Equipment equipment = new Equipment();
    // 行动力
    private int actionsPerTurn;

    /**
     * 获取玩家的姓
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 设置玩家的姓
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 获取玩家的名
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 设置玩家的名
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 获取玩家的种族
     *
     * @return
     */
    public EthnicityList getEthnicity() {
        return ethnicity;
    }

    /**
     * 设置玩家的种族
     *
     * @param ethnicity 种族类型
     */
    public void setEthnicity(EthnicityList ethnicity) {
        this.ethnicity = ethnicity;
    }

    /**
     * 获取玩家的职业
     *
     * @return
     */
    public JobList getJob() {
        return job;
    }

    /**
     * 设置玩家的职业
     *
     * @param job 职业类型
     */
    public void setJob(JobList job) {
        this.job = job;
    }

    /**
     * 获取玩家的经验
     *
     * @return
     */
    public int getExp() {
        return exp;
    }

    /**
     * 设置玩家的经验
     *
     * @param exp 增加的经验值
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     * 获取玩家的等级
     *
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * 设置玩家的等级
     *
     * @param level 等级
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * 获取玩家的最大生命值
     *
     * @return
     */
    public int getMaxHealthPoint() {
        return maxHealthPoint;
    }

    /**
     * 设置玩家的最大生命值
     *
     * @param maxHealthPoint 生命值
     */
    public void setMaxHealthPoint(int maxHealthPoint) {
        this.maxHealthPoint = maxHealthPoint;
    }

    /**
     * 获取玩家的最大魔法值
     *
     * @return
     */
    public int getMaxManaPoint() {
        return maxManaPoint;
    }

    /**
     * 设置玩家的最大魔法值
     *
     * @param maxManaPoint 魔法值
     */
    public void setMaxManaPoint(int maxManaPoint) {
        this.maxManaPoint = maxManaPoint;
    }

    /**
     * 获取玩家的力量
     *
     * @return
     */
    public int getStrength() {
        return strength;
    }

    /**
     * 设置玩家的力量
     *
     * @param strength 力量值
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * 获取玩家的敏捷
     *
     * @return
     */
    public int getAgility() {
        return agility;
    }

    /**
     * 设置玩家的敏捷
     *
     * @param agility 敏捷值
     */
    public void setAgility(int agility) {
        this.agility = agility;
    }

    /**
     * 获取玩家的智力
     *
     * @return
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * 设置玩家的智力
     *
     * @param intelligence 智力值
     */
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    /**
     * 获取玩家的防御力
     *
     * @return
     */
    public int getPhyDefense() {
        return physicsDefense;
    }

    /**
     * 设置玩家的防御力
     *
     * @param physicsDefense 防御力
     */
    public void setPhyDefense(int physicsDefense) {
        this.physicsDefense = physicsDefense;
    }

    /**
     * 获取玩家的魔法防御力
     *
     * @return
     */
    public int getMagicDefense() {
        return magicDefense;
    }

    /**
     * 设置玩家的魔法防御力
     *
     * @param magicDefense 魔法防御力
     */
    public void setMagicDefense(int magicDefense) {
        this.magicDefense = magicDefense;
    }

    /**
     * 获取玩家的生命恢复
     *
     * @return
     */
    public double getRecoverHP() {
        return recoverHP;
    }

    /**
     * 设置玩家的生命恢复
     *
     * @param recoverHP 每秒恢复的生命值
     */
    public void setRecoverHP(double recoverHP) {
        this.recoverHP = recoverHP;
    }

    /**
     * 获取玩家的魔法恢复
     *
     * @return
     */
    public double getRecoverMP() {
        return recoverMP;
    }

    /**
     * 设置玩家的魔法恢复
     *
     * @param recoverMP 每秒恢复的魔法值
     */
    public void setRecoverMP(double recoverMP) {
        this.recoverMP = recoverMP;
    }

    /**
     * 获取玩家的暴击率
     *
     * @return
     */
    public double getCriticalHitRate() {
        return criticalHitRate;
    }

    /**
     * 设置玩家的暴击率
     *
     * @param criticalHitRate 暴击率( 0-1之间的小数 )
     */
    public void setCriticalHitRate(double criticalHitRate) {
        this.criticalHitRate = criticalHitRate;
    }

    /**
     * 获取玩家背包
     *
     * @return
     */
    public Backpack getBackpack() {
        return backpack;
    }

    /**
     * 获取玩家装备
     *
     * @return
     */
    public Equipment getEquipment() {
        return equipment;
    }

    /**
     * 获取行动力
     *
     * @return
     */
    public int getActionsPerTurn() {
        return actionsPerTurn;
    }

    /**
     * 设置行动力
     *
     * @param actionsPerTurn 物品实例
     */
    public void setActionsPerTurn(int actionsPerTurn) {
        this.actionsPerTurn = actionsPerTurn;
    }

    /**
     * 获取玩家的属性字符串表示
     *
     * @return 属性字符串
     */
    @Override
    public String toString() {
        String ln = System.lineSeparator();
        StringBuilder sb = new StringBuilder();

        sb.append("基本属性 [");
        sb.append(ln).append("  姓: ").append(firstName);
        sb.append(ln).append("  名: ").append(lastName);
        sb.append(ln).append("  种族: ").append(ethnicity.getEthnicityZh());
        sb.append(ln).append("  职业: ").append(job.getNameZh());
        sb.append(ln).append("  持有经验: ").append(exp).append(" / ").append(ExpList.getExpByLevel(level + 1).getMinExp());
        sb.append(ln).append("  等级: ").append(level);
        sb.append(ln).append("  HP上限: ").append(maxHealthPoint);
        sb.append(ln).append("  MP上限: ").append(maxManaPoint);
        sb.append(ln).append("  力量: ").append(strength);
        sb.append(ln).append("  敏捷: ").append(agility);
        sb.append(ln).append("  智力: ").append(intelligence);
        sb.append(ln).append("  物理防御力: ").append(physicsDefense);
        sb.append(ln).append("  魔法防御力: ").append(magicDefense);
        sb.append(ln).append("  每轮恢复HP: ").append(recoverHP);
        sb.append(ln).append("  每轮恢复MP: ").append(recoverMP);
        sb.append(ln).append("  ").append(String.format("暴击率: %.2f%%", criticalHitRate * 100));
        sb.append(ln).append("]");
        return sb.toString();
    }

}
