package org.demo.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.demo.backpack.DropInfo;
import org.demo.list.EnemyEthnicityList;

public class EnemyModelDto {

    // 敌人ID
    private final String id;
    // 敌人名称
    private final String name;
    // 种族
    private final EnemyEthnicityList ethnicity;
    // 等级
    private final int level;
    // 最大生命值
    private final int maxHp;
    // 攻击力
    private final int attack;
    // 力量
    private final int strength;
    // 敏捷
    private final int agility;
    // 智力
    private final int intelligence;
    // 暴击率
    private final double criticalHitRate;
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
    public EnemyModelDto(String id, String name, EnemyEthnicityList ethnicity, int level, int maxHp, int attack,
            int strength, int agility, int intelligence, double criticalHitRate, int phyDefense, int magicDefense,
            int dropExp, double dropRate, Map<ItemModelDto, DropInfo> dropItems,
            List<String> areas, Map<String, SkillModelDto> enemySkills) {
        this.id = id;
        this.name = name;
        this.ethnicity = ethnicity;
        this.level = level;
        this.maxHp = maxHp;
        this.attack = attack;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.criticalHitRate = criticalHitRate;
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
     * 获取敌人种族
     *
     * @return 敌人种族
     */
    public EnemyEthnicityList getEthnicity() {
        return ethnicity;
    }

    /**
     * 获取敌人等级
     *
     * @return 敌人等级
     */
    public int getLevel() {
        return level;
    }

    /**
     * 获取敌人力量
     *
     * @return 敌人力量
     */
    public int getStrength() {
        return strength;
    }

    /**
     * 获取敌人敏捷
     *
     * @return 敌人敏捷
     */
    public int getAgility() {
        return agility;
    }

    /**
     * 获取敌人智力
     *
     * @return 敌人智力
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * 获取敌人暴击率
     *
     * @return 敌人暴击率
     */
    public double getCriticalHitRate() {
        return criticalHitRate;
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
     * 获取敌人掉落物品表物品名称
     *
     * @return 掉落物品表物品名称
     */
    public List<String> getDropItemsName() {
        List<String> list = dropItems.keySet().stream()
                .map(ItemModelDto::getName) // 假设getter为getName()
                .collect(Collectors.toList());
        return list;
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
        StringBuilder sb = new StringBuilder();
        String ln = System.lineSeparator();
        sb.append(ln).append("敌人属性 [").append(ln);
        sb.append("id: '").append(id).append('\'').append(ln)
                .append("  名称: '").append(name).append('\'').append(ln)
                .append("  种族: ").append(ethnicity != null ? ethnicity.getEthnicityZh() : "N/A").append(ln)
                .append("  等级: ").append(level).append(ln)
                .append("  最大生命值: ").append(maxHp).append(ln)
                .append("  攻击力: ").append(attack).append(ln)
                .append("  物理防御力: ").append(phyDefense).append(ln)
                .append("  魔法防御力: ").append(magicDefense).append(ln)
                .append("  力量: ").append(strength).append(ln)
                .append("  敏捷: ").append(agility).append(ln)
                .append("  智力: ").append(intelligence).append(ln)
                .append("  暴击率: ").append(String.format("%.2f%%", criticalHitRate * 100)).append(ln)
                .append("  掉落经验: ").append(dropExp).append(ln)
                .append("  掉落物品: ").append(getDropItemsName()).append(ln)
                .append("  活动区域: ").append(areas).append(ln)
                .append("  持有技能: ").append(getSkillNames()).append(ln)
                .append("]");

        return sb.toString();
    }
}
