package org.demo.list;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.demo.dto.ItemModelDto;

/**
 * 物品列表枚举类
 * <p>
 * 该类定义了游戏中的物品类型及其属性。
 * <p>
 * 每个物品类型包含一个模板 {@link ItemModelDto}，用于描述物品的基本属性。
 */
public enum ItemList {
    ORC_AXE("兽人斧", "weapon", "一把沉重的斧头", 200),
    GOBLIN_SWORD("哥布林之剑", "weapon", "一把锋利的剑", 100),
    SLIME_JUICE("史莱姆果汁", "consumable", "恢复少量生命值", 50);

    private final String name;
    private final String type;
    private final String description;
    private final int price;

    ItemList(String name, String type, String description, int price) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    /**
     * 获取物品的名称
     *
     * @return 物品名称
     */
    public ItemModelDto toItemModelDto() {
        return new ItemModelDto(this.name(), name, type, description, price);
    }

    /**
     * 获取物品的名称列表
     *
     * @return 物品名称列表
     */
    public static List<ItemModelDto> of(ItemList... items) {
        return Arrays.stream(items)
                .map(ItemList::toItemModelDto)
                .toList();
    }

    /**
     * 创建一个物品条目，包含物品、概率和数量
     */
    public static Object[] entry(ItemList item, double weight, int minQty, int maxQty) {
        return new Object[]{item, weight, minQty, maxQty};
    }

    /**
     * 将物品和数量映射为一个 Map<ItemModelDto, Integer>
     */
    // 构建 Map<ItemModelDto, DropInfo>
    public static Map<ItemModelDto, DropInfo> dropMap(Object[]... entries) {
        return Arrays.stream(entries).collect(Collectors.toMap(
                e -> ((ItemList) e[0]).toItemModelDto(),
                e -> new DropInfo((Double) e[1], (Integer) e[2], (Integer) e[3])
        ));
    }
}
