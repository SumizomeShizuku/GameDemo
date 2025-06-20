package org.demo.list;

import org.demo.dto.EnemyModelDto;

/**
 * 敌人列表枚举类
 * <p>
 * 该类定义了游戏中的敌人类型及其属性。
 * <p>
 * 每个敌人类型包含一个模板 {@link EnemyModelDto}, 用于描述敌人的基本属性和掉落物品。
 */
public enum EnemyList {
    GOBLIN(new EnemyModelDto("GOBLIN", "哥布林", 15, 5, 2, 10,
            ItemList.dropMap(
                    ItemList.entry(ItemList.GOBLIN_SWORD, 0.05, 1, 1),
                    ItemList.entry(ItemList.SLIME_JUICE, 0.95, 1, 5)),
            SkillList.skillMap(SkillList.Skill0001))),
    SLIME(new EnemyModelDto("SLIME", "史莱姆", 20, 3, 1, 500,
            ItemList.dropMap(
                    ItemList.entry(ItemList.SLIME_JUICE, 1.0, 1, 3)),
            SkillList.skillMap(SkillList.Skill0001))),
    ORC(new EnemyModelDto("ORC", "兽人", 500, 10, 5, 50,
            ItemList.dropMap(
                    ItemList.entry(ItemList.ORC_AXE, 1.0, 1, 1)),
            SkillList.skillMap(SkillList.Skill0001)));

    private final EnemyModelDto template;

    EnemyList(EnemyModelDto template) {
        this.template = template;
    }

    /**
     * 获取敌人的模板
     *
     * @return 敌人的模板 {@link EnemyModelDto}
     */
    public EnemyModelDto getTemplate() {
        return template;
    }
}
