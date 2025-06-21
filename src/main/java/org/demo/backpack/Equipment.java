package org.demo.backpack;

import java.util.ArrayList;
import java.util.List;

public class Equipment {

    // 各装备槽位：初始为空（null或空列表）
    // 主手
    private ItemInstance mainHand;
    // 副手
    private ItemInstance offHand;
    // 帽子
    private ItemInstance helmet;
    // 上衣
    private ItemInstance armor;
    // 裤子
    private ItemInstance pants;
    // 鞋子
    private ItemInstance shoes;
    // 首饰
    private List<ItemInstance> accessories = new ArrayList<>();

    public ItemInstance getMainHand() {
        return mainHand;
    }

    public void setMainHand(ItemInstance mainHand) {
        this.mainHand = mainHand;
    }

    public ItemInstance getOffHand() {
        return offHand;
    }

    public void setOffHand(ItemInstance offHand) {
        this.offHand = offHand;
    }

    public ItemInstance getHelmet() {
        return helmet;
    }

    public void setHelmet(ItemInstance helmet) {
        this.helmet = helmet;
    }

    public ItemInstance getArmor() {
        return armor;
    }

    public void setArmor(ItemInstance armor) {
        this.armor = armor;
    }

    public ItemInstance getPants() {
        return pants;
    }

    public void setPants(ItemInstance pants) {
        this.pants = pants;
    }

    public ItemInstance getShoes() {
        return shoes;
    }

    public void setShoes(ItemInstance shoes) {
        this.shoes = shoes;
    }

    public List<ItemInstance> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<ItemInstance> accessories) {
        this.accessories = accessories;
    }

    /**
     * 判断指定物品是否已装备。
     *
     * @param item 要检查的物品
     * @return 是否已装备
     */
    public boolean contains(ItemInstance item) {
        return item.equals(mainHand) || item.equals(offHand) || item.equals(helmet)
                || item.equals(armor) || item.equals(pants) || item.equals(shoes)
                || accessories.contains(item);
    }

}
