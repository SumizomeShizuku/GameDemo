package org.demo.backpack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.demo.dto.ItemModelDto;
import org.demo.list.EquipmentAffix;
import org.demo.list.ItemRarity;

/**
 * 玩家实际拥有的物品实例( 装备类 )。
 * <p>
 * 用于存储不可叠加物品及其附加属性, 如词条、附魔等。 每个实例具有唯一的实例ID( 与物品模板ID不同 ), 可以绑定附加属性, 并标识是否已装备。
 * </p>
 */
public class ItemInstance {

    /**
     * 唯一实例ID( 与物品模板ID不同 )。
     */
    private final String instanceId;

    /**
     * 物品模板对象, 包含物品的基本信息。
     */
    private final ItemModelDto model;

    // 物品稀有度
    private final ItemRarity rarity;
    // 物品装备词条
    private final List<EquipmentAffix> affixes;

    /**
     * 是否已装备。
     */
    private boolean isEquip;

    /**
     * 构造一个新的物品实例, 并生成唯一实例ID。
     *
     * @param model 物品模板对象
     */
    public ItemInstance(ItemModelDto model) {
        this.instanceId = UUID.randomUUID().toString();
        this.model = model;

        // 1. 随机抽取稀有度
        this.rarity = ItemRarity.randomRarity(new Random());
        // 2. 根据稀有度随机抽取词条
        int affixNum = getAffixCountByRarity(this.rarity);
        List<EquipmentAffix> temp = new ArrayList<>();
        for (int i = 0; i < affixNum; i++) {
            temp.add(EquipmentAffix.random(new Random()));
        }
        this.affixes = Collections.unmodifiableList(temp);
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
     * 判断该物品是否已装备。
     *
     * @return true 表示已装备, false 表示未装备
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
     * 获取物品的名称( 来自物品模板 )。
     *
     * @return 物品名称
     */
    public String getName() {
        return model.getName();
    }

    private int getAffixCountByRarity(ItemRarity rarity) {
        return switch (rarity) {
            case UNCOMMON ->
                1;
            case RARE ->
                2;
            case EPIC ->
                3;
            case LEGENDARY ->
                4;
            case MYTHIC ->
                5;
            default ->
                0;
        };
    }

    public ItemRarity getRarity() {
        return rarity;
    }

    public List<EquipmentAffix> getAffixes() {
        return affixes;
    }

    /**
     * 属性值格式化, 正数显示+号, %属性自动×100显示百分号
     */
    private String formatAffixAttribute(EquipmentAffix.Attribute attr, double val) {
        String zh = attr.getDisplayNameZh();
        String unit = attr.getUnit();
        boolean isPercent = "%".equals(unit);

        // 百分号属性自动乘100, 并保留1位小数
        String valueStr;
        if (isPercent) {
            valueStr = String.format("%+.1f%%", val * 100);
        } else if (val == (int) val) {
            valueStr = String.format("%+d", (int) val);
        } else {
            valueStr = String.format("%+.2f", val);
        }
        return zh + valueStr;
    }

    /**
     * 返回物品实例的字符串表示形式, 包含物品名、模板ID、附加属性( 如果有 )及唯一实例ID。
     *
     * @return 物品实例信息的字符串
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(model.getName());
        // if (isEquip)
        //     sb.append(" -已装备");
        sb.append(" [稀有度:").append(rarity.getDisplayNameZh());
        if (!affixes.isEmpty()) {
            sb.append(", 词条: ");
            for (EquipmentAffix affix : affixes) {
                sb.append(affix.getDisplayName()).append("( ");
                boolean first = true;
                for (Map.Entry<EquipmentAffix.Attribute, Double> entry : affix.getAttributeMap().entrySet()) {
                    if (!first) {
                        sb.append(", ");
                    }
                    EquipmentAffix.Attribute attr = entry.getKey();
                    double val = entry.getValue();
                    sb.append(formatAffixAttribute(attr, val));
                    first = false;
                }
                sb.append(" ) ");
            }
        }
        sb.append("] ID:").append(model.getId());
        // sb.append(", UUID:").append(instanceId).append("]");
        return sb.toString();
    }
}
