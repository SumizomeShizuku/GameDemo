package org.demo.factory;

import java.util.EnumMap;
import java.util.Map;

import org.demo.backpack.Backpack;
import org.demo.backpack.BackpackSlot;
import org.demo.backpack.Equipment;
import org.demo.backpack.ItemInstance;
import org.demo.dto.ItemModelDto;
import org.demo.dto.PlayerModelDto;
import org.demo.list.EquipmentAffix;
import org.demo.list.EquipmentAffix.Attribute;
import org.demo.list.EquipmentAffix.BonusType;
import org.demo.list.EthnicityList;
import org.demo.list.JobList;
import org.demo.util.LevelUpHandler;
import org.demo.util.SimpleLogger;

/**
 * 分离玩家数据与业务处理逻辑
 */
public class Player {

    private final PlayerModelDto model;

    public Player(PlayerModelDto model) {
        this.model = model;
    }

    /**
     * 获取玩家实际数据
     *
     * @return PlayerModelDto
     */
    public PlayerModelDto getModel() {
        return model;
    }

    /**
     * 获取玩家的姓
     *
     * @return
     */
    public String getFirstName() {
        return model.getFirstName();
    }

    /**
     * 获取玩家的名
     *
     * @return
     */
    public String getLastName() {
        return model.getLastName();
    }

    /**
     * 获取玩家的种族
     *
     * @return
     */
    public EthnicityList getEthnicity() {
        return model.getEthnicity();
    }

    /**
     * 获取玩家的职业
     *
     * @return
     */
    public JobList getJob() {
        return model.getJob();
    }

    /**
     * 获取玩家的经验
     *
     * @return
     */
    public int getExp() {
        return model.getExp();
    }

    /**
     * 获取玩家的等级
     *
     * @return
     */
    public int getLevel() {
        return model.getLevel();
    }

    /**
     * 获取玩家的最大生命值
     *
     * @return
     */
    public int getMaxHealthPoint() {
        return model.getMaxHealthPoint();
    }

    /**
     * 获取玩家的当前生命值
     *
     * @return
     */
    public int getCurrentHealthPoint() {
        return model.getCurrentHealthPoint();
    }

    /**
     * 获取玩家的最大魔法值
     *
     * @return
     */
    public int getMaxManaPoint() {
        return model.getMaxManaPoint();
    }

    /**
     * 获取玩家的当前魔法值
     *
     * @param currentManaPoint 当前魔法值
     */
    public int getCurrentManaPoint() {
        return model.getCurrentManaPoint();
    }

    /**
     * 获取玩家的力量
     *
     * @return
     */
    public int getStrength() {
        return model.getStrength();
    }

    /**
     * 获取玩家的敏捷
     *
     * @return
     */
    public int getAgility() {
        return model.getAgility();
    }

    /**
     * 获取玩家的智力
     *
     * @return
     */
    public int getIntelligence() {
        return model.getIntelligence();
    }

    /**
     * 获取玩家的防御力
     *
     * @return
     */
    public int getPhyDefense() {
        return model.getPhyDefense();
    }

    /**
     * 获取玩家的魔法防御力
     *
     * @return
     */
    public int getMagicDefense() {
        return model.getMagicDefense();
    }

    /**
     * 获取玩家的生命恢复
     *
     * @return
     */
    public double getRecoverHP() {
        return model.getRecoverHP();
    }

    /**
     * 获取玩家的魔法恢复
     *
     * @return
     */
    public double getRecoverMP() {
        return model.getRecoverMP();
    }

    /**
     * 获取玩家的基础冷却时间
     *
     * @return
     */
    public double getCommonCoolDown() {
        return model.getCommonCoolDown();
    }

    /**
     * 获取玩家的暴击率
     *
     * @return
     */
    public double getCriticalHitRate() {
        return model.getCriticalHitRate();
    }

    /**
     * 获取玩家的移动速度
     *
     * @return
     */
    public double getMoveSpeed() {
        return model.getMoveSpeed();
    }

    /**
     * 获取玩家背包
     *
     * @return
     */
    public Backpack getBackpack() {
        return model.getBackpack();
    }

    /**
     * 获取玩家装备
     *
     * @return
     */
    public Equipment getEquipment() {
        return model.getEquipment();
    }

    /**
     * 获取行动力
     *
     * @return
     */
    public int getActionsPerTurn() {
        return model.getActionsPerTurn();
    }

    /**
     * 玩家升级判定
     *
     * @param exp
     */
    public void gainExp(int exp) {
        LevelUpHandler.handleExpGain(model, exp);
    }

    /**
     * 玩家受伤判定
     *
     * @param damage 伤害量
     */
    public void takeDamage(int damage) {
        int hp = Math.max(0, model.getCurrentHealthPoint() - damage);
        model.setCurrentHealthPoint(hp);
    }

