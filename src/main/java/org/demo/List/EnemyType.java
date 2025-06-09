package org.demo.list;

import org.demo.dto.EnemyModelDto;

public enum EnemyType {
    GOBLIN(new EnemyModelDto("GOBLIN", "哥布林", 30, 5, 2)),
    SLIME(new EnemyModelDto("SLIME", "史莱姆", 20, 3, 1)),
    ORC(new EnemyModelDto("ORC", "兽人", 500, 10, 5));

    private final EnemyModelDto template;

    EnemyType(EnemyModelDto template) {
        this.template = template;
    }

    public EnemyModelDto getTemplate() {
        return template;
    }
}
