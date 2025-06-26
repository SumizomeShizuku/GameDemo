package org.demo.backpack;

import org.demo.dto.ItemModelDto;

/**
 * 背包格子, 表示一个背包格可容纳的物品或物品实例。
 */
public class BackpackSlot {

    private ItemModelDto item;
    private int count;
    private ItemInstance instance; // 非叠加物品可用

    /**
     * 构造叠加物品格子
     *
     * @param item 物品模板
     * @param count 物品数量
     */
    public BackpackSlot(ItemModelDto item, int count) {
        this.item = item;
        this.count = count;
    }

    /**
     * 构造非叠加物品格子
     *
     * @param instance 非叠加物品实例
     */
    public BackpackSlot(ItemInstance instance) {
        this.instance = instance;
    }

    /**
     * 判断该格是否为叠加物品
     *
     * @return true 如果为叠加物品
     */
    public boolean isStackable() {
        return item != null;
    }

    public ItemModelDto getItem() {
        return item;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ItemInstance getInstance() {
        return instance;
    }
}
