package org.demo.dto;

import java.util.EnumSet;

import org.demo.list.SkillType;

public class SkillModelDto {

    private String id; // 技能编号(如"Skill0001")
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

    // 下面这些方法全部照搬你枚举的实现：
    public boolean hasType(SkillType type) {
        return types.contains(type);
    }

    public boolean isMagicSkill() {
        return types.contains(SkillType.Magic);
    }

    public boolean isPhysicsSkill() {
        return types.contains(SkillType.Physics);
    }

    public boolean isHealingSkill() {
        return types.contains(SkillType.Healing);
    }

    public boolean isSupportSkill() {
        return types.contains(SkillType.Support);
    }

    public boolean isHybridSkill() {
        return types.contains(SkillType.Physics) && types.contains(SkillType.Magic);
    }

    public boolean isErrorSkill() {
        return types.contains(SkillType.Error);
    }
}
