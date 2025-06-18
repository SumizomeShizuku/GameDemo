package org.demo.list;

import java.util.EnumSet;

import org.demo.util.SimpleLogger;

public class Action {

    private ActionType actionType;
    private SkillList skillList;

    public Action(ActionType actionType, SkillList skillList) {
        if (actionType != ActionType.Skill && skillList != null) {
            SimpleLogger.log.info("技能列表不应为非技能动作类型提供。");
            this.actionType = actionType;
            this.skillList = null;
        } else if (actionType == ActionType.Skill && skillList == null) {
            SimpleLogger.log.info("技能列表为空，切换到错误状态。");
            this.actionType = ActionType.Error;
            this.skillList = SkillList.SkillERROR;
        } else if (actionType == ActionType.NormalAttack && skillList != null && skillList.hasType(SkillType.Normal)) {

        } else {
            this.actionType = actionType;
            this.skillList = skillList;
        }
    }

    public EnumSet<SkillType> getSkillTypes() {
        return (skillList != null) ? skillList.getTypes() : EnumSet.noneOf(SkillType.class);
    }

    public SkillList getSkillList() {
        return skillList;
    }

    public String getSkillName() {
        return (skillList != null) ? skillList.getName() : "未知技能";
    }

    public String getSkillDescription() {
        return (skillList != null) ? skillList.getDescription() : "没有描述。";
    }

    public int getSkillBaseDamage() {
        return (skillList != null) ? skillList.getBaseDamage() : 0;
    }

    @Override
    public String toString() {
        if (actionType == ActionType.Skill && skillList != null) {
            return "动作种类: " + actionType + ", 技能名: " + skillList.getName()
                    + ", 类型: " + skillList.getTypes() + ", 描述: " + skillList.getDescription();
        } else if (actionType == ActionType.Error) {
            return "动作种类: ERROR (invalid input)";
        } else {
            return "动作种类: " + actionType;
        }
    }
}
