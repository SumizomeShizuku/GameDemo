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
    RARE("月纱", 100),
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

    public int getAdjustedWeight(int luck) {
        // luck: 0~100
        // 假设：每+10幸运，低稀有度权重-10%，高稀有度+10%，中间线性插值
        double factor = 1.0;
        int ordinal = this.ordinal();
        int maxIndex = values().length - 1;
        double ratio = ordinal / (double) maxIndex; // 普通=0，最高=1

        // 高稀有度bonus越大，低稀有度bonus越小
        factor += ((ratio - 0.5) * 2) * (luck / 100.0); // luck=100时，普通-100%，最高+100%
        if (factor < 0.05) {
            factor = 0.1; // 保底5%概率，防止权重为0

                }return (int) Math.round(this.weight * factor);
    }

    /**
     * 按权重进行随机抽取一个稀有度。
     *
     * @param rng 随机数生成器
     * @return 抽取到的ItemRarity
     */
    public static ItemRarity randomRarity(Random rng, int luck) {
        int totalWeight = 0;
        for (ItemRarity rarity : values()) {
            totalWeight += rarity.getAdjustedWeight(luck);
        }
        int roll = rng.nextInt(totalWeight);
        int acc = 0;
        for (ItemRarity rarity : values()) {
            acc += rarity.getAdjustedWeight(luck);
            if (roll < acc) {
                return rarity;
            }
        }
        // Fallback, normally unreachable
        return COMMON;
    }
}
