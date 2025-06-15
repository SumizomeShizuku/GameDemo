package org.demo.list;

import java.util.EnumSet;

public enum SkillList {
    Skill0001("挥石魔法",
            EnumSet.of(SkillType.Physics),
            "使用石头进行攻击，造成物理伤害。",
            10,
            10),
    Skill0002("火球术",
            EnumSet.of(SkillType.Magic),
            "发射火球，造成魔法伤害。",
            10,
            20),
    SkillERROR("你不应该看到这个技能",
            EnumSet.of(SkillType.Error),
            "你不应该看到这个描述",
            0,
            0);

    private final String name;
    private final EnumSet<SkillType> types;
    private final String description;
    private final int baseDamage;
    private final int cost;

    SkillList(String name, EnumSet<SkillType> types, String description, int baseDamage, int cost) {
        this.name = name;
        this.types = types;
        this.description = description;
        this.baseDamage = baseDamage;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public EnumSet<SkillType> getTypes() {
        return types;
    }

    public String getDescription() {
        return description;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getCost() {
        return cost;
    }

    /**
     * 检查技能是否包含指定的类型。
     *
     * @param type 要检查的技能类型
     * @return 如果技能包含该类型，则返回 true；否则返回 false
     */
    public boolean hasType(SkillType type) {
        return types.contains(type);
    }

    /**
     * 检查技能是否包含魔法类型。
     *
     * @param types 要检查的技能类型集合
     * @return 如果技能包含所有指定的类型，则返回 true；否则返回 false
     */
    public boolean isMagicSkill() {
        return types.contains(SkillType.Magic);
    }

    /**
     * 检查技能是否包含物理类型。
     *
     * @param types 要检查的技能类型集合
     * @return 如果技能包含所有指定的类型，则返回 true；否则返回 false
     */
    public boolean isPhysicsSkill() {
        return types.contains(SkillType.Physics);
    }

    /**
     * 检查技能是否包含治疗类型。
     *
     * @param types 要检查的技能类型集合
     * @return 如果技能包含所有指定的类型，则返回 true；否则返回 false
     */
    public boolean isHealingSkill() {
        return types.contains(SkillType.Healing);
    }

    /**
     * 检查技能是否包含辅助类型。
     *
     * @param types 要检查的技能类型集合
     * @return 如果技能包含所有指定的类型，则返回 true；否则返回 false
     */
    public boolean isSupportSkill() {
        return types.contains(SkillType.Support);
    }

    /**
     * 检查技能是否为混合技能。 混合技能被定义为同时包含物理和魔法类型。
     *
     * @return 如果技能是混合技能，则返回 true；否则返回 false
     */
    public boolean isHybridSkill() {
        // 认为“混合技能”是同时包含至少两种主力类型
        return types.contains(SkillType.Physics) && types.contains(SkillType.Magic);
    }

    /**
     * 检查技能是否为错误技能。 错误技能被定义为包含错误类型。
     *
     * @return 如果技能是错误技能，则返回 true；否则返回 false
     */
    public boolean isErrorSkill() {
        return types.contains(SkillType.Error);
    }
}
