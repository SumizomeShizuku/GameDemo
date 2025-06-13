package org.demo.dto;

public class ItemModelDto {

    // 物品ID
    private final String id;
    // 物品名称
    private final String name;
    // 物品类型
    private final String type;
    // 物品描述
    private final String description;
    // 物品价格
    private final int price;

    public ItemModelDto(String id, String name, String type, String description, int price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ItemModelDto{"
                + "物品ID='" + id + '\''
                + ", 物品名称='" + name + '\''
                + ", 物品类型='" + type + '\''
                + ", 物品描述='" + description + '\''
                + ", 物品价格=" + price
                + '}';
    }
}
