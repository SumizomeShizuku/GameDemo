package org.demo.backpack;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.demo.list.ItemType;
import org.demo.util.SimpleLogger;

public class Equipment {

    // 各装备槽位: 初始为空( null或空列表 )
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
    private final ItemInstance[] accessories = new ItemInstance[4];

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

    // 添加饰品到第一个空槽，成功返回true，失败返回false
    public void addAccessory(ItemInstance accessory) {
        for (int i = 0; i < accessories.length; i++) {
            if (accessories[i] == null) {
                accessories[i] = accessory;
                SimpleLogger.log.info("装备成功");
            }
        }
        SimpleLogger.log.info("没有空位");
    }

    // 判断是否有空饰品槽
    public boolean hasEmptyAccessorySlot() {
        for (ItemInstance a : accessories) {
            if (a == null) {
                return true;
            }
        }
        return false;
    }

    // 如果玩家选择替换某个槽
    public void replaceAccessory(int slot, ItemInstance accessory) {
        if (checkAccessorySlot(slot)) {
            accessories[slot] = accessory;
        }
    }

    // 工具方法：校验饰品槽下标
    private boolean checkAccessorySlot(int slot) {
        if (slot < 0 || slot >= accessories.length) {
            SimpleLogger.log.info("饰品槽栏位错误");
            return false;
        }
        return true;
    }

    // 如果需要查询所有已装备饰品
    public List<ItemInstance> getAccessories() {
        List<ItemInstance> list = new ArrayList<>();
        for (ItemInstance a : accessories) {
            if (a != null) {
                list.add(a);
            }
        }
        return list;
    }

    /**
     * 判断指定物品是否已装备。
     *
     * @param item 要检查的物品
     * @return 是否已装备
     */
    public boolean contains(ItemInstance item) {
        return true;
    }

    // 装备功能实装
    public void setEquip(ItemInstance item) {
        EnumSet<ItemType> types = item.getModel().getType();
        for (ItemType type : types) {
            switch (type) {
                case ItemType.WEAPON -> {
                    setMainHand(item);
                    return;
                }

                case ItemType.ARMOR -> {
                    setHelmet(item);
                    return;
                }

                case ItemType.ACCESSORY -> {
                    addAccessory(item);
                    return;
                }

                default -> {
                    // 检索下一个type
                }
            }
        }

    }

}
