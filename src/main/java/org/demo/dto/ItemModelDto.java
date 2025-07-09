package org.demo.dto;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.demo.list.ItemType;

import com.fasterxml.jackson.annotation.JsonAnySetter;

/**
 * 该类用于表示游戏或应用中的物品信息, 包括ID、名称、类型、描述和价格等字段。
 * </p>
 *
 */
public class ItemModelDto {

    /**
     * 物品ID
     */
    private String id;

    /**
     * 物品名称
     */
    private String name;

    /**
     * 物品描述
     */
    private String description;

    /**
     * 物品价格
     */
    private int price;

    /**
     * 物品类型
     */
    private EnumSet<ItemType> type;

    /**
     * 物品所在道具池
     */
    private List<String> dropGroup;

    // 动态参数
    private final Map<String, Object> parameters = new HashMap<>();

    public ItemModelDto() {
    }

    /**
     * 构造一个新的物品DTO实例。
     *
     * @param id 物品ID
     * @param name 物品名称
     * @param type 物品类型
     * @param description 物品描述
     * @param price 物品价格
     */
    public ItemModelDto(String id, String name, String description, int price, EnumSet<ItemType> type,
            List<String> dropGroup) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
        this.dropGroup = dropGroup;
    }

    /**
     * 获取物品ID。
     *
     * @return 物品ID
     */
    public String getId() {
        return id;
    }

    /**
     * 获取物品名称。
     *
     * @return 物品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取物品类型。
     *
     * @return 物品类型
     */
    public EnumSet<ItemType> getType() {
        return type;
    }

    /**
     * 获取物品描述。
     *
     * @return 物品描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 获取物品价格。
     *
     * @return 物品价格
     */
    public int getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setType(EnumSet<ItemType> type) {
        this.type = type;
    }

    public List<String> getDropGroup() {
        return dropGroup;
    }

    public void setDropGroup(List<String> dropGroup) {
        this.dropGroup = dropGroup;
    }

    // 新增：动态属性Map的getter
    public Map<String, Object> getParameters() {
        return parameters;
    }

    // 新增：@JsonAnySetter 让Jackson自动把未知字段放进parameters
    @JsonAnySetter
    public void addParameter(String key, Object value) {
        this.parameters.put(key, value);
    }

    // 获取动态属性的便捷方法（带类型转换）
    // HP恢复属性
    public Integer getRestoreHp() {
        Object v = parameters.get("restoreHp");
        if (v instanceof Integer integer) {
            return integer;
        }
        if (v instanceof Number number) {
            return number.intValue();
        }
        if (v != null) {
            return Integer.valueOf(v.toString());
        }
        return null;
    }

    // MP恢复属性
    public Integer getRestoreMp() {
        Object v = parameters.get("restoreMp");
        if (v instanceof Integer integer) {
            return integer;
        }
        if (v instanceof Number number) {
            return number.intValue();
        }
        if (v != null) {
            return Integer.valueOf(v.toString());
        }
        return null;
    }

    // 道具释放技能
    public String getSkillId() {
        Object v = parameters.get("skillId");
        if (v != null) {
            return v.toString();
        }
        return null;
    }

    // 玩家学习新技能
    public String getLearnSkillId() {
        Object v = parameters.get("learnSkill");
        if (v != null) {
            return v.toString();
        }
        return null;
    }

    /**
     * 返回物品的字符串表示形式。
     *
     * @return 字符串, 包含物品的主要属性信息
     */
    @Override
    public String toString() {
        return String.format("[%s] %s: %s 类型:%s, 价格:%d, 参数:%s",
                id, name, description, type, price, parameters);
    }
}
