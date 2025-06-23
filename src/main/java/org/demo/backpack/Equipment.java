package org.demo.backpack;

import java.util.ArrayList;
import java.util.List;

import org.demo.dto.PlayerModelDto;
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
        mainHand.isEquip(true);
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
        offHand.isEquip(true);
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
        helmet.isEquip(true);
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
        armor.isEquip(true);
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
        pants.isEquip(true);
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
        shoes.isEquip(true);
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
                accessories[i].isEquip(true);
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
            accessories[slot].isEquip(false);
            accessories[slot] = accessory;
            accessories[slot].isEquip(true);
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
    public void setEquip(PlayerModelDto model, String position, ItemInstance selectedequip) {
        ItemInstance oldInstance;
        switch (position) {
            case "mainHand" -> {
                if (mainHand != null) {
                    oldInstance = putOffEquip("mainHand");
                    setMainHand(selectedequip);
                    model.getBackpack().addItem(oldInstance);
                } else {
                    setMainHand(selectedequip);
                }
            }
            case "offHand" -> {
                if (offHand != null) {
                    oldInstance = putOffEquip("offHand");
                    setOffHand(selectedequip);
                    model.getBackpack().addItem(oldInstance);
                } else {
                    setOffHand(selectedequip);
                }
            }
            case "helmet" -> {
                if (helmet != null) {
                    oldInstance = putOffEquip("helmet");
                    setHelmet(selectedequip);
                    model.getBackpack().addItem(oldInstance);
                } else {
                    setHelmet(selectedequip);
                }
            }
            case "armor" -> {
                if (armor != null) {
                    oldInstance = putOffEquip("armor");
                    setArmor(selectedequip);
                    model.getBackpack().addItem(oldInstance);
                } else {
                    setArmor(selectedequip);
                }
            }
            case "pants" -> {
                if (pants != null) {
                    oldInstance = putOffEquip("pants");
                    setPants(selectedequip);
                    model.getBackpack().addItem(oldInstance);
                } else {
                    setPants(selectedequip);
                }
            }
            case "shoes" -> {
                if (shoes != null) {
                    oldInstance = putOffEquip("shoes");
                    setShoes(selectedequip);
                    model.getBackpack().addItem(oldInstance);
                } else {
                    setShoes(selectedequip);
                }
            }
            case "accessory" -> {
                addAccessory(selectedequip);
            }
            case "accessory1" -> {
                if (accessories[0] != null) {
                    oldInstance = putOffEquip("accessory1");
                    accessories[0] = selectedequip;
                    model.getBackpack().addItem(oldInstance);
                } else {
                    accessories[0] = selectedequip;
                }
            }
            case "accessory2" -> {
                if (accessories[1] != null) {
                    oldInstance = putOffEquip("accessory2");
                    accessories[1] = selectedequip;
                    model.getBackpack().addItem(oldInstance);
                } else {
                    accessories[1] = selectedequip;
                }
            }
            case "accessory3" -> {
                if (accessories[2] != null) {
                    oldInstance = putOffEquip("accessory3");
                    accessories[2] = selectedequip;
                    model.getBackpack().addItem(oldInstance);
                } else {
                    accessories[2] = selectedequip;
                }
            }
            case "accessory4" -> {
                if (accessories[3] != null) {
                    oldInstance = putOffEquip("accessory4");
                    accessories[3] = selectedequip;
                    model.getBackpack().addItem(oldInstance);
                } else {
                    accessories[3] = selectedequip;
                }
            }
            default -> {
                SimpleLogger.log.info("部位名称错误: " + position);

            }
        }
        SimpleLogger.log.info("已装备" + selectedequip.getName() + "[" + selectedequip.getInstanceId() + "]");

    }

    /**
     * 脱下指定装备（通过部位名字符串），返回脱下的物品。
     *
     * @param position
     * 要脱下的部位名称（如"mainHand","offHand","helmet","armor","pants","shoes","accessory0"-"accessory3"）
     * @return 脱下的物品实例，如果该位置为空或输入非法，返回null
     */
    public ItemInstance putOffEquip(String position) {
        if (position == null) {
            SimpleLogger.log.info("部位不能为空");
            return null;
        }
        ItemInstance removed = null;
        switch (position) {
            case "mainHand" -> {
                if (mainHand != null) {
                    removed = mainHand;
                    removed.isEquip(false);
                    mainHand = null;
                }
            }
            case "offHand" -> {
                if (offHand != null) {
                    removed = offHand;
                    removed.isEquip(false);
                    offHand = null;
                }
            }
            case "helmet" -> {
                if (helmet != null) {
                    removed = helmet;
                    removed.isEquip(false);
                    helmet = null;
                }
            }
            case "armor" -> {
                if (armor != null) {
                    removed = armor;
                    removed.isEquip(false);
                    armor = null;
                }
            }
            case "pants" -> {
                if (pants != null) {
                    removed = pants;
                    removed.isEquip(false);
                    pants = null;
                }
            }
            case "shoes" -> {
                if (shoes != null) {
                    removed = shoes;
                    removed.isEquip(false);
                    shoes = null;
                }
            }
            case "accessory1" -> {
                if (accessories[0] != null) {
                    removed = accessories[0];
                    removed.isEquip(false);
                    accessories[0] = null;
                }
            }
            case "accessory2" -> {
                if (accessories[1] != null) {
                    removed = accessories[1];
                    removed.isEquip(false);
                    accessories[1] = null;
                }
            }
            case "accessory3" -> {
                if (accessories[2] != null) {
                    removed = accessories[2];
                    removed.isEquip(false);
                    accessories[2] = null;
                }
            }
            case "accessory4" -> {
                if (accessories[3] != null) {
                    removed = accessories[3];
                    removed.isEquip(false);
                    accessories[3] = null;
                }
            }
            default -> {
                SimpleLogger.log.info("部位名称错误: " + position);
                return null;
            }
        }
        if (removed != null) {
            SimpleLogger.log.info("已脱下装备: " + removed.getName() + " [" + removed.getInstanceId() + "]");
        } else {
            SimpleLogger.log.info("该位置没有装备可脱下: " + position);
        }
        return removed;
    }

    /**
     * 展示背包内容到日志
     */
    public void showEquipment() {
        SimpleLogger.log.info(toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String ln = System.lineSeparator();

        sb.append("【装备栏状态】\n");
        sb.append("主手: ").append(mainHand != null ? mainHand.getName() + " [" + mainHand.getInstanceId() + "]" : "空")
                .append("\n");
        sb.append("副手: ").append(offHand != null ? offHand.getName() + " [" + offHand.getInstanceId() + "]" : "空")
                .append("\n");
        sb.append("头部: ").append(helmet != null ? helmet.getName() + " [" + helmet.getInstanceId() + "]" : "空")
                .append("\n");
        sb.append("上衣: ").append(armor != null ? armor.getName() + " [" + armor.getInstanceId() + "]" : "空")
                .append("\n");
        sb.append("裤子: ").append(pants != null ? pants.getName() + " [" + pants.getInstanceId() + "]" : "空")
                .append("\n");
        sb.append("鞋子: ").append(shoes != null ? shoes.getName() + " [" + shoes.getInstanceId() + "]" : "空")
                .append("\n");
        sb.append("饰品列表: ").append(ln);
        boolean hasAccessory = false;
        for (int i = 0; i < accessories.length; i++) {
            if (accessories[i] != null) {
                if (hasAccessory) {
                    sb.append(ln);
                }
                sb.append("[").append(i + 1).append("] ")
                        .append(accessories[i].getName())
                        .append(" [").append(accessories[i].getInstanceId()).append("]");
                hasAccessory = true;
            }
        }
        if (!hasAccessory) {
            sb.append("空");
        }
        return sb.toString();
    }

}
