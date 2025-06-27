package org.demo.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.demo.backpack.DropInfo;

public class EnemyModelDto {

    // 敌人ID
    private final String id;
    // 敌人名称
    private final String name;
    // 最大生命值
    private final int maxHp;
    // 攻击力
    private final int attack;
    // 物理防御力
    private final int phyDefense;
    // 魔法防御力
    private final int magicDefense;
    // 掉落经验
    private final int dropExp;
    // 物品掉落率
    private final double dropRate;
    // 使用 Map<ItemModelDto, DropInfo> 表示每个物品及其掉落权重/概率/数量
    // 掉落物品表
    private final Map<ItemModelDto, DropInfo> dropItems;
    // 敌人活动区域
    private final List<String> areas;
    // 敌人技能
    private Map<String, SkillModelDto> enemySkills;

    /**
     * 构造一个敌人模型数据传输对象。
     *
     * @param id 敌人ID
     * @param name 敌人名称
     * @param maxHp 最大生命值
     * @param attack 攻击力
     * @param phyDefense 防御力
     * @param dropExp 掉落经验
     * @param dropExp 物品掉落率
     * @param dropItems 掉落物品表
     * @param areas 活动区域
     * @param enemySkills 敌人技能
     */
    public EnemyModelDto(String id, String name, int maxHp, int attack, int phyDefense, int magicDefense, int dropExp,
            double dropRate,
            Map<ItemModelDto, DropInfo> dropItems, List<String> areas, Map<String, SkillModelDto> enemySkills) {
        this.id = id;
        this.name = name;
        this.maxHp = maxHp;
        this.attack = attack;
        this.phyDefense = phyDefense;
        this.magicDefense = magicDefense;
        this.dropExp = dropExp;
        this.dropRate = dropRate;
        this.dropItems = dropItems;
        this.areas = areas;
        this.enemySkills = enemySkills;
    }

    /**
     * 获取敌人ID
     *
     * @return 敌人ID
     */
    public String getId() {
        return id;
    }

    /**
     * 获取敌人名称
     *
     * @return 敌人名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取敌人最大生命值
     *
     * @return 最大生命值
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * 获取敌人攻击力
     *
     * @return 攻击力
     */
    public int getAttack() {
        return attack;
    }

    /**
     * 获取敌人物理防御力
     *
     * @return 物理防御力
     */
    public int getPhyDefense() {
        return phyDefense;
    }

    /**
     * 获取敌人魔法防御力
     *
     * @return 魔法防御力
     */
    public int getMagicDefense() {
        return magicDefense;
    }

    /**
     * 获取敌人掉落经验
     *
     * @return 掉落经验
     */
    public int getDropExp() {
        return dropExp;
    }

    /**
     * 获取敌人物品掉落率
     *
     * @return 物品掉落率
     */
    public double getDropRate() {
        return dropRate;
    }

    /**
     * 获取敌人掉落物品表
     *
     * @return 掉落物品表
     */
    public Map<ItemModelDto, DropInfo> getDropItems() {
        return dropItems;
    }

    /**
     * 取得敌人活动区域
     *
     * @return
     */
    public List<String> getAreas() {
        return areas;
    }

    /**
     * 获取敌人技能
     *
     * @return 敌人技能
     */
    public Map<String, SkillModelDto> getEnemySkills() {
        return enemySkills;
    }

    public void setSkills(Map<String, SkillModelDto> enemySkills) {
        this.enemySkills = enemySkills;
    }

    /**
     * 返回技能名字符串（逗号分隔），不包含技能ID
     *
     * @param skillMap Map<String, SkillList>
     * @return 技能名称字符串，如 "普通攻击, 挥石魔法, 火球术"
     */
    public List<String> getSkillNames() {
        List<String> list = new ArrayList<>();
        for (SkillModelDto skill : enemySkills.values()) {
            list.add(skill.getName());
        }
        return list;
    }

    @Override
    public String toString() {
        return "敌人id='" + id + '\''
                + ", 敌人名称='" + name + '\''
                + ", 最大生命值=" + maxHp
                + ", 攻击力=" + attack
                + ", 物理防御力=" + phyDefense
                + ", 掉落经验=" + dropExp
                + ", 掉落物品=" + dropItems.toString()
                + ", 活动区域=" + areas
                + ", 持有技能=" + getSkillNames();
    }
}
