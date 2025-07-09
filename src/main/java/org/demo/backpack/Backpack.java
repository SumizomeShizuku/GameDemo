package org.demo.backpack;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.demo.dto.ItemModelDto;
import org.demo.list.ItemType;
import org.demo.util.SimpleLogger;

/**
 * 背包类: 管理玩家拥有的物品( 支持叠加与非叠加 )
 */
public class Backpack {

    private static final int CAPACITY = 40;
    private final List<BackpackSlot> slots = new ArrayList<>(CAPACITY);

    /**
     * 构造函数: 初始化背包格子
     */
    public Backpack() {
        // 预填充40个空格
        for (int i = 0; i < CAPACITY; i++) {
            slots.add(null);
        }
    }

    /**
     * 向背包中添加物品
     *
     * @param item 物品模板
     * @param count 添加数量
     * @return 成功添加后所在格子的编号( 下标 ), 失败返回-1
     */
    public int addItem(ItemModelDto item, int count, int luck) {
        if (isStackable(item)) {
            // 先查找可叠加的格子
            for (int i = 0; i < CAPACITY; i++) {
                BackpackSlot slot = slots.get(i);
                if (slot != null && slot.isStackable() && slot.getItem().equals(item)) {
                    slot.setCount(slot.getCount() + count);
                    SimpleLogger.log.info("获得物品: " + item.getName());
                    return i;
                }
            }
            // 没有找到, 查找空格子
            for (int i = 0; i < CAPACITY; i++) {
                if (slots.get(i) == null) {
                    slots.set(i, new BackpackSlot(item, count));
                    SimpleLogger.log.info("获得物品: " + item.getName());
                    return i;
                }
            }
        } else {
            count = 1;
            // 非叠加物品, 每个占一格
            for (int i = 0; i < CAPACITY && count > 0; i++) {
                if (slots.get(i) == null) {
                    ItemInstance instance = new ItemInstance(item, luck);
                    slots.set(i, new BackpackSlot(instance));
                    count--;
                    if (count == 0) {
                        SimpleLogger.log.info("获得物品: " + instance.getName() + " [ " + instance.getRarity() + " ]");
                        return i;
                    }
                }
            }
        }
        // 背包满
        return -1;
    }

    /**
     * 向背包中添加一个非叠加物品实例( 如装备类ItemInstance ) 用于装备卸下后放回背包
     *
     * @param instance 需要添加的物品实例
     * @return 成功添加后所在格子的编号( 下标 ), 失败返回-1
     */
    public int addItem(ItemInstance instance) {
        for (int i = 0; i < CAPACITY; i++) {
            if (slots.get(i) == null) {
                slots.set(i, new BackpackSlot(instance));
                return i;
            }
        }
        // 背包已满
        return -1;
    }

    /**
     * 移除背包中的物品( 支持叠加与非叠加 )
     * <p>
     * - 如果对应格子是叠加物品, 则移除指定数量( count )。<br>
     * 若移除后数量为0, 则格子置空。<br>
     * - 如果对应格子是非叠加物品, 则无视count, 直接清空格子。<br>
     *
     * @param slotId 格子编号( 下标 )
     * @param count 要移除的数量( 仅对可叠加物品有效 )
     * @return 是否移除成功
     */
    public boolean removeItemBySlot(int slotId, int count) {
        // slotId = slotId - 1;
        if (!isValidSlot(slotId)) {
            return false;
        }
        BackpackSlot slot = slots.get(slotId);
        if (slot == null) {
            return false;
        }
        if (slot.isStackable()) {
            if (slot.getCount() < count) {
                return false;
            }
            if (slot.getCount() == count) {
                slots.set(slotId, null);
            } else {
                slot.setCount(slot.getCount() - count);
            }
            return true;
        } else {
            // 非叠加物品, 直接移除
            slots.set(slotId, null);
            return true;
        }
    }

    /**
     * 获取指定格子的物品内容
     *
     * @param slotId 格子编号( 下标 )
     * @return 对应的BackpackSlot对象, 空格返回null
     */
    public BackpackSlot getSlot(int slotId) {
        // slotId = slotId - 1;
        if (!isValidSlot(slotId)) {
            SimpleLogger.log.warn("请选择背包范围内的物品 [ " + 0 + " - " + CAPACITY + ", 当前选择的栏位为 " + (slotId + 1) + " ]");
            return null;
        }
        if (slots.get(slotId) == null) {
            SimpleLogger.log.warn("当前物品栏为空, 当前选择的栏位为[ " + (slotId + 1) + " ]");
        }
        return slots.get(slotId);
    }

    /**
     * 检查格子编号是否合法
     *
     * @param slotId 格子编号( 下标 )
     * @return 合法返回true, 否则false
     */
    private boolean isValidSlot(int slotId) {
        // slotId = slotId - 1;
        return slotId >= 0 && slotId < CAPACITY;
    }

    /**
     * 展示背包内容到日志
     */
    public void showInventory() {
        String ln = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        sb.append("📦 背包内容: ").append(ln);
        int count = 0;
        for (int i = 0; i < CAPACITY; i++) {
            BackpackSlot slot = slots.get(i);

            if (slot == null) {
                count++;
            } else if (slot.isStackable()) {
                sb.append("格子[").append(i + 1).append("]: ");
                sb.append(slot.getItem().getName()).append(" x").append(slot.getCount()).append(" ").append(slot.getItem().getParameters()).append(ln);
            } else {
                sb.append("格子[").append(i + 1).append("]: ");
                sb.append(slot.getInstance().toString()).append(ln);
            }
        }
        sb.append("背包剩余容量: [ ").append(count).append(" / ").append(CAPACITY).append(" ]").append(ln);
        SimpleLogger.log.info(sb.toString());
    }

    /**
     * 判断物品是否为可叠加类型
     *
     * @param item 物品模板
     * @return true为可叠加物品, false为不可叠加
     */
    private boolean isStackable(ItemModelDto item) {
        EnumSet<ItemType> type = item.getType();
        return !(type.contains(ItemType.WEAPON) || type.contains(ItemType.ARMOR) || type.contains(ItemType.ACCESSORY));
    }
}
