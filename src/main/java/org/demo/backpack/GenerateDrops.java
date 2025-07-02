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
        double dropRate = enemy.getDropRate();
        // double dropRate = 1.0;
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
        // int minTypes = 1; // 至少掉落1件
        // int maxTypes = 2; // 至多掉落2件
        int typeCount;
        if (random.nextDouble() <= 0.95) {
            typeCount = 1; // 80%掉落1种物品
        } else {
            typeCount = 2; // 20%掉落2种物品
        }

        // int typeCount = random.nextInt(maxTypes - minTypes + 1) + minTypes;// 随机掉落1到1种物品
        // 如果掉落物品数量超过可选项总数, 则限制为可选项总数
        // 限制不超过可选项总数
        typeCount = Math.min(typeCount, dropItems.size());

        // 外层循环：控制本次最多抽取 typeCount 种物品( 每种只会被抽中一次 )
        for (int i = 0; i < typeCount; i++) {

            // 1. 计算还未被抽中的物品的总权重( 每次循环都需要重新计算, 因为已抽中的物品不再参与本轮抽取 )
            double totalWeight = dropItems.entrySet().stream()
                    .filter(e -> !selected.contains(e.getKey()))
                    .mapToDouble(e -> e.getValue().getWeight())
                    .sum();

            // 2. 如果没有可抽取的物品( 总权重<=0 ), 提前终止抽取
            if (totalWeight <= 0) {
                break;
            }

            // 3. 生成一个 [0, totalWeight) 区间的随机数, 用于加权随机抽选物品
            double roll = random.nextDouble() * totalWeight;
            double current = 0;

            // 4. 遍历所有掉落物品, 根据权重依次累加, 找到“roll”落入的区间, 抽中该物品
            for (Map.Entry<ItemModelDto, DropInfo> entry : dropItems.entrySet()) {
                // 跳过已被抽中的物品, 确保不重复
                if (selected.contains(entry.getKey())) {
                    continue;
                }

                // 累加当前物品的权重
                current += entry.getValue().getWeight();

                // 如果累加权重大于roll, 说明roll落在当前物品的区间
                if (roll < current) {
                    // 5. 抽中该物品
                    // 标记该物品已被抽中, 后续不再参与抽取
                    selected.add(entry.getKey());
                    // 根据DropInfo随机决定掉落数量
                    int qty = entry.getValue().getRandomQuantity();
                    // 记录到最终掉落Map
                    drops.put(entry.getKey(), qty);
                    // 本轮抽取结束, 进入下一个i
                    break;
                }
            }
        }

        return drops;
    }
}