    /**
     * 判断玩家是否存活
     *
     * @return true:存活, false:死亡
     */
    public boolean isAlive() {
        return model.getCurrentHealthPoint() > 0;
    }

    /**
     * 玩家背包添加物品
     *
     * @param item 物品dto
     * @param count 数量
     */
    public void addItem(ItemModelDto item, int count) {
        model.getBackpack().addItem(item, count);
    }

    /**
     * 玩家背包添加物品
     *
     * @param instance 非叠加物品实例
     */
    public void addItem(ItemInstance instance) {
        model.getBackpack().addItem(instance);
    }

    /**
     * 玩家背包移除物品
     *
     * @param item 物品dto
     * @param count 数量
     * @return 是否移除成功
     */
    public boolean removeItemBySlot(int slotId, int count) {
        // 实际数组以0开始, 但背包以1开始
        // 故实际传入方法参与计算的 slotId 需要 -1
        slotId = slotId - 1;
        return model.getBackpack().removeItemBySlot(slotId, count);
    }

    /**
     * 玩家背包物品展示
     *
     * @param itemConfig
     */
    public void showInventory() {
        model.getBackpack().showInventory();
    }

    /**
     * 玩家装备物品展示
     *
     * @param itemConfig
     */
    public void showEquipment() {
        model.getEquipment().showEquipment();
    }

    /**
     * 玩家选择背包物品
     *
     * @param itemName 物品名
     */
    public BackpackSlot getSlot(int slotId) {
        // 实际数组以0开始, 但背包以1开始
        // 故实际传入方法参与计算的 slotId 需要 -1
        slotId = slotId - 1;
        return model.getBackpack().getSlot(slotId);
    }

    /**
     * 玩家装备道具
     */
    public boolean setEquip(String position, int slotId) {
        boolean isEquipable = false;
        // 已在 getSlot 方法中处理了slotId
        // 故此处无需 -1
        BackpackSlot bs = getSlot(slotId);
        if (bs == null) {
            return isEquipable;
        }
        if (bs.getInstance() != null) {
            ItemInstance item = bs.getInstance();
            if (model.getEquipment().setEquip(model, position, item)) {
                if (removeItemBySlot(slotId, 1)) {
                    isEquipable = true;
                }

            }

        }
        if (!isEquipable) {
            StringBuilder sb = new StringBuilder();
            sb.append("该物品不可装备! [");
            if (bs.getInstance() != null) {
                sb.append(bs.getInstance().getName());
                sb.append(", ").append(bs.getInstance().getInstanceId());
            } else if (bs.getItem() != null) {
                sb.append(bs.getItem().getName());
            }
            sb.append("]");
            SimpleLogger.log.warn(sb.toString());

        }
        refreshTotalAttributes();
        return isEquipable;
    }

    /**
     * 玩家脱下装备
     */
    public boolean putOffEquip(String position) {
        ItemInstance item = model.getEquipment().putOffEquip(position);
        if (item != null) {
            addItem(item);
            return true;
        }
        return false;
    }

    /**
     * 获取玩家自身( 不含装备 )所有属性。
     */
    public Map<Attribute, Double> getSelfAttributes() {
        Map<Attribute, Double> attrs = new EnumMap<>(Attribute.class);
        attrs.put(Attribute.STRENGTH, (double) getStrength());
        attrs.put(Attribute.AGILITY, (double) getAgility());
        attrs.put(Attribute.INTELLIGENCE, (double) getIntelligence());
        attrs.put(Attribute.MAX_HEALTH_POINT, (double) getMaxHealthPoint());
        attrs.put(Attribute.MAX_MANA_POINT, (double) getMaxManaPoint());
        attrs.put(Attribute.PHY_DEFENSE, (double) getPhyDefense());
        attrs.put(Attribute.MAGIC_DEFENSE, (double) getMagicDefense());
        attrs.put(Attribute.RECOVER_HP, getRecoverHP());
        attrs.put(Attribute.RECOVER_MP, getRecoverMP());
        attrs.put(Attribute.CRITICAL_HIT_RATE, getCriticalHitRate());
        attrs.put(Attribute.EVASION, 0.0); // 假设基础为0, 没有特殊字段
        attrs.put(Attribute.ACCURACY, 0.0); // 同上
        attrs.put(Attribute.LUCK, 0.0); // 如有getLuck()请用实际方法
        attrs.put(Attribute.FORTITUDE, 0.0);
        attrs.put(Attribute.BLEED_CHANCE, 0.0);
        attrs.put(Attribute.POISON_CHANCE, 0.0);
        attrs.put(Attribute.BURN_CHANCE, 0.0);
        attrs.put(Attribute.FREEZE_CHANCE, 0.0);
        attrs.put(Attribute.SPIRIT_POWER, 0.0);
        attrs.put(Attribute.DAMAGE_REDUCTION, 0.0);
        attrs.put(Attribute.HEALING_EFFECTIVENESS, 0.0);
        attrs.put(Attribute.GOLD_FIND, 0.0);
        // 如果你的PlayerModelDto有这些属性getter, 可补全
        return attrs;
    }

