package org.demo.util;

import org.demo.dto.EnemyModelDto;

/**
 * 敌人类，表示游戏中的敌人。 包含敌人的属性和行为。
 */
public class Enemy {

    // 敌人ID
    private final String id;
    // 敌人名称
    private final String name;
    // 最大生命值
    private final int maxHp;
    // 当前生命值
    private int currentHp;
    // 攻击力
    private final int attack;
    // 防御力
    private final int defense;

    /**
     * 构造一个敌人对象。
     *
     * @param attr 敌人模型数据传输对象，包含敌人的属性
     */
    public Enemy(EnemyModelDto attr) {
        this.id = attr.getId();
        this.name = attr.getName();
        this.maxHp = attr.getMaxHp();
        this.currentHp = maxHp;
        this.attack = attr.getAttack();
        this.defense = attr.getDefense();
    }

    /**
     * 计算敌人受到的伤害。
     *
     * @param damage 伤害值
     */
    public void takeDamage(int damage) {
        currentHp = Math.max(0, currentHp - damage);
    }

    // 获取敌人名字
    public String getName() {
        return name;
    }

    //获取敌人血量
    public int getCurrentHp() {
        return currentHp;
    }

    // 获取敌人攻击力
    public int getAttack() {
        return attack;
    }

    // 获取敌人防御力
    public int getDefense() {
        return defense;
    }

    @Override
    public String toString() {
        return name + "[" + id + "]" + " (HP: " + currentHp + "/" + maxHp + ")";
    }
}
