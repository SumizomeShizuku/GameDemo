package org.demo.list;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Random;

/**
 * <h2>敌人稀有度枚举</h2>
 * <p>
 * 枚举值按默认出现概率从高到低排列, 并内置权重随机抽取工具。<br>
 * ⚠️ 本枚举的 <b>{@link #randomExcludeHouchou(Random)}</b>
 * 方法会<strong>排除宝兆</strong>, 其余权重依次为 89 / 21 / 5 / 2 / 1。
 * </p>
 */
public enum EnemyRarity {

    /**
     * 常徒 —— 普通杂兵( 权重 89 )
     */
    JOUTO("常徒", "じょうと", "Joto", 89),
    /**
     * 異相 —— 变异体( 权重 21 )
     */
    ISO("異相", "いそう", "Iso", 21),
    /**
     * 凶牙 —— 猛兽级精英( 权重 5 )
     */
    KYOUGA("凶牙", "きょうが", "Kyoga", 5),
    /**
     * 邪祟 —— 诅咒级威胁( 权重 2 )
     */
    JASUI("邪祟", "じゃすい", "Jasui", 2),
    /**
     * 宝兆 —— 奖励怪( 本方法排除；如需参与请单独处理 )
     */
    HOUCHOU("宝兆", "ほうちょう", "Hocho", 0),
    /**
     * 禍神 —— 神话级存在( 权重 1 )
     */
    MAGATSUKAMI("禍神", "まがつかみ", "Magatsukami", 1);

    /* ---------- 实例字段 ---------- */
    /**
     * 中文显示名
     */
    private final String displayNameZh;
    /**
     * 日文假名
     */
    private final String kana;
    /**
     * 罗马字( ヘボン式 )
     */
    private final String romaji;
    /**
     * 用于随机抽取的权重
     */
    private final int weight;

    /* ---------- 构造器 ---------- */
    EnemyRarity(String displayNameZh, String kana, String romaji, int weight) {
        this.displayNameZh = displayNameZh;
        this.kana = kana;
        this.romaji = romaji;
        this.weight = weight;
    }

    /* ---------- Getter ---------- */
    public String getDisplayNameZh() {
        return displayNameZh;
    }

    public String getKana() {
        return kana;
    }

    public String getRomaji() {
        return romaji;
    }

    public int getWeight() {
        return weight;
    }

    /*
     * ------------------------------------------------------------------
     * 核心：按权重随机抽取稀有度, 并以 0.2 % 概率附加「宝兆」
     * ------------------------------------------------------------------
     */
    /**
     * 随机选取敌人稀有度( 排除宝兆权重 ), 并以 0.2 % 概率附加宝兆。
     *
     * @return 至少包含 1 个元素的 {@link EnumSet}。<br>
     * 必含按权重抽出的稀有度；若命中 0.2 % 机率, 则同时含 {@link #HOUCHOU 宝兆}。
     */
    public static EnumSet<EnemyRarity> randomExcludeHouchou() {
        Random random = new Random();
        // 结果集, 使用 EnumSet 便于高效存取
        EnumSet<EnemyRarity> result = EnumSet.noneOf(EnemyRarity.class);

        /* ---------- 1. 权重随机抽取( 不含宝兆 ) ---------- */
        // 计算权重总和
        int totalWeight = Arrays.stream(values())
                .filter(r -> r != HOUCHOU) // 跳过宝兆
                .mapToInt(EnemyRarity::getWeight)
                .sum();

        // 随机区段
        int rand = random.nextInt(totalWeight);
        int acc = 0;
        EnemyRarity selected = JOUTO; // 兜底

        for (EnemyRarity r : values()) {
            if (r == HOUCHOU) {
                continue; // 宝兆不参与权重池

            }
            acc += r.weight;
            if (rand < acc) {
                selected = r;
                break;
            }
        }
        result.add(selected); // 至少加入 1 个稀有度

        /* ---------- 2. 0.2 % 概率追加宝兆 ---------- */
        if (random.nextDouble() < 0.002) { // 0.002 = 0.2%
            result.add(HOUCHOU);
        }

        return result;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", displayNameZh, romaji);
    }
}
