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
import org.demo.list.ExpList;
import org.demo.list.JobList;
import org.demo.util.LevelUpHandler;
import org.demo.util.SimpleLogger;

/**
 * 分离玩家数据与业务处理逻辑
 */
public class Player {

    // 力量
    private int strength;
    // 敏捷
    private int agility;
    // 智力
    private int intelligence;
    // 最大生命
    private int maxHealthPoint;
    // 当前HP
    private int currentHealthPoint;
    // 最大魔法
    private int maxManaPoint;
    // 当前MP
    private int currentManaPoint;
    // 物理防御
    private int phyDefense;
    // 魔法防御
    private int magicDefense;
    // 生命恢复
    private double recoverHP;
    // 魔法恢复
    private double recoverMP;
    // 暴击率
    private double criticalHitRate;
    // 闪避
    private double evasion;
    // 命中
    private double accuracy;
    // 幸运
    private int luck;
    // 坚韧
    private int fortitude;
    // 出血几率
    private double bleedChance;
    // 中毒几率
    private double poisonChance;
    // 灼烧几率
    private double burnChance;
    // 冰冻几率
    private double freezeChance;
    // 灵力
    private int spiritPower;
    // 伤害减免
    private double damageReduction;
    // 治疗效果
    private double healingEffectiveness;
    // 金币获取
    private double goldFind;

    // 玩家背包
    private Backpack backpack;
    // 玩家装备
    private Equipment equipment;

    private final PlayerModelDto model;

    public Player(PlayerModelDto model) {
        this.model = model;
    }

