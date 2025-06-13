package org.demo.list;

import java.util.List;

import org.demo.dto.EnemyModelDto;
import org.demo.dto.ItemModelDto;

public enum EnemyList {
    GOBLIN(new EnemyModelDto("GOBLIN", "哥布林", 30, 5, 2, 10, List.of(new ItemModelDto("GOBLIN_SWORD", "哥布林之剑", "weapon", "一把锋利的剑", 100)))),
    SLIME(new EnemyModelDto("SLIME", "史莱姆", 20, 3, 1, 50, List.of(new ItemModelDto("SLIME_JUICE", "史莱姆果汁", " consumable", "恢复少量生命值", 50)))),
    ORC(new EnemyModelDto("ORC", "兽人", 500, 10, 5, 50, List.of(new ItemModelDto("ORC_AXE", "兽人斧", "weapon", "一把沉重的斧头", 200))));

    private final EnemyModelDto template;

    EnemyList(EnemyModelDto template) {
        this.template = template;
    }

    public EnemyModelDto getTemplate() {
        return template;
    }
}
