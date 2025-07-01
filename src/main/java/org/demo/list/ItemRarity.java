package org.demo.list;

/**
 * 物品稀有度枚举，按由低至高排列。
 */
public enum ItemRarity {

    /**
     * 常曦 —— 常见之曦光
     */
    COMMON("常曦"),
    /**
     * 绀辉 —— 深蓝微辉
     */
    UNCOMMON("绀辉"),
    /**
     * 月纱 —— 月光织纱
     */
    RARE("月纱"),
    /**
     * 星嵌 —— 镶嵌星尘
     */
    EPIC("星嵌"),
    /**
     * 辉界 —— 辉映两界
     */
    LEGENDARY("辉界"),
    /**
     * 神溯 —— 溯本归源
     */
    MYTHIC("神溯");

    private final String displayNameZh;

    ItemRarity(String displayNameZh) {
        this.displayNameZh = displayNameZh;
    }

    /**
     * 获取中文显示名。
     *
     * @return 稀有度名称（中文）
     */
    public String getDisplayNameZh() {
        return displayNameZh;
    }

    @Override
    public String toString() {
        return displayNameZh;
    }
}
