package org.demo.factory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.demo.dto.EnemyModelDto;
import org.demo.dto.ItemModelDto;
import org.demo.list.DropInfo;

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
    private final Map<ItemModelDto, DropInfo> dropItems;

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

    public Map<ItemModelDto, DropInfo> getDropItems() {
        return dropItems;
    }

    /**
     * 随机掉落一定范围内种类数量的物品，按权重抽取，避免重复。
     *
     * @param minTypes 最少掉落几种物品
     * @param maxTypes 最多掉落几种物品
     * @return 掉落的物品列表（可能包含相同物品多次）
     */
    public List<ItemModelDto> generateDrops() {
        List<ItemModelDto> drops = new ArrayList<>();
        if (dropItems.isEmpty()) {
            return drops;
        }

        Random random = new Random();
        Set<ItemModelDto> selected = new HashSet<>();

        // 确定要掉落多少种不同物品
        int minTypes = 0; // 最少掉落0种物品
        int maxTypes = 1; // 最多掉落1种物品
        int typeCount = random.nextInt(maxTypes - minTypes + 1) + minTypes;// 随机掉落0到1种物品
        // 如果掉落物品数量超过可选项总数，则限制为可选项总数
        // 限制不超过可选项总数
        typeCount = Math.min(typeCount, dropItems.size());

        for (int i = 0; i < typeCount; i++) {
            double totalWeight = dropItems.entrySet().stream()
                    .filter(e -> !selected.contains(e.getKey()))
                    .mapToDouble(e -> e.getValue().getWeight())
                    .sum();

            if (totalWeight <= 0) {
                break;
            }

            double roll = random.nextDouble() * totalWeight;
            double current = 0;

            for (Map.Entry<ItemModelDto, DropInfo> entry : dropItems.entrySet()) {
                if (selected.contains(entry.getKey())) {
                    continue;
                }

                current += entry.getValue().getWeight();
                if (roll < current) {
                    selected.add(entry.getKey());
                    int qty = entry.getValue().getRandomQuantity();
                    for (int j = 0; j < qty; j++) {
                        drops.add(entry.getKey());
                    }
                    break;
                }
            }
        }

        return drops;
    }

    public String formatDropItems(List<ItemModelDto> droppedItems) {
        StringBuilder sb = new StringBuilder();
        String ln = System.lineSeparator();
        Map<ItemModelDto, Integer> countMap = new java.util.HashMap<>();

        for (ItemModelDto item : droppedItems) {
            countMap.put(item, countMap.getOrDefault(item, 0) + 1);
        }

        for (Map.Entry<ItemModelDto, Integer> entry : countMap.entrySet()) {
            ItemModelDto item = entry.getKey();
            int count = entry.getValue();
            sb.append(ln);
            sb.append("道具名称: ").append(item.getName()).append(ln);
            sb.append("物品类型: ").append(item.getType()).append(ln);
            sb.append("物品描述: ").append(item.getDescription()).append(ln);
            sb.append("物品价格: ").append(item.getPrice()).append(ln);
            sb.append("掉落数量: ").append(count).append(ln);
            sb.append("---------------------------").append(ln);
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return name + "[" + id + "]" + " (HP: " + currentHp + "/" + maxHp + ")";
    }
}
