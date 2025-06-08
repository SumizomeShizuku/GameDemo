package org.demo.List;

import java.util.List;

public enum PhysicsSkillList {
    PhyAtt0001("1", List.of(SkillType.Buff, SkillType.Physics));

    private final String name;
    private final List<SkillType> type;

    PhysicsSkillList(String name, List<SkillType> type){
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public List<SkillType> getType() {
        return type;
    }


}