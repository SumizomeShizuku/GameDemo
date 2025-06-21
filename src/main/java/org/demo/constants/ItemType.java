package org.demo.constants;

/**
 * 物品类型常量定义
 */
public class ItemType {

    private ItemType() {
    } // 防止实例化

    /**
     * 武器
     */
    public static final String WEAPON = "weapon";
    /**
     * 剑( SWORD )：包括单手剑、双手剑、巨剑等
     */
    public static final String SWORD = "sword";
    /**
     * 匕首( DAGGER )：短刀、飞刀等
     */
    public static final String DAGGER = "dagger";
    /**
     * 斧头( AXE )：单手斧、双手斧
     */
    public static final String AXE = "axe";
    /**
     * 锤( HAMMER )：战锤、钉锤等
     */
    public static final String HAMMER = "hammer";
    /**
     * 钝器( MACE )：狼牙棒、锤棒等
     */
    public static final String MACE = "mace";
    /**
     * 长矛( SPEAR )：矛、戟、枪等
     */
    public static final String SPEAR = "spear";
    /**
     * 法杖( STAFF )：魔法杖、牧师杖等
     */
    public static final String STAFF = "staff";
    /**
     * 拳套( FIST_WEAPON )：铁拳、臂铠等
     */
    public static final String FIST_WEAPON = "fist_weapon";

    /**
     * 弓( BOW )：长弓、短弓、复合弓等
     */
    public static final String BOW = "bow";
    /**
     * 弩( CROSSBOW )：连弩、重弩等
     */
    public static final String CROSSBOW = "crossbow";
    /**
     * 投石索( SLING )
     */
    public static final String SLING = "sling";
    /**
     * 投掷武器( THROWN )：飞镖、投矛、回旋镖等
     */
    public static final String THROWN = "thrown";

    /**
     * 魔杖( WAND )：小型魔法棒
     */
    public static final String WAND = "wand";
    /**
     * 法球( ORB )：水晶球、法力球等
     */
    public static final String ORB = "orb";
    /**
     * 魔法书( TOME )：魔法卷轴、法书
     */
    public static final String TOME = "tome";

    /**
     * 手枪( PISTOL )
     */
    public static final String PISTOL = "pistol";
    /**
     * 步枪( RIFLE )
     */
    public static final String RIFLE = "rifle";
    /**
     * 霰弹枪( SHOTGUN )
     */
    public static final String SHOTGUN = "shotgun";
    /**
     * 机枪( MACHINE_GUN )
     */
    public static final String MACHINE_GUN = "machine_gun";
    /**
     * 狙击枪( SNIPER )
     */
    public static final String SNIPER = "sniper";
    /**
     * 发射器( LAUNCHER )：火箭筒、榴弹发射器等
     */
    public static final String LAUNCHER = "launcher";

    /**
     * 鞭子( WHIP )
     */
    public static final String WHIP = "whip";
    /**
     * 锁链武器( CHAIN )
     */
    public static final String CHAIN = "chain";

    /**
     * 防具( 大类 )
     */
    public static final String ARMOR = "armor";
    // 细化的防具类型
    /**
     * 帽子
     */
    public static final String ARMOR_HAT = "armor_hat";
    /**
     * 上衣
     */
    public static final String ARMOR_CHEST = "armor_chest";
    /**
     * 裤子
     */
    public static final String ARMOR_PANTS = "armor_pants";
    /**
     * 鞋子
     */
    public static final String ARMOR_SHOES = "armor_shoes";
    /**
     * 护盾
     */
    public static final String ARMOR_SHIELD = "armor_shield";
    /**
     * 手套
     */
    public static final String ARMOR_GLOVES = "armor_gloves";
    /**
     * 护肩
     */
    public static final String ARMOR_SHOULDERS = "armor_shoulders";
    /**
     * 披风
     */
    public static final String ARMOR_CLOAK = "armor_cloak";
    /**
     * 腰带
     */
    public static final String ARMOR_BELT = "armor_belt";
    /**
     * 护腕
     */
    public static final String ARMOR_BRACER = "armor_bracer";

    // 饰品类( 细化 )
    /**
     * 饰品( 通用，不细分时用 )
     */
    public static final String ACCESSORY = "accessory";
    /**
     * 项链
     */
    public static final String NECKLACE = "necklace";
    /**
     * 戒指
     */
    public static final String RING = "ring";
    /**
     * 护符
     */
    public static final String AMULET = "amulet";
    /**
     * 耳环
     */
    public static final String EARRING = "earring";
    /**
     * 徽章
     */
    public static final String BADGE = "badge";

    // 其它常用类型
    /**
     * 消耗品
     */
    public static final String CONSUMABLE = "consumable";
    /**
     * 药水
     */
    public static final String POTION = "potion";
    /**
     * 材料
     */
    public static final String MATERIAL = "material";
    /**
     * 任务物品
     */
    public static final String QUEST_ITEM = "quest_item";
    /**
     * 宝箱
     */
    public static final String CHEST = "chest";
    /**
     * 书籍
     */
    public static final String BOOK = "book";
    /**
     * 杂物
     */
    public static final String MISC = "misc";
}
