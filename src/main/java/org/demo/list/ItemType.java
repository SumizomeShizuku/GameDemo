package org.demo.list;

/**
 * 物品类型枚举定义
 * <p>
 * 适用于武器、防具、饰品、消耗品等所有可用物品类型
 * </p>
 */
public enum ItemType {

    // 武器
    /**
     * 武器( 通用 )
     */
    WEAPON("weapon"),
    /**
     * 武器( 主手可装备 )
     */
    MAINHAND("mainHand"),
    /**
     * 武器( 副手可装备 )
     */
    OFFHAND("offHand"),
    /**
     * 剑( 包括单手剑、双手剑、巨剑等 )
     */
    SWORD("sword"),
    /**
     * 匕首( 短刀、飞刀等 )
     */
    DAGGER("dagger"),
    /**
     * 斧头( 单手斧、双手斧 )
     */
    AXE("axe"),
    /**
     * 锤( 战锤、钉锤等 )
     */
    HAMMER("hammer"),
    /**
     * 钝器( 狼牙棒、锤棒等 )
     */
    MACE("mace"),
    /**
     * 长矛( 矛、戟、枪等 )
     */
    SPEAR("spear"),
    /**
     * 法杖( 魔法杖、牧师杖等 )
     */
    STAFF("staff"),
    /**
     * 拳套( 铁拳、臂铠等 )
     */
    FIST_WEAPON("fist_weapon"),
    /**
     * 弓( 长弓、短弓、复合弓等 )
     */
    BOW("bow"),
    /**
     * 弩( 连弩、重弩等 )
     */
    CROSSBOW("crossbow"),
    /**
     * 投石索
     */
    SLING("sling"),
    /**
     * 投掷武器( 飞镖、投矛、回旋镖等 )
     */
    THROWN("thrown"),
    /**
     * 魔杖( 小型魔法棒 )
     */
    WAND("wand"),
    /**
     * 法球( 水晶球、法力球等 )
     */
    ORB("orb"),
    /**
     * 魔法书( 魔法卷轴、法书 )
     */
    TOME("tome"),
    /**
     * 手枪
     */
    PISTOL("pistol"),
    /**
     * 步枪
     */
    RIFLE("rifle"),
    /**
     * 霰弹枪
     */
    SHOTGUN("shotgun"),
    /**
     * 机枪
     */
    MACHINE_GUN("machine_gun"),
    /**
     * 狙击枪
     */
    SNIPER("sniper"),
    /**
     * 发射器( 火箭筒、榴弹发射器等 )
     */
    LAUNCHER("launcher"),
    /**
     * 鞭子
     */
    WHIP("whip"),
    /**
     * 锁链武器
     */
    CHAIN("chain"),
    // 防具
    /**
     * 防具( 通用 )
     */
    ARMOR("armor"),
    /**
     * 帽子
     */
    ARMOR_HAT("armor_hat"),
    /**
     * 上衣
     */
    ARMOR_CHEST("armor_chest"),
    /**
     * 裤子
     */
    ARMOR_PANTS("armor_pants"),
    /**
     * 鞋子
     */
    ARMOR_SHOES("armor_shoes"),
    /**
     * 护盾
     */
    ARMOR_SHIELD("armor_shield"),
    /**
     * 手套
     */
    ARMOR_GLOVES("armor_gloves"),
    /**
     * 护肩
     */
    ARMOR_SHOULDERS("armor_shoulders"),
    /**
     * 披风
     */
    ARMOR_CLOAK("armor_cloak"),
    /**
     * 腰带
     */
    ARMOR_BELT("armor_belt"),
    /**
     * 护腕
     */
    ARMOR_BRACER("armor_bracer"),
    // 饰品
    /**
     * 饰品( 通用 )
     */
    ACCESSORY("accessory"),
    /**
     * 项链
     */
    NECKLACE("necklace"),
    /**
     * 戒指
     */
    RING("ring"),
    /**
     * 护符
     */
    AMULET("amulet"),
    /**
     * 耳环
     */
    EARRING("earring"),
    /**
     * 徽章
     */
    BADGE("badge"),
    /**
     * 消耗品
     */
    CONSUMABLE("consumable"),
    /**
     * 药水
     */
    POTION("potion"),
    /**
     * 材料
     */
    MATERIAL("material"),
    /**
     * 任务物品
     */
    QUEST_ITEM("quest_item"),
    /**
     * 宝箱
     */
    CHEST("chest"),
    /**
     * 书籍
     */
    BOOK("book"),
    /**
     * 杂物
     */
    MISC("misc");

    /**
     * 物品类型字符串值
     */
    private final String value;

    ItemType(String value) {
        this.value = value;
    }

    /**
     * 获取物品类型字符串值
     *
     * @return 字符串类型
     */
    public String getValue() {
        return value;
    }

    /**
     * 根据字符串值获取对应枚举( 忽略大小写, 不存在返回null )
     *
     * @param value 字符串
     * @return ItemType 或 null
     */
    public static ItemType fromValue(String value) {
        for (ItemType type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }
}