    /**
     * 获取玩家装备所有属性。
     */
    public Map<Attribute, Double> getAllEquipmentAttributes(BonusType type) {
        Map<Attribute, Double> result = new EnumMap<>(Attribute.class);

        if (type == BonusType.ADD) {
            // 加算型所有装备直接累加
            for (ItemInstance equip : model.getEquipment().getAllEquippedItems()) {
                if (equip == null) {
                    continue;
                }
                for (EquipmentAffix affix : equip.getAffixes()) {
                    Map<Attribute, Double> affixMap = affix.getAttributeMap(BonusType.ADD);
                    for (Map.Entry<Attribute, Double> entry : affixMap.entrySet()) {
                        result.merge(entry.getKey(), entry.getValue(), Double::sum);
                    }
                }
            }
        } else if (type == BonusType.MULTIPLY) {
            // 乘算型需要“连乘”
            for (Attribute attr : Attribute.values()) {
                result.put(attr, 1.0); // 初始为1，之后连乘
            }
            for (ItemInstance equip : model.getEquipment().getAllEquippedItems()) {
                if (equip == null) {
                    continue;
                }
                for (EquipmentAffix affix : equip.getAffixes()) {
                    Map<Attribute, Double> affixMap = affix.getAttributeMap(BonusType.MULTIPLY);
                    for (Map.Entry<Attribute, Double> entry : affixMap.entrySet()) {
                        Attribute attr = entry.getKey();
                        double value = entry.getValue();
                        result.merge(attr, 1 + value, (a, b) -> a * b);
                    }
                }
            }
            // 最终转成“总百分比”，即mul - 1
            for (Attribute attr : Attribute.values()) {
                double mul = result.get(attr);
                result.put(attr, mul - 1.0);
            }
        }
        return result;
    }

    public void refreshTotalAttributes() {
        // 1. 获取角色自身基础属性
        Map<Attribute, Double> self = getSelfAttributes();

        // 2. 获取装备的加算与乘算属性
        Map<Attribute, Double> equipAdd = getAllEquipmentAttributes(EquipmentAffix.BonusType.ADD);
        Map<Attribute, Double> equipMul = getAllEquipmentAttributes(EquipmentAffix.BonusType.MULTIPLY);
        // equipMul 每个值都是“所有装备词条对应属性的 (1+m1)*(1+m2)*...*(1+mn)-1”结果

        // 3. 合成最终属性
        Map<Attribute, Double> total = new EnumMap<>(Attribute.class);
        for (Attribute attr : EquipmentAffix.Attribute.values()) {
            double base = self.getOrDefault(attr, 0.0);
            double add = equipAdd.getOrDefault(attr, 0.0);
            double mul = equipMul.getOrDefault(attr, 0.0); // 实际为所有百分比连乘后的总百分比

            // 合成公式：(base + add) * (1 + mul)
            double result = (base + add) * (1.0 + mul);

            total.put(attr, result);
        }

        // 4. 写入到PlayerModelDto
        this.model.setStrength(total.getOrDefault(EquipmentAffix.Attribute.STRENGTH, (double) getStrength()).intValue());
        this.model.setAgility(total.getOrDefault(EquipmentAffix.Attribute.AGILITY, (double) getAgility()).intValue());
        this.model.setIntelligence(total.getOrDefault(EquipmentAffix.Attribute.INTELLIGENCE, (double) getIntelligence()).intValue());
        this.model.setMaxHealthPoint(total.getOrDefault(EquipmentAffix.Attribute.MAX_HEALTH_POINT, (double) getMaxHealthPoint()).intValue());
        this.model.setMaxManaPoint(total.getOrDefault(EquipmentAffix.Attribute.MAX_MANA_POINT, (double) getMaxManaPoint()).intValue());
        this.model.setPhyDefense(total.getOrDefault(EquipmentAffix.Attribute.PHY_DEFENSE, (double) getPhyDefense()).intValue());
        this.model.setMagicDefense(total.getOrDefault(EquipmentAffix.Attribute.MAGIC_DEFENSE, (double) getMagicDefense()).intValue());
        this.model.setRecoverHP(total.getOrDefault(EquipmentAffix.Attribute.RECOVER_HP, getRecoverHP()).intValue());
        this.model.setRecoverMP(total.getOrDefault(EquipmentAffix.Attribute.RECOVER_MP, getRecoverMP()).intValue());
        this.model.setCriticalHitRate(total.getOrDefault(EquipmentAffix.Attribute.CRITICAL_HIT_RATE, getCriticalHitRate()));
        // ... 其他属性同理，可按需追加
    }
}
