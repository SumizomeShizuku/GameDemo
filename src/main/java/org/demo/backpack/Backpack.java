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

    /**
     * 添加物品
     */
    public void addItem(ItemModelDto itemModel, int count) {
        if (isStackable(itemModel)) {
            stackableItems.merge(itemModel, count, Integer::sum);
        } else {
            for (int i = 0; i < count; i++) {
                nonStackableItems.add(new ItemInstance(itemModel));
            }
        }
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
                if (it.next().getModel().getId().equals(itemModel.getId())) {
                    it.remove();
                    removed++;
                }
            }
            return removed == count;
        }
    }

    /**
     * 背包展示
     */
    // public void showInventory(Map<String, ItemModelDto> itemConfig) {
    public void showInventory() {
        String ln = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        sb.append(ln).append("📦 背包内容: ").append(ln);
        if (stackableItems.isEmpty() && nonStackableItems.isEmpty()) {
            sb.append("背包是空的。").append(ln);
            SimpleLogger.log.info(sb.toString());
            return;
        }
        for (Map.Entry<ItemModelDto, Integer> entry : stackableItems.entrySet()) {
            // String itemId = entry.getKey().getId();
            ItemModelDto item = entry.getKey();
            sb.append(" - ").append(item.getName()).append(" x").append(entry.getValue()).append(ln);
        }

        for (ItemInstance item : nonStackableItems) {
            sb.append(" - ").append(item.toString()).append(ln);
        }

        SimpleLogger.log.info(sb.toString());

    }

    private boolean isStackable(ItemModelDto item) {
        EnumSet<ItemType> type = item.getType();
        // String type = item.getType().toLowerCase();
        return !(type.contains(ItemType.WEAPON) || type.contains(ItemType.ARMOR) || type.contains(ItemType.ACCESSORY));
    }

    @Override
    public String toString() {
        return "Backpack [stackableItems=" + stackableItems + ", nonStackableItems=" + nonStackableItems + "]";
    }

}
