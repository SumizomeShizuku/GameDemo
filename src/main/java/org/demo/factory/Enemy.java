package org.demo.factory;

import java.util.List;

import org.demo.dto.EnemyModelDto;
import org.demo.dto.ItemModelDto;

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
    // 掉落经验
    private final int dropExp;
    // 掉落物品
    private final List<ItemModelDto> dropItems;

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
        this.dropExp = attr.getDropExp();
        this.dropItems = attr.getDropItems();
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

    public int getDropExp() {
        return dropExp;
    }

    public List<ItemModelDto> getDropItems() {
        return dropItems;
    }

    @Override
    public String toString() {
        return name + "[" + id + "]" + " (HP: " + currentHp + "/" + maxHp + ")";
    }
}
