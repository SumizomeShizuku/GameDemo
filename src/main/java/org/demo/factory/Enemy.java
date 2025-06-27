package org.demo.factory;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.demo.backpack.DropInfo;
import org.demo.backpack.GenerateDrops;
import org.demo.dto.EnemyModelDto;
import org.demo.dto.ItemModelDto;
import org.demo.dto.SkillModelDto;
import org.demo.list.ItemType;

/**
 * 敌人类, 表示游戏中的敌人。 包含敌人的属性和行为。
 */
public class Enemy {

    // 敌人ID
    private final String id;
    // 敌人名称
    private final String name;
    // 最大生命值
    private final int maxHp;
    // 当前生命值
    private int currentHp;
    // 攻击力
    private final int attack;
    // 物理防御力
    private final int phyDefense;
    // 魔法防御力
    private final int magicDefense;
    // 掉落经验
    private final int dropExp;
    // 物品掉落率
    private final double dropRate;
    // 掉落物列表
    private final Map<ItemModelDto, DropInfo> dropItems;
    // 敌人活动区域
    private final List<String> areas;
    // 敌人技能
    private final Map<String, SkillModelDto> enemySkills;

    /**
     * 构造一个敌人对象。
     *
     * @param attr 敌人模型数据传输对象, 包含敌人的属性
     */
    public Enemy(EnemyModelDto attr) {
        this.id = attr.getId();
        this.name = attr.getName();
        this.maxHp = attr.getMaxHp();
        this.currentHp = maxHp;
        this.attack = attr.getAttack();
        this.phyDefense = attr.getPhyDefense();
        this.magicDefense = attr.getMagicDefense();
        this.dropExp = attr.getDropExp();
        this.dropRate = attr.getDropRate();
        this.dropItems = attr.getDropItems();
        this.areas = attr.getAreas();
        this.enemySkills = attr.getEnemySkills();
    }

    /**
     * 计算敌人受到的伤害。
     *
     * @param damage 伤害值
     */
    public void takeDamage(int damage) {
        currentHp = Math.max(0, currentHp - damage);
    }

    /**
     * 获取敌人名字
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 获取敌人最大血量
     *
     * @return
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * 获取敌人最大血量
     *
     * @return
     */
    public int getCurrentHp() {
        return currentHp;
    }

    /**
     * 获取敌人攻击力
     *
     * @return
     */
    public int getAttack() {
        return attack;
    }

    /**
     * 获取敌人物理防御力
     *
     * @return
     */
    public int getPhyDefense() {
        return phyDefense;
    }

    /**
     * 获取敌人魔法防御力
     *
     * @return
     */
    public int getMagicDefense() {
        return magicDefense;
    }

    /**
     * 获取敌人掉落经验
     *
     * @return 掉落经验
     */
    public int getDropExp() {
        return dropExp;
    }

    /**
     * 获取敌人物品掉落率
     *
     * @return 物品掉落率
     */
    public double getDropRate() {
        return dropRate;
    }

    /**
     * 获取敌人活动区域
     *
     * @return 敌人活动区域
     */
    public List<String> getAreas() {
        return areas;
    }

    /**
     * 获取敌人掉落物品表
     *
     * @return 掉落物品表
     */
    public Map<ItemModelDto, DropInfo> getDropItems() {
        return dropItems;
    }

    /**
     * 获取敌人技能
     *
     * @return 敌人技能
     */
    public Map<String, SkillModelDto> getEnemySkills() {
        return enemySkills;
    }

    /**
     * 判断敌人是否存活
     *
     * @return true:存活, false:死亡
     */
    public boolean isAlive() {
        return this.currentHp > 0;
    }

    /**
     * 随机掉落一定范围内种类数量的物品, 按权重抽取, 避免重复。
     *
     * @param droppedItems 物品掉落率, 默认80%
     * @return 掉落的物品列表( 可能包含相同物品多次 )
     */
    public Map<ItemModelDto, Integer> generateDrops(Enemy enemy) {
        Map<ItemModelDto, Integer> drops = GenerateDrops.generateDrops(enemy);
        return drops;
    }

    /**
     * 将掉落的物品列表转换为文字列
     *
     * @param droppedItems
     * @return 掉落物(文字列)
     */
    public String formatDropItems(Map<ItemModelDto, Integer> droppedItems) {
        StringBuilder sb = new StringBuilder();
        String ln = System.lineSeparator();

        for (Map.Entry<ItemModelDto, Integer> entry : droppedItems.entrySet()) {
            ItemModelDto item = entry.getKey();
            int count = entry.getValue();
            if (isStackable(item)) {
                sb.append(ln);
                sb.append("道具名称: ").append(item.getName()).append(ln);
                sb.append("物品类型: ").append(item.getType()).append(ln);
                sb.append("物品描述: ").append(item.getDescription()).append(ln);
                sb.append("物品价格: ").append(item.getPrice()).append(ln);
                sb.append("掉落数量: ").append(count).append(ln);
                sb.append("---------------------------").append(ln);
            } else {
                for (int i = 0; i < count; i++) {
                    sb.append(ln);
                    sb.append("道具名称: ").append(item.getName()).append(ln);
                    sb.append("物品类型: ").append(item.getType()).append(ln);
                    sb.append("物品描述: ").append(item.getDescription()).append(ln);
                    sb.append("物品价格: ").append(item.getPrice()).append(ln);
                    sb.append("---------------------------").append(ln);
                }
            }

        }

        return sb.toString();
    }

    /**
     * 判断物品是否为可叠加类型
     *
     * @param item 物品模板
     * @return true为可叠加物品, false为不可叠加
     */
    private boolean isStackable(ItemModelDto item) {
        EnumSet<ItemType> type = item.getType();
        return !(type.contains(ItemType.WEAPON) || type.contains(ItemType.ARMOR) || type.contains(ItemType.ACCESSORY));
    }

    /**
     * 从敌人的技能池中随机获取一个技能
     *
     * @param skillMap 敌人技能Map
     * @return 随机技能( SkillList )
     */
    public SkillModelDto getEnemyRandomSkill() {
        if (enemySkills == null || enemySkills.isEmpty()) {
            return null;
        }
        List<SkillModelDto> skillList = new ArrayList<>(enemySkills.values());
        Random random = new Random();
        return skillList.get(random.nextInt(skillList.size()));
    }

    /**
     * 检查敌人是否存活
     *
     * @return
     */
    public boolean aliveChaeck() {
        return true;
    }

    @Override
    public String toString() {
        return name + "[" + id + "]" + " (HP: " + currentHp + "/" + maxHp + ")";
    }

}
