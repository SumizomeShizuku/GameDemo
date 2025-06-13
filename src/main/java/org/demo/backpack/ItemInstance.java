package org.demo.backpack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.demo.dto.ItemModelDto;

/**
 * 玩家实际拥有的物品实例（装备类），用于存储不可叠加物品及其附加属性。
 */
public class ItemInstance {

    private final String instanceId; // 唯一实例ID（不同于 itemModel.id）
    private final String itemId; // 物品ID（对应于 ItemModelDto 的 id）
    private final ItemModelDto model; // 配置模板
    private final Map<String, Object> attributes = new HashMap<>(); // 词条、附魔等属性

    public ItemInstance(ItemModelDto model) {
        this.instanceId = UUID.randomUUID().toString();
        this.itemId = model.getId();
        this.model = model;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public String getItemId() {
        return itemId;
    }

    public ItemModelDto getModel() {
        return model;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void addAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    @Override
    public String toString() {
        if (attributes.isEmpty()) {
            return model.getName() + " [ID:" + itemId + "]";
        }
        return model.getName() + " [ID:" + itemId + ", 属性:" + attributes + "]";
    }
}
