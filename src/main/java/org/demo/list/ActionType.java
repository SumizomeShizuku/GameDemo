package org.demo.list;

/**
 * 动作类型 该枚举定义了游戏中可能的动作类型。 包括普通攻击、技能、使用道具、防御等。
 */
public enum ActionType {
    /**
     * 普通攻击
     */
    NormalAttack,
    /**
     * 释放技能
     */
    Skill,
    /**
     * 使用道具
     */
    UseItem,
    /**
     * 防御
     */
    Defend,
    /**
     * 错误类型, 不支持的行动类型
     */
    Error;

    public static ActionType checkAct(int act) {
        return switch (act) {
            case 1 ->
                NormalAttack;
            case 2 ->
                Skill;
            case 3 ->
                UseItem;
            case 4 ->
                Defend;
            default ->
                Error;
        };
    }
}
