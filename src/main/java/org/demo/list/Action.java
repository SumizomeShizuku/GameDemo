package org.demo.list;

import java.util.EnumSet;

import org.demo.util.SimpleLogger;

public class Action {

    private final ActionType actionType;
    private final SkillList SkillList;

    public Action(ActionType actionType, SkillList SkillList) {
        if (actionType != ActionType.Skill && SkillList != null) {
            SimpleLogger.log.info("技能列表不应为非技能动作类型提供。");
            this.actionType = actionType;
            this.SkillList = null;
        } else if (actionType == ActionType.Skill && SkillList == null) {
            SimpleLogger.log.info("技能列表为空，切换到错误状态。");
            this.actionType = ActionType.Error;
            this.SkillList = org.demo.list.SkillList.SkillERROR;
        } else {
            this.actionType = actionType;
            this.SkillList = SkillList;
        }
    }

    public EnumSet<SkillType> getSkillTypes() {
        return (SkillList != null) ? SkillList.getTypes() : EnumSet.noneOf(SkillType.class);
    }

    public SkillList getSkillList() {
        return SkillList;
    }

    public String getSkillName() {
        return (SkillList != null) ? SkillList.getName() : "Unknown Skill";
    }

    public String getSkillDescription() {
        return (SkillList != null) ? SkillList.getDescription() : "No description.";
    }

    public int getSkillBaseDamage() {
        return (SkillList != null) ? SkillList.getBaseDamage() : 0;
    }

    @Override
    public String toString() {
        if (actionType == ActionType.Skill && SkillList != null) {
            return "动作种类: " + actionType + ", 技能名: " + SkillList.getName()
                    + ", 类型: " + SkillList.getTypes() + ", 描述: " + SkillList.getDescription();
        } else if (actionType == ActionType.Error) {
            return "动作种类: ERROR (invalid input)";
        } else {
            return "动作种类: " + actionType;
        }
    }
}
