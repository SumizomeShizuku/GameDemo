package org.demo.backpack;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.demo.dto.ItemModelDto;
import org.demo.list.ItemType;
import org.demo.util.SimpleLogger;

/**
 * 背包类: 管理玩家拥有的物品( 支持叠加与非叠加 )
 */
public class Backpack {

    private final Map<ItemModelDto, Integer> stackableItems = new HashMap<>(); // itemId -> count
    private final List<ItemInstance> nonStackableItems = new ArrayList<>();

    private final Map<Long, ItemInstance> idToNonStackableItem = new HashMap<>();

    /**
     * 添加物品
     */
    public List<Long> addItem(ItemModelDto itemModel, int count) {
        List<Long> ids = new ArrayList<>();
        if (isStackable(itemModel)) {
            stackableItems.merge(itemModel, count, Integer::sum);
        } else {
            for (int i = 0; i < count; i++) {
                // nonStackableItems.add(new ItemInstance(itemModel));
                ItemInstance inst = new ItemInstance(itemModel);
                nonStackableItems.add(inst);
                idToNonStackableItem.put(inst.getInstanceId(), inst);
                ids.add(inst.getInstanceId());
            }
        }
        return ids;
    }

    /**
     * 移除物品, 返回是否移除成功
     */
    public boolean removeItem(ItemModelDto itemModel, int count) {
        if (isStackable(itemModel)) {
            int current = stackableItems.getOrDefault(itemModel.getId(), 0);
            if (current < count) {
                return false;
            }
            if (current == count) {
                stackableItems.remove(itemModel);
            } else {
                stackableItems.put(itemModel, current - count);
            }
            return true;
        } else {
            int removed = 0;
            Iterator<ItemInstance> it = nonStackableItems.iterator();
            while (it.hasNext() && removed < count) {
                ItemInstance inst = it.next();
                if (inst.getModel().getId().equals(itemModel.getId())) {
                    idToNonStackableItem.remove(inst.getInstanceId());
                    it.remove();
                    removed++;
                }
            }
            return removed == count;
        }
    }

    /**
     * 根据物品编号移除非叠加物品
     */
    public boolean removeItemById(long itemInstanceId) {
        ItemInstance inst = idToNonStackableItem.remove(itemInstanceId);
        if (inst != null) {
            nonStackableItems.remove(inst);
            return true;
        }
        return false;
    }

    /**
     * 背包展示
     */
    public void showInventory() {
        String ln = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        sb.append(ln).append("📦 背包内容: ").append(ln);
        if (stackableItems.isEmpty() && nonStackableItems.isEmpty()) {
            sb.append("背包是空的。").append(ln);
            SimpleLogger.log.info(sb.toString());
            return;
        }
        sb.append("道具").append(ln);
        for (Map.Entry<ItemModelDto, Integer> entry : stackableItems.entrySet()) {
            // String itemId = entry.getKey().getId();
            ItemModelDto item = entry.getKey();
            sb.append(" - ").append(item.getName()).append(" x").append(entry.getValue()).append(ln);
        }

        sb.append(ln).append("装备").append(ln);
        for (ItemInstance item : nonStackableItems) {
            sb.append(" - ").append(item.toString());
            if (item.isEquip()) {
                sb.append(" - 已装备").append(ln);
            } else {
                sb.append(ln);
            }

        }

        SimpleLogger.log.info(sb.toString());

    }

    /**
     * 通过名称查找物品
     */
    public List<ItemInstance> selectItem(String itemName) {
        List<ItemInstance> items = new ArrayList<>();
        for (ItemInstance item : nonStackableItems) {
            if (item.getName().contains(itemName)) {
                items.add(item);
            }
        }
        return items;
    }

    /**
     * 通过编号查找物品
     */
    public ItemInstance selectItemById(long itemInstanceId) {
        return idToNonStackableItem.get(itemInstanceId);
    }

    private boolean isStackable(ItemModelDto item) {
        EnumSet<ItemType> type = item.getType();
        return !(type.contains(ItemType.WEAPON) || type.contains(ItemType.ARMOR) || type.contains(ItemType.ACCESSORY));
    }

    @Override
    public String toString() {
        return "Backpack [stackableItems=" + stackableItems + ", nonStackableItems=" + nonStackableItems + "]";
    }

}
