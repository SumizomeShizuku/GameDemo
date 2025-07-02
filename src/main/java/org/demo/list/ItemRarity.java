package org.demo.list;

import java.util.Random;

/**
 * 物品稀有度枚举, 按由低至高排列。
 */
public enum ItemRarity {

    /**
     * 常曦 —— 常见之曦光
     */
    COMMON("常曦", 1000),
    /**
     * 绀辉 —— 深蓝微辉
     */
    UNCOMMON("绀辉", 400),
    /**
     * 月纱 —— 月光织纱
     */
    RARE("月纱", 10000),
    /**
     * 星嵌 —— 镶嵌星尘
     */
    EPIC("星嵌", 30),
    /**
     * 辉界 —— 辉映两界
     */
    LEGENDARY("辉界", 8),
    /**
     * 神溯 —— 溯本归源
     */
    MYTHIC("神溯", 2);

    private final String displayNameZh;
    private final int weight;

    ItemRarity(String displayNameZh, int weight) {
        this.displayNameZh = displayNameZh;
        this.weight = weight;
    }

    /**
     * 获取中文显示名。
     *
     * @return 稀有度名称( 中文 )
     */
    public String getDisplayNameZh() {
        return displayNameZh;
    }

    /**
     * 获取权重值。
     */
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return displayNameZh;
    }

    /**
     * 按权重进行随机抽取一个稀有度。
     *
     * @param rng 随机数生成器
     * @return 抽取到的ItemRarity
     */
    public static ItemRarity randomRarity(Random rng) {
        int totalWeight = 0;
        for (ItemRarity rarity : values()) {
            totalWeight += rarity.weight;
        }
        int roll = rng.nextInt(totalWeight);
        int acc = 0;
        for (ItemRarity rarity : values()) {
            acc += rarity.weight;
            if (roll < acc) {
                return rarity;
            }
        }
        // Fallback, normally unreachable
        return COMMON;
    }
}
