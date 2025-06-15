package org.demo.backpack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.demo.dto.ItemModelDto;
import org.demo.util.SimpleLogger;

/**
 * 背包类：管理玩家拥有的物品（支持叠加与非叠加）
 */
public class Backpack {

    private final Map<String, Integer> stackableItems = new HashMap<>(); // itemId -> count
    private final List<ItemInstance> nonStackableItems = new ArrayList<>();

    /**
     * 添加物品
     */
    public void addItem(ItemModelDto itemModel, int count) {
        if (isStackable(itemModel)) {
            stackableItems.merge(itemModel.getId(), count, Integer::sum);
        } else {
            for (int i = 0; i < count; i++) {
                nonStackableItems.add(new ItemInstance(itemModel));
            }
        }
    }

    /**
     * 移除物品
     */
    public boolean removeItem(ItemModelDto itemModel, int count) {
        if (isStackable(itemModel)) {
            int current = stackableItems.getOrDefault(itemModel.getId(), 0);
            if (current < count) {
                return false;
            }
            if (current == count) {
                stackableItems.remove(itemModel.getId());
            } else {
                stackableItems.put(itemModel.getId(), current - count);
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
    public String showInventory(Map<String, ItemModelDto> itemConfig) {
        SimpleLogger.log.info("📦 背包内容：");
        String ln = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : stackableItems.entrySet()) {
            String itemId = entry.getKey();
            ItemModelDto item = itemConfig.get(itemId);
            sb.append(" - ").append(item.getName()).append(" x").append(entry.getValue()).append(ln);
        }

        for (ItemInstance item : nonStackableItems) {
            SimpleLogger.log.info(" - " + item.toString());
            sb.append(" - ").append(item.toString()).append(ln);
        }

        return sb.toString();
    }

    private boolean isStackable(ItemModelDto item) {
        String type = item.getType().toLowerCase();
        return !(type.equals("weapon") || type.equals("armor") || type.equals("accessory"));
    }
}
