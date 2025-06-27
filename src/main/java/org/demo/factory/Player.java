package org.demo.factory;

import org.demo.backpack.Backpack;
import org.demo.backpack.BackpackSlot;
import org.demo.backpack.Equipment;
import org.demo.backpack.ItemInstance;
import org.demo.dto.ItemModelDto;
import org.demo.dto.PlayerModelDto;
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
    public int getPhysicsDefenes() {
        return model.getPhysicsDefenes();
    }

    /**
     * 获取玩家的魔法防御力
     *
     * @return
     */
    public int getMagicDefenes() {
        return model.getMagicDefenes();
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
}
