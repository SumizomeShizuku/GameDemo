package org.demo.list;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

public enum SkillList {
    /**
     * Skill0001: 普通攻击
     */
    Skill0001("普通攻击",
            EnumSet.of(SkillType.Normal),
            "使用手中的武器攻击, 根据武器属性造成伤害",
            10,
            0),
    /**
     * Skill0002: 挥石魔法
     */
    Skill0002("挥石魔法",
            EnumSet.of(SkillType.Physics),
            "使用石头进行攻击, 造成物理伤害。",
            10,
            3),
    /**
     * Skill0003: 火球术
     */
    Skill0003("火球术",
            EnumSet.of(SkillType.Magic),
            "发射火球, 造成魔法伤害。",
            30,
            5),
    /**
     * Skill0004: 冰锥术
     */
    Skill0004("冰锥术",
            EnumSet.of(SkillType.Physics, SkillType.Magic),
            "发射冰锥, 造成物理和魔法伤害。",
            30,
            5),
    /**
     * SkillERROR: 错误技能
     */
    SkillERROR("你不应该看到这个技能",
            EnumSet.of(SkillType.Error),
            "你不应该看到这个描述",
            9999,
            9999);

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
     * @return 如果技能包含该类型, 则返回 true；否则返回 false
     */
    public boolean hasType(SkillType type) {
        return types.contains(type);
    }

    /**
     * 检查技能是否包含魔法类型。
     *
     * @param types 要检查的技能类型集合
     * @return 如果技能包含所有指定的类型, 则返回 true；否则返回 false
     */
    public boolean isMagicSkill() {
        return types.contains(SkillType.Magic);
    }

    /**
     * 检查技能是否包含物理类型。
     *
     * @param types 要检查的技能类型集合
     * @return 如果技能包含所有指定的类型, 则返回 true；否则返回 false
     */
    public boolean isPhysicsSkill() {
        return types.contains(SkillType.Physics);
    }

    /**
     * 检查技能是否包含治疗类型。
     *
     * @param types 要检查的技能类型集合
     * @return 如果技能包含所有指定的类型, 则返回 true；否则返回 false
     */
    public boolean isHealingSkill() {
        return types.contains(SkillType.Healing);
    }

    /**
     * 检查技能是否包含辅助类型。
     *
     * @param types 要检查的技能类型集合
     * @return 如果技能包含所有指定的类型, 则返回 true；否则返回 false
     */
    public boolean isSupportSkill() {
        return types.contains(SkillType.Support);
    }

    /**
     * 检查技能是否为混合技能。 混合技能被定义为同时包含物理和魔法类型。
     *
     * @return 如果技能是混合技能, 则返回 true；否则返回 false
     */
    public boolean isHybridSkill() {
        // 认为“混合技能”是同时包含至少两种主力类型
        return types.contains(SkillType.Physics) && types.contains(SkillType.Magic);
    }

    /**
     * 检查技能是否为错误技能。 错误技能被定义为包含错误类型。
     *
     * @return 如果技能是错误技能, 则返回 true；否则返回 false
     */
    public boolean isErrorSkill() {
        return types.contains(SkillType.Error);
    }

    /**
     * 根据传入的技能条目数组生成技能Map
     *
     * @param skills 技能条目, 每项为 Object[], 第0个元素应为SkillList
     * @return Map, key为技能编号( 如"Skill0001" ), value为对应SkillList
     */
    public static Map<String, SkillList> skillMap(SkillList... skills) {
        return Arrays.stream(skills).collect(Collectors.toMap(
                SkillList::name, // 使用枚举的name()作为key
                s -> s // 枚举本身作为value
        ));
    }
}
