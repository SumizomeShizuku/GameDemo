package org.demo.backpack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.demo.dto.ItemModelDto;
import org.demo.factory.Enemy;

public class GenerateDrops {

    /**
     * 随机掉落一定范围内种类数量的物品, 按权重抽取, 避免重复。
     *
     * @param droppedItems 物品掉落率, 默认80%
     * @return 掉落的物品列表( 可能包含相同物品多次 )
     */
    public static Map<ItemModelDto, Integer> generateDrops(Enemy enemy) {
        Map<ItemModelDto, DropInfo> dropItems = enemy.getDropItems();
        // double dropRate = enemy.getDropRate();
        double dropRate = 1.0;
        Map<ItemModelDto, Integer> drops = new HashMap<>();
        if (dropItems.isEmpty()) {
            return drops;
        }

        Random random = new Random();

        // 判断是否掉落
        if (random.nextDouble() > dropRate) {
            return drops; // 不掉落任何物品
        }
        Set<ItemModelDto> selected = new HashSet<>();

        // 确定要掉落多少种不同物品
        int minTypes = 2; // 最少掉落0种物品
        int maxTypes = 2; // 最多掉落1种物品
        int typeCount = random.nextInt(maxTypes - minTypes + 1) + minTypes;// 随机掉落0到1种物品
        // 如果掉落物品数量超过可选项总数, 则限制为可选项总数
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
                    drops.put(entry.getKey(), qty);
                    break;
                }
            }
        }

        return drops;
    }
}
