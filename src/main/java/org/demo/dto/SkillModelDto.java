package org.demo.dto;

import java.util.EnumSet;

import org.demo.list.SkillType;

public class SkillModelDto {

    private String id; // 技能编号
    private String name; // 技能名
    private EnumSet<SkillType> types; // 技能类型集合
    private String description; // 技能描述
    private int baseDamage; // 基础伤害
    private int cost; // 技能消耗

    public SkillModelDto() {
    }

    public SkillModelDto(String id, String name, EnumSet<SkillType> types, String description, int baseDamage,
            int cost) {
        this.id = id;
        this.name = name;
        this.types = types;
        this.description = description;
        this.baseDamage = baseDamage;
        this.cost = cost;
    }

    // Getter / Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnumSet<SkillType> getTypes() {
        return types;
    }

    public void setTypes(EnumSet<SkillType> types) {
        this.types = types;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * 获取技能类型集合
     *
     * @param type 技能类型
     * @return 如果技能类型集合中包含指定类型，则返回true，否则返回false
     */
    public boolean hasType(SkillType type) {
        return types.contains(type);
    }

    /**
     * 判断是否为魔法技能
     *
     * @return 如果技能类型集合中包含魔法类型，则返回true，否则返回false
     */
    public boolean isMagicSkill() {
        return types.contains(SkillType.Magic);
    }

    /**
     * 判断是否为物理技能
     *
     * @return 如果技能类型集合中包含物理类型，则返回true，否则返回false
     */
    public boolean isPhysicsSkill() {
        return types.contains(SkillType.Physics);
    }

    /**
     * 判断是否为治疗技能
     *
     * @return 如果技能类型集合中包含治疗类型，则返回true，否则返回false
     */
    public boolean isHealingSkill() {
        return types.contains(SkillType.Healing);
    }

    /**
     * 判断是否为辅助技能
     *
     * @return 如果技能类型集合中包含辅助类型，则返回true，否则返回false
     */
    public boolean isSupportSkill() {
        return types.contains(SkillType.Support);
    }

    /**
     * 判断是否为混合技能 混合技能同时包含物理和魔法类型
     *
     * @return 如果技能类型集合中同时包含物理和魔法类型，则返回true，否则返回false
     */
    public boolean isHybridSkill() {
        return types.contains(SkillType.Physics) && types.contains(SkillType.Magic);
    }

    /**
     * 判断是否为错误技能
     *
     * @return 如果技能类型集合中包含错误类型，则返回true，否则返回false
     */
    public boolean isErrorSkill() {
        return types.contains(SkillType.Error);
    }
}
