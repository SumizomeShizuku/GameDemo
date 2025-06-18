package org.demo.list;

/**
 * 技能类型 该枚举定义了游戏中可能的技能类型。 包括物理攻击、魔法攻击、治疗技能、辅助技能等。 如果遇到未知或不支持的技能类型，将使用 Error 类型。
 */
public enum SkillType {
    Physics,
    Magic,
    Mix,
    Normal,
    Healing,
    Support,
    Error; // 错误类型，表示未知或不支持的技能类型
}
