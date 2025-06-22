package org.demo.backpack;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.demo.list.ItemType;
import org.demo.util.SimpleLogger;

/**
 * 玩家装备栏，管理各类可穿戴装备和饰品槽。
 * <p>
 * 支持主手、副手、防具（头部、上衣、裤子、鞋子）、4格饰品自动分配与替换。
 * </p>
 */
public class Equipment {

    /**
     * 主手装备
     */
    private ItemInstance mainHand;
    /**
     * 副手装备
     */
    private ItemInstance offHand;
    /**
     * 头部装备（如帽子、头盔）
     */
    private ItemInstance helmet;
    /**
     * 上衣装备（如护甲）
     */
    private ItemInstance armor;
    /**
     * 裤子装备
     */
    private ItemInstance pants;
    /**
     * 鞋子装备
     */
    private ItemInstance shoes;
    /**
     * 饰品槽，固定4格，自动分配空位
     */
    private final ItemInstance[] accessories = new ItemInstance[4];

    /**
     * 获取主手装备。
     */
    public ItemInstance getMainHand() {
        return mainHand;
    }

    /**
     * 设置主手装备。
     */
    public void setMainHand(ItemInstance mainHand) {
        this.mainHand = mainHand;
        mainHand.setEquip(true);
    }

    /**
     * 获取副手装备。
     */
    public ItemInstance getOffHand() {
        return offHand;
    }

    /**
     * 设置副手装备。
     */
    public void setOffHand(ItemInstance offHand) {
        this.offHand = offHand;
    }

    /**
     * 获取头部装备。
     */
    public ItemInstance getHelmet() {
        return helmet;
    }

    /**
     * 设置头部装备。
     */
    public void setHelmet(ItemInstance helmet) {
        this.helmet = helmet;
    }

    /**
     * 获取上衣装备。
     */
    public ItemInstance getArmor() {
        return armor;
    }

    /**
     * 设置上衣装备。
     */
    public void setArmor(ItemInstance armor) {
        this.armor = armor;
    }

    /**
     * 获取裤子装备。
     */
    public ItemInstance getPants() {
        return pants;
    }

    /**
     * 设置裤子装备。
     */
    public void setPants(ItemInstance pants) {
        this.pants = pants;
    }

    /**
     * 获取鞋子装备。
     */
    public ItemInstance getShoes() {
        return shoes;
    }

    /**
     * 设置鞋子装备。
     */
    public void setShoes(ItemInstance shoes) {
        this.shoes = shoes;
    }

    /**
     * 尝试添加饰品到第一个空槽，成功则日志提示装备成功，否则提示没有空位。
     *
     * @param accessory 待装备的饰品实例
     */
    public void addAccessory(ItemInstance accessory) {
        for (int i = 0; i < accessories.length; i++) {
            if (accessories[i] == null) {
                accessories[i] = accessory;
                SimpleLogger.log.info("装备成功");
                return; // 成功后直接退出
            }
        }
        SimpleLogger.log.info("没有空位");
    }

    /**
     * 判断饰品槽是否还有空位。
     *
     * @return 是否有空的饰品槽
     */
    public boolean hasEmptyAccessorySlot() {
        for (ItemInstance a : accessories) {
            if (a == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 替换指定饰品槽的内容（适用于玩家手动替换）。
     *
     * @param slot 替换槽位下标（0~3）
     * @param accessory 新的饰品实例
     */
    public void replaceAccessory(int slot, ItemInstance accessory) {
        if (checkAccessorySlot(slot)) {
            accessories[slot] = accessory;
        }
    }

    /**
     * 检查饰品槽下标是否合法。
     *
     * @param slot 槽位下标
     * @return 合法返回true，否则输出日志并返回false
     */
    private boolean checkAccessorySlot(int slot) {
        if (slot < 0 || slot >= accessories.length) {
            SimpleLogger.log.info("饰品槽栏位错误");
            return false;
        }
        return true;
    }

    /**
     * 获取所有已装备的饰品（不含null槽位）。
     *
     * @return 已装备饰品的列表
     */
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
     * 判断指定物品是否已装备于任意槽位。
     *
     * @param item 要检查的物品
     * @return 是否已装备
     */
    public boolean contains(ItemInstance item) {
        if (item == null) {
            return false;
        }
        if (item.equals(mainHand) || item.equals(offHand) || item.equals(helmet)
                || item.equals(armor) || item.equals(pants) || item.equals(shoes)) {
            return true;
        }
        for (ItemInstance a : accessories) {
            if (item.equals(a)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 自动分配装备到对应槽位。
     * <ul>
     * <li>武器装备到主手</li>
     * <li>防具装备到头部（示例，可拓展分部位）</li>
     * <li>饰品自动分配到第一个空槽</li>
     * <li>如有多类型，则依次尝试</li>
     * </ul>
     *
     * @param item 物品实例
     */
    public void setEquip(ItemInstance selectedequip) {
        ItemInstance settedItem = null;
        ItemInstance oldItem = null;
        EnumSet<ItemType> types = selectedequip.getModel().getType();
        for (ItemType type : types) {
            switch (type) {
                case WEAPON -> {
                    if (mainHand != null) {
                        oldItem = getMainHand();
                    }
                    if (oldItem != null) {
                        if (selectedequip.getInstanceId() != oldItem.getInstanceId()) {
                            oldItem.setEquip(false);
                            setMainHand(selectedequip);

                            settedItem = selectedequip;
                        }
                    } else {
                        setMainHand(selectedequip);
                        settedItem = selectedequip;
                    }
                    break;
                }
                case ARMOR -> {
                    setHelmet(selectedequip); // 此处可根据防具细分类型进一步实现
                    break;
                }
                case ACCESSORY -> {
                    addAccessory(selectedequip);
                    break;
                }
                default -> {
                    // 跳过其它类型，继续尝试匹配
                }
            }
            if (!(settedItem == null)) {
                SimpleLogger.log.info("已装备" + settedItem.getName() + "[" + settedItem.getInstanceId() + "]");
            }
            break;
        }
        // if (!(settedItem == null)) {
        //     SimpleLogger.log.info("已装备" + settedItem.getName() + "[" + settedItem.getInstanceId() + "]");
        // }
        // break;

    }

}