    public void syncBaseAttributesFromModel() {
        this.strength = model.getStrength();
        this.agility = model.getAgility();
        this.intelligence = model.getIntelligence();
        this.maxHealthPoint = model.getMaxHealthPoint();
        this.currentHealthPoint = model.getCurrentHealthPoint();
        this.maxManaPoint = model.getMaxManaPoint();
        this.currentManaPoint = model.getCurrentManaPoint();
        this.phyDefense = model.getPhyDefense();
        this.magicDefense = model.getMagicDefense();
        this.recoverHP = model.getRecoverHP();
        this.recoverMP = model.getRecoverMP();
        this.criticalHitRate = model.getCriticalHitRate();
        refreshTotalAttributes();
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

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getMaxHealthPoint() {
        return maxHealthPoint;
    }

    public void setMaxHealthPoint(int maxHealthPoint) {
        this.maxHealthPoint = maxHealthPoint;
    }

    public int getCurrentHealthPoint() {
        return currentHealthPoint;
    }

    public void setCurrentHealthPoint(int currentHealthPoint) {
        this.currentHealthPoint = currentHealthPoint;
    }

    public int getMaxManaPoint() {
        return maxManaPoint;
    }

    public void setMaxManaPoint(int maxManaPoint) {
        this.maxManaPoint = maxManaPoint;
    }

    public int getCurrentManaPoint() {
        return currentManaPoint;
    }

    public void setCurrentManaPoint(int currentManaPoint) {
        this.currentManaPoint = currentManaPoint;
    }

    public int getPhyDefense() {
        return phyDefense;
    }

    public void setPhyDefense(int phyDefense) {
        this.phyDefense = phyDefense;
    }

    public int getMagicDefense() {
        return magicDefense;
    }

    public void setMagicDefense(int magicDefense) {
        this.magicDefense = magicDefense;
    }

    public double getRecoverHP() {
        return recoverHP;
    }

    public void setRecoverHP(double recoverHP) {
        this.recoverHP = recoverHP;
    }

    public double getRecoverMP() {
        return recoverMP;
    }

    public void setRecoverMP(double recoverMP) {
        this.recoverMP = recoverMP;
    }

    public double getCriticalHitRate() {
        return criticalHitRate;
    }

    public void setCriticalHitRate(double criticalHitRate) {
        this.criticalHitRate = criticalHitRate;
    }

    public double getEvasion() {
        return evasion;
    }

    public void setEvasion(double evasion) {
        this.evasion = evasion;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public int getFortitude() {
        return fortitude;
    }

    public void setFortitude(int fortitude) {
        this.fortitude = fortitude;
    }

    public double getBleedChance() {
        return bleedChance;
    }

    public void setBleedChance(double bleedChance) {
        this.bleedChance = bleedChance;
    }

    public double getPoisonChance() {
        return poisonChance;
    }

    public void setPoisonChance(double poisonChance) {
        this.poisonChance = poisonChance;
    }

    public double getBurnChance() {
        return burnChance;
    }

    public void setBurnChance(double burnChance) {
        this.burnChance = burnChance;
    }

    public double getFreezeChance() {
        return freezeChance;
    }

    public void setFreezeChance(double freezeChance) {
        this.freezeChance = freezeChance;
    }

    public int getSpiritPower() {
        return spiritPower;
    }

    public void setSpiritPower(int spiritPower) {
        this.spiritPower = spiritPower;
    }

    public double getDamageReduction() {
        return damageReduction;
    }

    public void setDamageReduction(double damageReduction) {
        this.damageReduction = damageReduction;
    }

    public double getHealingEffectiveness() {
        return healingEffectiveness;
    }

    public void setHealingEffectiveness(double healingEffectiveness) {
        this.healingEffectiveness = healingEffectiveness;
    }

    public double getGoldFind() {
        return goldFind;
    }

    public void setGoldFind(double goldFind) {
        this.goldFind = goldFind;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    /**
     * 玩家升级判定
     *
     * @param exp
     */
    public void gainExp(int exp) {
        LevelUpHandler.handleExpGain(this, exp);
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
        attrs.put(Attribute.STRENGTH, (double) model.getStrength());
        attrs.put(Attribute.AGILITY, (double) model.getAgility());
        attrs.put(Attribute.INTELLIGENCE, (double) model.getIntelligence());
        attrs.put(Attribute.MAX_HEALTH_POINT, (double) model.getMaxHealthPoint());
        attrs.put(Attribute.MAX_MANA_POINT, (double) model.getMaxManaPoint());
        attrs.put(Attribute.PHY_DEFENSE, (double) model.getPhyDefense());
        attrs.put(Attribute.MAGIC_DEFENSE, (double) model.getMagicDefense());
        attrs.put(Attribute.RECOVER_HP, model.getRecoverHP());
        attrs.put(Attribute.RECOVER_MP, model.getRecoverMP());
        attrs.put(Attribute.CRITICAL_HIT_RATE, model.getCriticalHitRate());
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
        // 1. 读取基础属性
        Map<Attribute, Double> baseAttrs = getSelfAttributes();

        // 2. 获取装备的加算和乘算属性
        Map<Attribute, Double> equipAdd = getAllEquipmentAttributes(EquipmentAffix.BonusType.ADD);
        Map<Attribute, Double> equipMul = getAllEquipmentAttributes(EquipmentAffix.BonusType.MULTIPLY);

        // 3. 合成并赋值到字段
        this.strength = (int) ((baseAttrs.getOrDefault(Attribute.STRENGTH, 0.0)
                + equipAdd.getOrDefault(Attribute.STRENGTH, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.STRENGTH, 0.0)));

        this.agility = (int) ((baseAttrs.getOrDefault(Attribute.AGILITY, 0.0)
                + equipAdd.getOrDefault(Attribute.AGILITY, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.AGILITY, 0.0)));

        this.intelligence = (int) ((baseAttrs.getOrDefault(Attribute.INTELLIGENCE, 0.0)
                + equipAdd.getOrDefault(Attribute.INTELLIGENCE, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.INTELLIGENCE, 0.0)));

        this.maxHealthPoint = (int) ((baseAttrs.getOrDefault(Attribute.MAX_HEALTH_POINT, 0.0)
                + equipAdd.getOrDefault(Attribute.MAX_HEALTH_POINT, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.MAX_HEALTH_POINT, 0.0)));

        this.maxManaPoint = (int) ((baseAttrs.getOrDefault(Attribute.MAX_MANA_POINT, 0.0)
                + equipAdd.getOrDefault(Attribute.MAX_MANA_POINT, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.MAX_MANA_POINT, 0.0)));

        this.phyDefense = (int) ((baseAttrs.getOrDefault(Attribute.PHY_DEFENSE, 0.0)
                + equipAdd.getOrDefault(Attribute.PHY_DEFENSE, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.PHY_DEFENSE, 0.0)));

        this.magicDefense = (int) ((baseAttrs.getOrDefault(Attribute.MAGIC_DEFENSE, 0.0)
                + equipAdd.getOrDefault(Attribute.MAGIC_DEFENSE, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.MAGIC_DEFENSE, 0.0)));

        this.recoverHP = (baseAttrs.getOrDefault(Attribute.RECOVER_HP, 0.0)
                + equipAdd.getOrDefault(Attribute.RECOVER_HP, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.RECOVER_HP, 0.0));

        this.recoverMP = (baseAttrs.getOrDefault(Attribute.RECOVER_MP, 0.0)
                + equipAdd.getOrDefault(Attribute.RECOVER_MP, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.RECOVER_MP, 0.0));

        this.criticalHitRate = (baseAttrs.getOrDefault(Attribute.CRITICAL_HIT_RATE, 0.0)
                + equipAdd.getOrDefault(Attribute.CRITICAL_HIT_RATE, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.CRITICAL_HIT_RATE, 0.0));

        this.evasion = (baseAttrs.getOrDefault(Attribute.EVASION, 0.0)
                + equipAdd.getOrDefault(Attribute.EVASION, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.EVASION, 0.0));

        this.accuracy = (baseAttrs.getOrDefault(Attribute.ACCURACY, 0.0)
                + equipAdd.getOrDefault(Attribute.ACCURACY, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.ACCURACY, 0.0));

        this.luck = (int) ((baseAttrs.getOrDefault(Attribute.LUCK, 0.0)
                + equipAdd.getOrDefault(Attribute.LUCK, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.LUCK, 0.0)));

        this.fortitude = (int) ((baseAttrs.getOrDefault(Attribute.FORTITUDE, 0.0)
                + equipAdd.getOrDefault(Attribute.FORTITUDE, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.FORTITUDE, 0.0)));

        this.bleedChance = (baseAttrs.getOrDefault(Attribute.BLEED_CHANCE, 0.0)
                + equipAdd.getOrDefault(Attribute.BLEED_CHANCE, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.BLEED_CHANCE, 0.0));

        this.poisonChance = (baseAttrs.getOrDefault(Attribute.POISON_CHANCE, 0.0)
                + equipAdd.getOrDefault(Attribute.POISON_CHANCE, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.POISON_CHANCE, 0.0));

        this.burnChance = (baseAttrs.getOrDefault(Attribute.BURN_CHANCE, 0.0)
                + equipAdd.getOrDefault(Attribute.BURN_CHANCE, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.BURN_CHANCE, 0.0));

        this.freezeChance = (baseAttrs.getOrDefault(Attribute.FREEZE_CHANCE, 0.0)
                + equipAdd.getOrDefault(Attribute.FREEZE_CHANCE, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.FREEZE_CHANCE, 0.0));

        this.spiritPower = (int) ((baseAttrs.getOrDefault(Attribute.SPIRIT_POWER, 0.0)
                + equipAdd.getOrDefault(Attribute.SPIRIT_POWER, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.SPIRIT_POWER, 0.0)));

        this.damageReduction = (baseAttrs.getOrDefault(Attribute.DAMAGE_REDUCTION, 0.0)
                + equipAdd.getOrDefault(Attribute.DAMAGE_REDUCTION, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.DAMAGE_REDUCTION, 0.0));

        this.healingEffectiveness = (baseAttrs.getOrDefault(Attribute.HEALING_EFFECTIVENESS, 0.0)
                + equipAdd.getOrDefault(Attribute.HEALING_EFFECTIVENESS, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.HEALING_EFFECTIVENESS, 0.0));

        this.goldFind = (baseAttrs.getOrDefault(Attribute.GOLD_FIND, 0.0)
                + equipAdd.getOrDefault(Attribute.GOLD_FIND, 0.0))
                * (1.0 + equipMul.getOrDefault(Attribute.GOLD_FIND, 0.0));
    }

    /**
     * 获取玩家的属性字符串表示
     *
     * @return 属性字符串
     */
    @Override
    public String toString() {
        String ln = System.lineSeparator();
        StringBuilder sb = new StringBuilder();

        sb.append("属性 [");
        sb.append(ln).append("  姓: ").append(getFirstName());
        sb.append(ln).append("  名: ").append(getLastName());
        sb.append(ln).append("  种族: ").append(getEthnicity().getEthnicityZh());
        sb.append(ln).append("  职业: ").append(getJob().getNameZh());
        sb.append(ln).append("  持有经验: ").append(getExp()).append(" / ").append(ExpList.getExpByLevel(getLevel() + 1).getMinExp());
        sb.append(ln).append("  等级: ").append(getLevel());
        sb.append(ln).append("  HP: ").append(currentHealthPoint).append(" / ").append(maxHealthPoint);
        sb.append(ln).append("  MP: ").append(currentManaPoint).append(" / ").append(maxManaPoint);
        sb.append(ln).append("  力量: ").append(strength);
        sb.append(ln).append("  敏捷: ").append(agility);
        sb.append(ln).append("  智力: ").append(intelligence);
        sb.append(ln).append("  物理防御力: ").append(phyDefense);
        sb.append(ln).append("  魔法防御力: ").append(magicDefense);
        sb.append(ln).append("  每轮恢复HP: ").append(recoverHP);
        sb.append(ln).append("  每轮恢复MP: ").append(recoverMP);
        sb.append(ln).append("  ").append(String.format("暴击率: %.2f%%", criticalHitRate * 100));
        sb.append(ln).append("]");
        return sb.toString();
    }
}
