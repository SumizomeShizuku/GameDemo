package org.demo.dto;

import java.util.List;

import org.demo.backpack.Backpack;
import org.demo.backpack.Equipment;
import org.demo.backpack.ItemInstance;
import org.demo.list.EthnicityList;
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
    // 当前HP
    private int currentHealthPoint;
    // 最大MP
    private int maxManaPoint;
    // 当前MP
    private int currentManaPoint;
    // 移速
    private double moveSpeed;
    // 力量
    private int strength;
    // 敏捷
    private int agility;
    // 智力
    private int intelligence;
    // 防御力
    private int physicsDefenes;
    // 魔法防御力
    private int magicDefenes;
    // HP恢复速度
    private double recoverHP;
    // MP恢复速度
    private double recoverMP;
    // 基础冷却时间
    private double commonCoolDown;
    // 基础攻击力
    private double baseAttribute;
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
        // setLevel(getExp());
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
     * 获取玩家的当前生命值
     *
     * @return
     */
    public int getCurrentHealthPoint() {
        return currentHealthPoint;
    }

    /**
     * 设置玩家的当前生命值
     *
     * @param currentHealthPoint 当前生命值
     */
    public void setCurrentHealthPoint(int currentHealthPoint) {
        this.currentHealthPoint = currentHealthPoint;
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
     * 获取玩家的当前魔法值
     *
     * @param currentManaPoint 当前魔法值
     */
    public int getCurrentManaPoint() {
        return currentManaPoint;
    }

    /**
     * 设置玩家的当前魔法值
     *
     * @return 当前魔法值
     */
    public void setCurrentManaPoint(int currentManaPoint) {
        this.currentManaPoint = currentManaPoint;
    }

    /**
     * 获取玩家的移动速度
     *
     * @return
     */
    public double getMoveSpeed() {
        return moveSpeed;
    }

    /**
     * 设置玩家的移动速度
     *
     * @param moveSpeed 移动速度
     */
    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
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
    public int getPhysicsDefenes() {
        return physicsDefenes;
    }

    /**
     * 设置玩家的防御力
     *
     * @param physicsDefenes 防御力
     */
    public void setPhysicsDefenes(int physicsDefenes) {
        this.physicsDefenes = physicsDefenes;
    }

    /**
     * 获取玩家的魔法防御力
     *
     * @return
     */
    public int getMagicDefenes() {
        return magicDefenes;
    }

    /**
     * 设置玩家的魔法防御力
     *
     * @param magicDefenes 魔法防御力
     */
    public void setMagicDefenes(int magicDefenes) {
        this.magicDefenes = magicDefenes;
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
     * 获取玩家的基础冷却时间
     *
     * @return
     */
    public double getCommonCoolDown() {
        return commonCoolDown;
    }

    /**
     * 设置玩家的基础冷却时间
     *
     * @param commonCoolDown 冷却时间( 单位: 秒 )
     */
    public void setCommonCoolDown(double commonCoolDown) {
        this.commonCoolDown = commonCoolDown;
    }

    /**
     * 获取玩家的基础攻击力
     *
     * @return
     */
    public double getBaseAttribute() {
        return baseAttribute;
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

    // 玩家受伤判定
    public void takeDamage(int damage) {
        this.currentHealthPoint = Math.max(0, currentHealthPoint - damage);
    }

    /**
     * 判断玩家是否存活
     *
     * @return true:存活, false:死亡
     */
    public boolean isAlive() {
        return this.currentHealthPoint > 0;
    }

    // /**
    //  * 玩家背包添加物品
    //  *
    //  * @param item 物品dto
    //  * @param count 数量
    //  */
    // public void addItem(ItemModelDto item, int count) {
    //     backpack.addItem(item, count);
    // }
    /**
     * 玩家背包添加物品
     *
     * @param item 物品dto
     * @param count 数量
     */
    public void addItem(ItemModelDto item, int count) {
        backpack.addItem(item, count);
    }

    /**
     * 玩家背包移除物品
     *
     * @param item 物品dto
     * @param count 数量
     * @return 是否移除成功
     */
    public boolean removeItem(ItemModelDto item, int count) {
        return backpack.removeItem(item, count);
    }

    /**
     * 玩家背包移除物品
     *
     * @param id 物品id
     * @return 是否移除成功
     */
    public boolean removeItemById(long id) {
        return backpack.removeItemById(id);
    }

    /**
     * 玩家背包移除物品
     *
     * @param id 物品id
     * @return 是否移除成功
     */
    public boolean removeStackableItemById(long id, int count) {
        return backpack.removeStackableItemById(id, count);
    }

    /**
     * 玩家背包物品展示
     *
     * @param itemConfig
     */
    public void showInventory() {
        backpack.showInventory();
    }

    /**
     * 玩家选择背包物品
     *
     * @param itemName 物品名
     */
    public List<ItemInstance> selectItem(String itemName) {
        return backpack.selectItem(itemName);
    }

    /**
     * 玩家装备道具
     */
    public void setEquip(long instanceId) {
        equipment.setEquip(backpack.selectItemById(instanceId));
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

        sb.append("属性 [");
        sb.append(ln).append("  姓: ").append(firstName);
        sb.append(ln).append("  名: ").append(lastName);
        sb.append(ln).append("  种族: ").append(ethnicity.getEthnicityZh());
        sb.append(ln).append("  职业: ").append(job.getNameZh());
        sb.append(ln).append("  持有经验: ").append(exp);
        sb.append(ln).append("  等级: ").append(level);
        sb.append(ln).append("  HP: ").append(currentHealthPoint).append(" / ").append(maxHealthPoint);
        sb.append(ln).append("  MP: ").append(currentManaPoint).append(" / ").append(maxManaPoint);
        sb.append(ln).append("  力量: ").append(strength);
        sb.append(ln).append("  敏捷: ").append(agility);
        sb.append(ln).append("  智力: ").append(intelligence);
        sb.append(ln).append("  每秒恢复HP: ").append(recoverHP);
        sb.append(ln).append("  每秒恢复MP: ").append(recoverMP);
        sb.append(ln).append("  基础冷却时间: ").append(commonCoolDown);
        sb.append(ln).append("  移速: ").append(moveSpeed);
        sb.append(ln).append("  基础攻击力: ").append(baseAttribute);
        sb.append(ln).append("  ").append(String.format("暴击率: %.2f%%", criticalHitRate * 100));
        sb.append(ln).append("]");
        return sb.toString();
    }

}
