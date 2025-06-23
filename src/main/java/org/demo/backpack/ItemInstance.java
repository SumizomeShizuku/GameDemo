package org.demo.backpack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.demo.dto.ItemModelDto;

/**
 * 玩家实际拥有的物品实例( 装备类 )。
 * <p>
 * 用于存储不可叠加物品及其附加属性，如词条、附魔等。 每个实例具有唯一的实例ID( 与物品模板ID不同 )，可以绑定附加属性，并标识是否已装备。
 * </p>
 */
public class ItemInstance {

    /**
     * 唯一实例ID( 与物品模板ID不同 )。
     */
    private final String instanceId;

    /**
     * 物品模板对象，包含物品的基本信息。
     */
    private final ItemModelDto model;

    /**
     * 物品附加属性( 如词条、附魔等，当前未实装 )。
     */
    private final Map<String, Object> attributes = new HashMap<>();

    /**
     * 是否已装备。
     */
    private boolean isEquip;

    /**
     * 构造一个新的物品实例，并生成唯一实例ID。
     *
     * @param model 物品模板对象
     */
    public ItemInstance(ItemModelDto model) {
        this.instanceId = UUID.randomUUID().toString();
        this.model = model;
    }

    /**
     * 获取该物品实例的唯一ID。
     *
     * @return 实例ID( UUID )
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * 获取物品模板对象。
     *
     * @return {@link ItemModelDto} 物品模板
     */
    public ItemModelDto getModel() {
        return model;
    }

    /**
     * 获取物品的附加属性集合。
     *
     * @return 属性Map
     */
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * 判断该物品是否已装备。
     *
     * @return true 表示已装备，false 表示未装备
     */
    public boolean isEquip() {
        return isEquip;
    }

    /**
     * 设置该物品是否装备状态。
     *
     * @param isEquip 是否装备
     */
    public void isEquip(boolean isEquip) {
        this.isEquip = isEquip;
    }

    /**
     * 添加或修改一个附加属性。
     *
     * @param key 属性名
     * @param value 属性值
     */
    public void addAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    /**
     * 获取物品的名称( 来自物品模板 )。
     *
     * @return 物品名称
     */
    public String getName() {
        return model.getName();
    }

    /**
     * 返回物品实例的字符串表示形式，包含物品名、模板ID、附加属性( 如果有 )及唯一实例ID。
     *
     * @return 物品实例信息的字符串
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(model.getName());
        if (isEquip) {
            sb.append(" -已装备");
        }
        if (attributes.isEmpty()) {
            sb.append(" [ID:").append(model.getId()).append(", UUID:").append(instanceId)
                    .append("]");
        } else {
            sb.append(" [ID:").append(model.getId()).append(", 属性:").append(attributes)
                    .append(", UUID:").append(instanceId).append("]");
        }

        return sb.toString();
    }
}
