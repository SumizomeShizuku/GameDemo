package org.demo.list;

/**
 * 动作类型 该枚举定义了游戏中可能的动作类型。 包括普通攻击、技能、增益效果、减益效果等。
 */
public enum ActionType {
    NormalAttack,
    Skill,
    Buff,
    Debuff,
    Error; // 错误类型，表示未知或不支持的技能类型
}
