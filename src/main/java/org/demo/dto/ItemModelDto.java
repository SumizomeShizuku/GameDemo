package org.demo.dto;

import java.util.EnumSet;

import org.demo.list.ItemType;

/**
 * 该类用于表示游戏或应用中的物品信息，包括ID、名称、类型、描述和价格等字段。
 * </p>
 *
 */
public class ItemModelDto {

    /**
     * 物品ID
     */
    private final String id;

    /**
     * 物品名称
     */
    private final String name;

    /**
     * 物品描述
     */
    private final String description;

    /**
     * 物品价格
     */
    private final int price;

    /**
     * 物品类型
     */
    private final EnumSet<ItemType> type;

    /**
     * 构造一个新的物品DTO实例。
     *
     * @param id 物品ID
     * @param name 物品名称
     * @param type 物品类型
     * @param description 物品描述
     * @param price 物品价格
     */
    public ItemModelDto(String id, String name, String description, int price, EnumSet<ItemType> type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    /**
     * 获取物品ID。
     *
     * @return 物品ID
     */
    public String getId() {
        return id;
    }

    /**
     * 获取物品名称。
     *
     * @return 物品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取物品类型。
     *
     * @return 物品类型
     */
    public EnumSet<ItemType> getType() {
        return type;
    }

    /**
     * 获取物品描述。
     *
     * @return 物品描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 获取物品价格。
     *
     * @return 物品价格
     */
    public int getPrice() {
        return price;
    }

    /**
     * 返回物品的字符串表示形式。
     *
     * @return 字符串，包含物品的主要属性信息
     */
    @Override
    public String toString() {
        return "物品ID='" + id + '\''
                + ", 物品名称='" + name + '\''
                // + ", 物品类型='" + type + '\''
                + ", 物品描述='" + description + '\''
                + ", 物品价格=" + price;
    }
}
