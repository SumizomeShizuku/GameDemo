package org.demo.list;

import java.util.List;

public enum PhysicsSkillList {
    PhyAtt0001("挥石魔法",
            List.of(SkillType.Buff, SkillType.PhysicsAtt),
            5,
            10);

    private final String name;
    private final List<SkillType> type;
    private final int damage;
    private final int cost;

    PhysicsSkillList(String name, List<SkillType> type, int damage, int cost) {
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public List<SkillType> getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public int getCost() {
        return cost;
    }
}
