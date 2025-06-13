package org.demo.dto;

import java.util.List;

public class EnemyModelDto {

    // 敌人ID
    private final String id;
    // 敌人名称
    private final String name;
    // 最大生命值
    private final int maxHp;
    // 攻击力
    private final int attack;
    // 防御力
    private final int defense;
    // 掉落经验
    private final int dropExp;
    // 掉落物品
    private final List<ItemModelDto> dropItems;

    /**
     * 构造一个敌人模型数据传输对象。
     *
     * @param id 敌人ID
     * @param name 敌人名称
     * @param maxHp 最大生命值
     * @param attack 攻击力
     * @param defense 防御力
     * @param dropExp 掉落经验
     * @param dropItems 掉落物品
     */
    public EnemyModelDto(String id, String name, int maxHp, int attack, int defense, int dropExp, List<ItemModelDto> dropItems) {
        this.id = id;
        this.name = name;
        this.maxHp = maxHp;
        this.attack = attack;
        this.defense = defense;
        this.dropExp = dropExp;
        this.dropItems = dropItems;
    }

    /**
     * 获取敌人ID
     *
     * @return 敌人ID
     */
    public String getId() {
        return id;
    }

    /**
     * 获取敌人名称
     *
     * @return 敌人名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取敌人最大生命值
     *
     * @return 最大生命值
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * 获取敌人攻击力
     *
     * @return 攻击力
     */
    public int getAttack() {
        return attack;
    }

    /**
     * 获取敌人防御力
     *
     * @return 防御力
     */
    public int getDefense() {
        return defense;
    }

    public int getDropExp() {
        return dropExp;
    }

    public List<ItemModelDto> getDropItems() {
        return dropItems;
    }

    @Override
    public String toString() {
        return "EnemyModelDto{"
                + "id='" + id + '\''
                + ", 敌人名称='" + name + '\''
                + ", 最大生命值=" + maxHp
                + ", 攻击力=" + attack
                + ", 防御力=" + defense
                + ", 掉落经验=" + dropExp
                + ", 掉落物品=" + dropItems.toString()
                + '}';
    }
}
