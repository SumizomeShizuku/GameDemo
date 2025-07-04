package org.demo.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 装备词条枚举类( 34 组 × 5 级 = 170 条 )<br>
 * 词条名称以日式阴阳道 / 神道 / 妖怪风格为主题, 使用罗马数字 I‑V 表示等级。<br>
 * 每条词条拥有：<br>
 * ▸ <b>rank</b> – 等级( 1‑5 )<br>
 * ▸ <b>affixWeight</b> – 词条出现权重( 与等级无关 )<br>
 * ▸ <b>levelWeight</b> – 在该词条下各等级出现的权重<br>
 * ▸ <b>modifiers</b> – 对 {@link Attribute} 的一组加值( double, 既可整数也可百分比 )<br>
 * <p>
 * 使用示例：
 *
 * <pre>{@code
 * EquipmentAffix affix = EquipmentAffix.random(rng);
 * double extraCrit = affix.getAttributeValue(Attribute.CRITICAL_HIT_RATE);
 * Map<Attribute, Double> map = affix.getAttributeMap();
 * }</pre>
 */
public enum EquipmentAffix {
    // region 01 天照
    AMATERASU_KAGAYAKI_I("天照ノ輝き I", 1, 1000, 1000, m(Attribute.STRENGTH, 2, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.01, BonusType.ADD)),
    AMATERASU_KAGAYAKI_II("天照ノ輝き II", 2, 1000, 450, m(Attribute.STRENGTH, 4, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.02, BonusType.ADD)),
    AMATERASU_KAGAYAKI_III("天照ノ輝き III", 3, 1000, 180, m(Attribute.STRENGTH, 6, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.03, BonusType.ADD)),
    AMATERASU_KAGAYAKI_IV("天照ノ輝き IV", 4, 1000, 45, m(Attribute.STRENGTH, 8, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.04, BonusType.ADD)),
    AMATERASU_KAGAYAKI_V("天照ノ輝き V", 5, 1000, 10, m(Attribute.STRENGTH, 10, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.05, BonusType.ADD)),
    // region 02 月読
    TSUKUYOMI_SEIJAKU_I("月読ノ静寂 I", 1, 1000, 1000, m(Attribute.INTELLIGENCE, 2, BonusType.ADD), m(Attribute.RECOVER_MP, 2, BonusType.ADD)),
    TSUKUYOMI_SEIJAKU_II("月読ノ静寂 II", 2, 1000, 450, m(Attribute.INTELLIGENCE, 4, BonusType.ADD), m(Attribute.RECOVER_MP, 4, BonusType.ADD)),
    TSUKUYOMI_SEIJAKU_III("月読ノ静寂 III", 3, 1000, 180, m(Attribute.INTELLIGENCE, 6, BonusType.ADD), m(Attribute.RECOVER_MP, 6, BonusType.ADD)),
    TSUKUYOMI_SEIJAKU_IV("月読ノ静寂 IV", 4, 1000, 45, m(Attribute.INTELLIGENCE, 8, BonusType.ADD), m(Attribute.RECOVER_MP, 8, BonusType.ADD)),
    TSUKUYOMI_SEIJAKU_V("月読ノ静寂 V", 5, 1000, 10, m(Attribute.INTELLIGENCE, 10, BonusType.ADD), m(Attribute.RECOVER_MP, 10, BonusType.ADD)),
    // region 03 素戔嗚尊
    SUSANOO_ARASHI_I("素戔嗚尊ノ嵐 I", 1, 1000, 1000, m(Attribute.AGILITY, 2, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.01, BonusType.ADD)),
    SUSANOO_ARASHI_II("素戔嗚尊ノ嵐 II", 2, 1000, 450, m(Attribute.AGILITY, 4, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.02, BonusType.ADD)),
    SUSANOO_ARASHI_III("素戔嗚尊ノ嵐 III", 3, 1000, 180, m(Attribute.AGILITY, 6, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.03, BonusType.ADD)),
    SUSANOO_ARASHI_IV("素戔嗚尊ノ嵐 IV", 4, 1000, 45, m(Attribute.AGILITY, 8, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.04, BonusType.ADD)),
    SUSANOO_ARASHI_V("素戔嗚尊ノ嵐 V", 5, 1000, 10, m(Attribute.AGILITY, 10, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.05, BonusType.ADD)),
    // region 04 風神
    FUJIN_IBUKI_I("風神ノ息吹 I", 1, 1000, 1000, m(Attribute.INCDAMAGE, 0.02, BonusType.ADD), m(Attribute.EVASION, 0.02, BonusType.ADD)),
    FUJIN_IBUKI_II("風神ノ息吹 II", 2, 1000, 450, m(Attribute.INCDAMAGE, 0.04, BonusType.ADD), m(Attribute.EVASION, 0.04, BonusType.ADD)),
    FUJIN_IBUKI_III("風神ノ息吹 III", 3, 1000, 180, m(Attribute.INCDAMAGE, 0.06, BonusType.ADD), m(Attribute.EVASION, 0.06, BonusType.ADD)),
    FUJIN_IBUKI_IV("風神ノ息吹 IV", 4, 1000, 45, m(Attribute.INCDAMAGE, 0.08, BonusType.ADD), m(Attribute.EVASION, 0.08, BonusType.ADD)),
    FUJIN_IBUKI_V("風神ノ息吹 V", 5, 1000, 10, m(Attribute.INCDAMAGE, 0.10, BonusType.ADD), m(Attribute.EVASION, 0.10, BonusType.ADD)),
    // region 05 雷神
    RAIJIN_TODOROKI_I("雷神ノ轟き I", 1, 1000, 1000, m(Attribute.PHY_DEFENSE, 2, BonusType.ADD), m(Attribute.BLEED_CHANCE, 0.02, BonusType.ADD)),
    RAIJIN_TODOROKI_II("雷神ノ轟き II", 2, 1000, 450, m(Attribute.PHY_DEFENSE, 4, BonusType.ADD), m(Attribute.BLEED_CHANCE, 0.04, BonusType.ADD)),
    RAIJIN_TODOROKI_III("雷神ノ轟き III", 3, 1000, 180, m(Attribute.PHY_DEFENSE, 6, BonusType.ADD), m(Attribute.BLEED_CHANCE, 0.06, BonusType.ADD)),
    RAIJIN_TODOROKI_IV("雷神ノ轟き IV", 4, 1000, 45, m(Attribute.PHY_DEFENSE, 8, BonusType.ADD), m(Attribute.BLEED_CHANCE, 0.08, BonusType.ADD)),
    RAIJIN_TODOROKI_V("雷神ノ轟き V", 5, 1000, 10, m(Attribute.PHY_DEFENSE, 10, BonusType.ADD), m(Attribute.BLEED_CHANCE, 0.10, BonusType.ADD)),
    // region 06 稲荷
    INARI_KEKKAI_I("稲荷ノ結界 I", 1, 1000, 1000, m(Attribute.MAGIC_DEFENSE, 1, BonusType.ADD), m(Attribute.LUCK, 3, BonusType.ADD)),
    INARI_KEKKAI_II("稲荷ノ結界 II", 2, 1000, 450, m(Attribute.MAGIC_DEFENSE, 2, BonusType.ADD), m(Attribute.LUCK, 6, BonusType.ADD)),
    INARI_KEKKAI_III("稲荷ノ結界 III", 3, 1000, 180, m(Attribute.MAGIC_DEFENSE, 3, BonusType.ADD), m(Attribute.LUCK, 9, BonusType.ADD)),
    INARI_KEKKAI_IV("稲荷ノ結界 IV", 4, 1000, 45, m(Attribute.MAGIC_DEFENSE, 4, BonusType.ADD), m(Attribute.LUCK, 12, BonusType.ADD)),
    INARI_KEKKAI_V("稲荷ノ結界 V", 5, 1000, 10, m(Attribute.MAGIC_DEFENSE, 5, BonusType.ADD), m(Attribute.LUCK, 15, BonusType.ADD)),
    // region 07 八幡
    HACHIMAN_SHUGO_I("八幡ノ守護 I", 1, 1000, 1000, m(Attribute.MAX_HEALTH_POINT, 10, BonusType.ADD), m(Attribute.FORTITUDE, 1, BonusType.ADD)),
    HACHIMAN_SHUGO_II("八幡ノ守護 II", 2, 1000, 450, m(Attribute.MAX_HEALTH_POINT, 20, BonusType.ADD), m(Attribute.FORTITUDE, 2, BonusType.ADD)),
    HACHIMAN_SHUGO_III("八幡ノ守護 III", 3, 1000, 180, m(Attribute.MAX_HEALTH_POINT, 30, BonusType.ADD), m(Attribute.FORTITUDE, 3, BonusType.ADD)),
    HACHIMAN_SHUGO_IV("八幡ノ守護 IV", 4, 1000, 45, m(Attribute.MAX_HEALTH_POINT, 40, BonusType.ADD), m(Attribute.FORTITUDE, 4, BonusType.ADD)),
    HACHIMAN_SHUGO_V("八幡ノ守護 V", 5, 1000, 10, m(Attribute.MAX_HEALTH_POINT, 50, BonusType.ADD), m(Attribute.FORTITUDE, 5, BonusType.ADD)),
    // region 08 猿田彦
    SARUTAHIKO_MICHIBIKI_I("猿田彦ノ導き I", 1, 1000, 1000, m(Attribute.INCDAMAGE, 0.03, BonusType.ADD), m(Attribute.RECOVER_HP, 1, BonusType.ADD)),
    SARUTAHIKO_MICHIBIKI_II("猿田彦ノ導き II", 2, 1000, 450, m(Attribute.INCDAMAGE, 0.06, BonusType.ADD), m(Attribute.RECOVER_HP, 2, BonusType.ADD)),
    SARUTAHIKO_MICHIBIKI_III("猿田彦ノ導き III", 3, 1000, 180, m(Attribute.INCDAMAGE, 0.09, BonusType.ADD), m(Attribute.RECOVER_HP, 3, BonusType.ADD)),
    SARUTAHIKO_MICHIBIKI_IV("猿田彦ノ導き IV", 4, 1000, 45, m(Attribute.INCDAMAGE, 0.12, BonusType.ADD), m(Attribute.RECOVER_HP, 4, BonusType.ADD)),
    SARUTAHIKO_MICHIBIKI_V("猿田彦ノ導き V", 5, 1000, 10, m(Attribute.INCDAMAGE, 0.15, BonusType.ADD), m(Attribute.RECOVER_HP, 5, BonusType.ADD)),
    // region 09 イザナギ
    IZANAGI_SOSEI_I("イザナギノ創世 I", 1, 1000, 1000, m(Attribute.STRENGTH, 1, BonusType.ADD), m(Attribute.MAX_MANA_POINT, 5, BonusType.ADD)),
    IZANAGI_SOSEI_II("イザナギノ創世 II", 2, 1000, 450, m(Attribute.STRENGTH, 2, BonusType.ADD), m(Attribute.MAX_MANA_POINT, 10, BonusType.ADD)),
    IZANAGI_SOSEI_III("イザナギノ創世 III", 3, 1000, 180, m(Attribute.STRENGTH, 3, BonusType.ADD), m(Attribute.MAX_MANA_POINT, 15, BonusType.ADD)),
    IZANAGI_SOSEI_IV("イザナギノ創世 IV", 4, 1000, 45, m(Attribute.STRENGTH, 4, BonusType.ADD), m(Attribute.MAX_MANA_POINT, 20, BonusType.ADD)),
    IZANAGI_SOSEI_V("イザナギノ創世 V", 5, 1000, 10, m(Attribute.STRENGTH, 5, BonusType.ADD), m(Attribute.MAX_MANA_POINT, 25, BonusType.ADD)),
    // region 10 イザナミ
    IZANAMI_KURAYAMI_I("イザナミノ暗闇 I", 1, 1000, 1000, m(Attribute.MAGIC_DEFENSE, 1, BonusType.ADD), m(Attribute.DAMAGE_REDUCTION, 0.01, BonusType.ADD)),
    IZANAMI_KURAYAMI_II("イザナミノ暗闇 II", 2, 1000, 450, m(Attribute.MAGIC_DEFENSE, 2, BonusType.ADD), m(Attribute.DAMAGE_REDUCTION, 0.02, BonusType.ADD)),
    IZANAMI_KURAYAMI_III("イザナミノ暗闇 III", 3, 1000, 180, m(Attribute.MAGIC_DEFENSE, 3, BonusType.ADD), m(Attribute.DAMAGE_REDUCTION, 0.03, BonusType.ADD)),
    IZANAMI_KURAYAMI_IV("イザナミノ暗闇 IV", 4, 1000, 45, m(Attribute.MAGIC_DEFENSE, 4, BonusType.ADD), m(Attribute.DAMAGE_REDUCTION, 0.04, BonusType.ADD)),
    IZANAMI_KURAYAMI_V("イザナミノ暗闇 V", 5, 1000, 10, m(Attribute.MAGIC_DEFENSE, 5, BonusType.ADD), m(Attribute.DAMAGE_REDUCTION, 0.05, BonusType.ADD)),
    // region 11 志那都彦
    SHINATSUHIKO_SEIFU_I("志那都彦ノ清風 I", 1, 1000, 1000, m(Attribute.FORTITUDE, 1, BonusType.ADD), m(Attribute.INCDAMAGE, 0.01, BonusType.ADD)),
    SHINATSUHIKO_SEIFU_II("志那都彦ノ清風 II", 2, 1000, 450, m(Attribute.FORTITUDE, 2, BonusType.ADD), m(Attribute.INCDAMAGE, 0.02, BonusType.ADD)),
    SHINATSUHIKO_SEIFU_III("志那都彦ノ清風 III", 3, 1000, 180, m(Attribute.FORTITUDE, 3, BonusType.ADD), m(Attribute.INCDAMAGE, 0.03, BonusType.ADD)),
    SHINATSUHIKO_SEIFU_IV("志那都彦ノ清風 IV", 4, 1000, 45, m(Attribute.FORTITUDE, 4, BonusType.ADD), m(Attribute.INCDAMAGE, 0.04, BonusType.ADD)),
    SHINATSUHIKO_SEIFU_V("志那都彦ノ清風 V", 5, 1000, 10, m(Attribute.FORTITUDE, 5, BonusType.ADD), m(Attribute.INCDAMAGE, 0.05, BonusType.ADD)),
    // region 12 琴平
    KOTOHIRA_RENKEI_I("琴平ノ連携 I", 1, 1000, 1000, m(Attribute.BLEED_CHANCE, 0.02, BonusType.ADD), m(Attribute.STRENGTH, 1, BonusType.ADD)),
    KOTOHIRA_RENKEI_II("琴平ノ連携 II", 2, 1000, 450, m(Attribute.BLEED_CHANCE, 0.04, BonusType.ADD), m(Attribute.STRENGTH, 2, BonusType.ADD)),
    KOTOHIRA_RENKEI_III("琴平ノ連携 III", 3, 1000, 180, m(Attribute.BLEED_CHANCE, 0.06, BonusType.ADD), m(Attribute.STRENGTH, 3, BonusType.ADD)),
    KOTOHIRA_RENKEI_IV("琴平ノ連携 IV", 4, 1000, 45, m(Attribute.BLEED_CHANCE, 0.08, BonusType.ADD), m(Attribute.STRENGTH, 4, BonusType.ADD)),
    KOTOHIRA_RENKEI_V("琴平ノ連携 V", 5, 1000, 10, m(Attribute.BLEED_CHANCE, 0.10, BonusType.ADD), m(Attribute.STRENGTH, 5, BonusType.ADD)),
    // region 13 弁才天
    BENZAITEN_MEIGETSU_I("弁才天ノ明月 I", 1, 1000, 1000, m(Attribute.HEALING_EFFECTIVENESS, 0.05, BonusType.ADD), m(Attribute.RECOVER_MP, 2, BonusType.ADD)),
    BENZAITEN_MEIGETSU_II("弁才天ノ明月 II", 2, 1000, 450, m(Attribute.HEALING_EFFECTIVENESS, 0.10, BonusType.ADD), m(Attribute.RECOVER_MP, 4, BonusType.ADD)),
    BENZAITEN_MEIGETSU_III("弁才天ノ明月 III", 3, 1000, 180, m(Attribute.HEALING_EFFECTIVENESS, 0.15, BonusType.ADD), m(Attribute.RECOVER_MP, 6, BonusType.ADD)),
    BENZAITEN_MEIGETSU_IV("弁才天ノ明月 IV", 4, 1000, 45, m(Attribute.HEALING_EFFECTIVENESS, 0.20, BonusType.ADD), m(Attribute.RECOVER_MP, 8, BonusType.ADD)),
    BENZAITEN_MEIGETSU_V("弁才天ノ明月 V", 5, 1000, 10, m(Attribute.HEALING_EFFECTIVENESS, 0.25, BonusType.ADD), m(Attribute.RECOVER_MP, 10, BonusType.ADD)),
    // region 14 大国主
    OKUNINUSHI_KAIUN_I("大国主ノ開運 I", 1, 1000, 1000, m(Attribute.LUCK, 5, BonusType.ADD), m(Attribute.GOLD_FIND, 0.05, BonusType.ADD)),
    OKUNINUSHI_KAIUN_II("大国主ノ開運 II", 2, 1000, 450, m(Attribute.LUCK, 10, BonusType.ADD), m(Attribute.GOLD_FIND, 0.10, BonusType.ADD)),
    OKUNINUSHI_KAIUN_III("大国主ノ開運 III", 3, 1000, 180, m(Attribute.LUCK, 15, BonusType.ADD), m(Attribute.GOLD_FIND, 0.15, BonusType.ADD)),
    OKUNINUSHI_KAIUN_IV("大国主ノ開運 IV", 4, 1000, 45, m(Attribute.LUCK, 20, BonusType.ADD), m(Attribute.GOLD_FIND, 0.20, BonusType.ADD)),
    OKUNINUSHI_KAIUN_V("大国主ノ開運 V", 5, 1000, 10, m(Attribute.LUCK, 25, BonusType.ADD), m(Attribute.GOLD_FIND, 0.25, BonusType.ADD)),
    // region 15 鬼
    ONI_KONSHIN_I("鬼ノ渾身 I", 1, 1000, 1000, m(Attribute.STRENGTH, 3, BonusType.ADD), m(Attribute.BURN_CHANCE, 0.01, BonusType.ADD)),
    ONI_KONSHIN_II("鬼ノ渾身 II", 2, 1000, 450, m(Attribute.STRENGTH, 6, BonusType.ADD), m(Attribute.BURN_CHANCE, 0.02, BonusType.ADD)),
    ONI_KONSHIN_III("鬼ノ渾身 III", 3, 1000, 180, m(Attribute.STRENGTH, 9, BonusType.ADD), m(Attribute.BURN_CHANCE, 0.03, BonusType.ADD)),
    ONI_KONSHIN_IV("鬼ノ渾身 IV", 4, 1000, 45, m(Attribute.STRENGTH, 12, BonusType.ADD), m(Attribute.BURN_CHANCE, 0.04, BonusType.ADD)),
    ONI_KONSHIN_V("鬼ノ渾身 V", 5, 1000, 10, m(Attribute.STRENGTH, 15, BonusType.ADD), m(Attribute.BURN_CHANCE, 0.05, BonusType.ADD)),
    // region 16 天狗
    TENGU_UCHU_I("天狗ノ羽風 I", 1, 1000, 1000, m(Attribute.EVASION, 0.03, BonusType.ADD), m(Attribute.RECOVER_HP, 2, BonusType.ADD)),
    TENGU_UCHU_II("天狗ノ羽風 II", 2, 1000, 450, m(Attribute.EVASION, 0.06, BonusType.ADD), m(Attribute.RECOVER_HP, 4, BonusType.ADD)),
    TENGU_UCHU_III("天狗ノ羽風 III", 3, 1000, 180, m(Attribute.EVASION, 0.09, BonusType.ADD), m(Attribute.RECOVER_HP, 6, BonusType.ADD)),
    TENGU_UCHU_IV("天狗ノ羽風 IV", 4, 1000, 45, m(Attribute.EVASION, 0.12, BonusType.ADD), m(Attribute.RECOVER_HP, 8, BonusType.ADD)),
    TENGU_UCHU_V("天狗ノ羽風 V", 5, 1000, 10, m(Attribute.EVASION, 0.15, BonusType.ADD), m(Attribute.RECOVER_HP, 10, BonusType.ADD)),
    // region 17 狐
    KITSUNE_GENMAN_I("狐ノ幻魅 I", 1, 1000, 1000, m(Attribute.INTELLIGENCE, 2, BonusType.ADD), m(Attribute.SPIRIT_POWER, 1, BonusType.ADD)),
    KITSUNE_GENMAN_II("狐ノ幻魅 II", 2, 1000, 450, m(Attribute.INTELLIGENCE, 4, BonusType.ADD), m(Attribute.SPIRIT_POWER, 2, BonusType.ADD)),
    KITSUNE_GENMAN_III("狐ノ幻魅 III", 3, 1000, 180, m(Attribute.INTELLIGENCE, 6, BonusType.ADD), m(Attribute.SPIRIT_POWER, 3, BonusType.ADD)),
    KITSUNE_GENMAN_IV("狐ノ幻魅 IV", 4, 1000, 45, m(Attribute.INTELLIGENCE, 8, BonusType.ADD), m(Attribute.SPIRIT_POWER, 4, BonusType.ADD)),
    KITSUNE_GENMAN_V("狐ノ幻魅 V", 5, 1000, 10, m(Attribute.INTELLIGENCE, 10, BonusType.ADD), m(Attribute.SPIRIT_POWER, 5, BonusType.ADD)),
    // region 18 狸
    TANUKI_DOKUJIN_I("狸ノ毒陣 I", 1, 1000, 1000, m(Attribute.POISON_CHANCE, 0.02, BonusType.ADD), m(Attribute.PHY_DEFENSE, 1, BonusType.ADD)),
    TANUKI_DOKUJIN_II("狸ノ毒陣 II", 2, 1000, 450, m(Attribute.POISON_CHANCE, 0.04, BonusType.ADD), m(Attribute.PHY_DEFENSE, 2, BonusType.ADD)),
    TANUKI_DOKUJIN_III("狸ノ毒陣 III", 3, 1000, 180, m(Attribute.POISON_CHANCE, 0.06, BonusType.ADD), m(Attribute.PHY_DEFENSE, 3, BonusType.ADD)),
    TANUKI_DOKUJIN_IV("狸ノ毒陣 IV", 4, 1000, 45, m(Attribute.POISON_CHANCE, 0.08, BonusType.ADD), m(Attribute.PHY_DEFENSE, 4, BonusType.ADD)),
    TANUKI_DOKUJIN_V("狸ノ毒陣 V", 5, 1000, 10, m(Attribute.POISON_CHANCE, 0.10, BonusType.ADD), m(Attribute.PHY_DEFENSE, 5, BonusType.ADD)),
    // region 19 迦楼罗
    GARUDA_HONO_I("迦楼罗ノ炎翼 I", 1, 1000, 1000, m(Attribute.BURN_CHANCE, 0.02, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.01, BonusType.ADD)),
    GARUDA_HONO_II("迦楼罗ノ炎翼 II", 2, 1000, 450, m(Attribute.BURN_CHANCE, 0.04, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.02, BonusType.ADD)),
    GARUDA_HONO_III("迦楼罗ノ炎翼 III", 3, 1000, 180, m(Attribute.BURN_CHANCE, 0.06, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.03, BonusType.ADD)),
    GARUDA_HONO_IV("迦楼罗ノ炎翼 IV", 4, 1000, 45, m(Attribute.BURN_CHANCE, 0.08, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.04, BonusType.ADD)),
    GARUDA_HONO_V("迦楼罗ノ炎翼 V", 5, 1000, 10, m(Attribute.BURN_CHANCE, 0.10, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.05, BonusType.ADD)),
    // region 20 河童
    KAPPA_SUIGEKI_I("河童ノ水撃 I", 1, 1000, 1000, m(Attribute.LUCK, 2, BonusType.ADD), m(Attribute.EVASION, 0.02, BonusType.ADD)),
    KAPPA_SUIGEKI_II("河童ノ水撃 II", 2, 1000, 450, m(Attribute.LUCK, 4, BonusType.ADD), m(Attribute.EVASION, 0.04, BonusType.ADD)),
    KAPPA_SUIGEKI_III("河童ノ水撃 III", 3, 1000, 180, m(Attribute.LUCK, 6, BonusType.ADD), m(Attribute.EVASION, 0.06, BonusType.ADD)),
    KAPPA_SUIGEKI_IV("河童ノ水撃 IV", 4, 1000, 45, m(Attribute.LUCK, 8, BonusType.ADD), m(Attribute.EVASION, 0.08, BonusType.ADD)),
    KAPPA_SUIGEKI_V("河童ノ水撃 V", 5, 1000, 10, m(Attribute.LUCK, 10, BonusType.ADD), m(Attribute.EVASION, 0.10, BonusType.ADD)),
    // region 21 不知火
    SHIRANUI_HOMURA_I("不知火ノ炎 I", 1, 1000, 1000, m(Attribute.CRITICAL_HIT_RATE, 0.01, BonusType.ADD), m(Attribute.BLEED_CHANCE, 0.02, BonusType.ADD)),
    SHIRANUI_HOMURA_II("不知火ノ炎 II", 2, 1000, 450, m(Attribute.CRITICAL_HIT_RATE, 0.02, BonusType.ADD), m(Attribute.BLEED_CHANCE, 0.04, BonusType.ADD)),
    SHIRANUI_HOMURA_III("不知火ノ炎 III", 3, 1000, 180, m(Attribute.CRITICAL_HIT_RATE, 0.03, BonusType.ADD), m(Attribute.BLEED_CHANCE, 0.06, BonusType.ADD)),
    SHIRANUI_HOMURA_IV("不知火ノ炎 IV", 4, 1000, 45, m(Attribute.CRITICAL_HIT_RATE, 0.04, BonusType.ADD), m(Attribute.BLEED_CHANCE, 0.08, BonusType.ADD)),
    SHIRANUI_HOMURA_V("不知火ノ炎 V", 5, 1000, 10, m(Attribute.CRITICAL_HIT_RATE, 0.05, BonusType.ADD), m(Attribute.BLEED_CHANCE, 0.10, BonusType.ADD)),
    // region 22 雀
    SUZUME_NA_HANAYAGI_I("雀ノ華やぎ I", 1, 1000, 1000, m(Attribute.RECOVER_HP, 1, BonusType.ADD), m(Attribute.HEALING_EFFECTIVENESS, 0.05, BonusType.ADD)),
    SUZUME_NA_HANAYAGI_II("雀ノ華やぎ II", 2, 1000, 450, m(Attribute.RECOVER_HP, 2, BonusType.ADD), m(Attribute.HEALING_EFFECTIVENESS, 0.10, BonusType.ADD)),
    SUZUME_NA_HANAYAGI_III("雀ノ華やぎ III", 3, 1000, 180, m(Attribute.RECOVER_HP, 3, BonusType.ADD), m(Attribute.HEALING_EFFECTIVENESS, 0.15, BonusType.ADD)),
    SUZUME_NA_HANAYAGI_IV("雀ノ華やぎ IV", 4, 1000, 45, m(Attribute.RECOVER_HP, 4, BonusType.ADD), m(Attribute.HEALING_EFFECTIVENESS, 0.20, BonusType.ADD)),
    SUZUME_NA_HANAYAGI_V("雀ノ華やぎ V", 5, 1000, 10, m(Attribute.RECOVER_HP, 5, BonusType.ADD), m(Attribute.HEALING_EFFECTIVENESS, 0.25, BonusType.ADD)),
    // region 23 山姥
    YAMANBA_TAKUMI_I("山姥ノ匠 I", 1, 1000, 1000, m(Attribute.FORTITUDE, 1, BonusType.ADD), m(Attribute.PHY_DEFENSE, 1, BonusType.ADD)),
    YAMANBA_TAKUMI_II("山姥ノ匠 II", 2, 1000, 450, m(Attribute.FORTITUDE, 2, BonusType.ADD), m(Attribute.PHY_DEFENSE, 2, BonusType.ADD)),
    YAMANBA_TAKUMI_III("山姥ノ匠 III", 3, 1000, 180, m(Attribute.FORTITUDE, 3, BonusType.ADD), m(Attribute.PHY_DEFENSE, 3, BonusType.ADD)),
    YAMANBA_TAKUMI_IV("山姥ノ匠 IV", 4, 1000, 45, m(Attribute.FORTITUDE, 4, BonusType.ADD), m(Attribute.PHY_DEFENSE, 4, BonusType.ADD)),
    YAMANBA_TAKUMI_V("山姥ノ匠 V", 5, 1000, 10, m(Attribute.FORTITUDE, 5, BonusType.ADD), m(Attribute.PHY_DEFENSE, 5, BonusType.ADD)),
    // region 24 麒麟
    KIRIN_SETSUNA_I("麒麟ノ刹那 I", 1, 1000, 1000, m(Attribute.FREEZE_CHANCE, 0.02, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.01, BonusType.ADD)),
    KIRIN_SETSUNA_II("麒麟ノ刹那 II", 2, 1000, 450, m(Attribute.FREEZE_CHANCE, 0.04, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.02, BonusType.ADD)),
    KIRIN_SETSUNA_III("麒麟ノ刹那 III", 3, 1000, 180, m(Attribute.FREEZE_CHANCE, 0.06, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.03, BonusType.ADD)),
    KIRIN_SETSUNA_IV("麒麟ノ刹那 IV", 4, 1000, 45, m(Attribute.FREEZE_CHANCE, 0.08, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.04, BonusType.ADD)),
    KIRIN_SETSUNA_V("麒麟ノ刹那 V", 5, 1000, 10, m(Attribute.FREEZE_CHANCE, 0.10, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.05, BonusType.ADD)),
    // region 25 九尾
    KYUBI_ENKO_I("九尾ノ炎狐 I", 1, 1000, 1000, m(Attribute.BURN_CHANCE, 0.02, BonusType.ADD), m(Attribute.LUCK, 2, BonusType.ADD)),
    KYUBI_ENKO_II("九尾ノ炎狐 II", 2, 1000, 450, m(Attribute.BURN_CHANCE, 0.04, BonusType.ADD), m(Attribute.LUCK, 4, BonusType.ADD)),
    KYUBI_ENKO_III("九尾ノ炎狐 III", 3, 1000, 180, m(Attribute.BURN_CHANCE, 0.06, BonusType.ADD), m(Attribute.LUCK, 6, BonusType.ADD)),
    KYUBI_ENKO_IV("九尾ノ炎狐 IV", 4, 1000, 45, m(Attribute.BURN_CHANCE, 0.08, BonusType.ADD), m(Attribute.LUCK, 8, BonusType.ADD)),
    KYUBI_ENKO_V("九尾ノ炎狐 V", 5, 1000, 10, m(Attribute.BURN_CHANCE, 0.10, BonusType.ADD), m(Attribute.LUCK, 10, BonusType.ADD)),
    // region 26 獅子神
    SHISHIGAMI_SEINOU_I("獅子神ノ聖能 I", 1, 1000, 1000, m(Attribute.HEALING_EFFECTIVENESS, 0.05, BonusType.ADD),
            m(Attribute.MAX_HEALTH_POINT, 10, BonusType.ADD)),
    SHISHIGAMI_SEINOU_II("獅子神ノ聖能 II", 2, 1000, 450, m(Attribute.HEALING_EFFECTIVENESS, 0.10, BonusType.ADD),
            m(Attribute.MAX_HEALTH_POINT, 20, BonusType.ADD)),
    SHISHIGAMI_SEINOU_III("獅子神ノ聖能 III", 3, 1000, 180, m(Attribute.HEALING_EFFECTIVENESS, 0.15, BonusType.ADD),
            m(Attribute.MAX_HEALTH_POINT, 30, BonusType.ADD)),
    SHISHIGAMI_SEINOU_IV("獅子神ノ聖能 IV", 4, 1000, 45, m(Attribute.HEALING_EFFECTIVENESS, 0.20, BonusType.ADD),
            m(Attribute.MAX_HEALTH_POINT, 40, BonusType.ADD)),
    SHISHIGAMI_SEINOU_V("獅子神ノ聖能 V", 5, 1000, 10, m(Attribute.HEALING_EFFECTIVENESS, 0.25, BonusType.ADD),
            m(Attribute.MAX_HEALTH_POINT, 50, BonusType.ADD)),
    // region 27 猩々
    SHOUJOU_SHUYU_I("猩々ノ酒癒 I", 1, 1000, 1000, m(Attribute.HEALING_EFFECTIVENESS, 0.05, BonusType.ADD), m(Attribute.SPIRIT_POWER, 1, BonusType.ADD)),
    SHOUJOU_SHUYU_II("猩々ノ酒癒 II", 2, 1000, 450, m(Attribute.HEALING_EFFECTIVENESS, 0.10, BonusType.ADD), m(Attribute.SPIRIT_POWER, 2, BonusType.ADD)),
    SHOUJOU_SHUYU_III("猩々ノ酒癒 III", 3, 1000, 180, m(Attribute.HEALING_EFFECTIVENESS, 0.15, BonusType.ADD),
            m(Attribute.SPIRIT_POWER, 3, BonusType.ADD)),
    SHOUJOU_SHUYU_IV("猩々ノ酒癒 IV", 4, 1000, 45, m(Attribute.HEALING_EFFECTIVENESS, 0.20, BonusType.ADD), m(Attribute.SPIRIT_POWER, 4, BonusType.ADD)),
    SHOUJOU_SHUYU_V("猩々ノ酒癒 V", 5, 1000, 10, m(Attribute.HEALING_EFFECTIVENESS, 0.25, BonusType.ADD), m(Attribute.SPIRIT_POWER, 5, BonusType.ADD)),
    // region 28 烏
    KARASU_TSUME_I("烏ノ爪痕 I", 1, 1000, 1000, m(Attribute.BLEED_CHANCE, 0.02, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.01, BonusType.ADD)),
    KARASU_TSUME_II("烏ノ爪痕 II", 2, 1000, 450, m(Attribute.BLEED_CHANCE, 0.04, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.02, BonusType.ADD)),
    KARASU_TSUME_III("烏ノ爪痕 III", 3, 1000, 180, m(Attribute.BLEED_CHANCE, 0.06, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.03, BonusType.ADD)),
    KARASU_TSUME_IV("烏ノ爪痕 IV", 4, 1000, 45, m(Attribute.BLEED_CHANCE, 0.08, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.04, BonusType.ADD)),
    KARASU_TSUME_V("烏ノ爪痕 V", 5, 1000, 10, m(Attribute.BLEED_CHANCE, 0.10, BonusType.ADD), m(Attribute.CRITICAL_HIT_RATE, 0.05, BonusType.ADD)),
    // region 29 乙姫
    OTOHIME_MISORA_I("乙姫ノ美空 I", 1, 1000, 1000, m(Attribute.MAX_MANA_POINT, 5, BonusType.ADD), m(Attribute.RECOVER_MP, 2, BonusType.ADD)),
    OTOHIME_MISORA_II("乙姫ノ美空 II", 2, 1000, 450, m(Attribute.MAX_MANA_POINT, 10, BonusType.ADD), m(Attribute.RECOVER_MP, 4, BonusType.ADD)),
    OTOHIME_MISORA_III("乙姫ノ美空 III", 3, 1000, 180, m(Attribute.MAX_MANA_POINT, 15, BonusType.ADD), m(Attribute.RECOVER_MP, 6, BonusType.ADD)),
    OTOHIME_MISORA_IV("乙姫ノ美空 IV", 4, 1000, 45, m(Attribute.MAX_MANA_POINT, 20, BonusType.ADD), m(Attribute.RECOVER_MP, 8, BonusType.ADD)),
    OTOHIME_MISORA_V("乙姫ノ美空 V", 5, 1000, 10, m(Attribute.MAX_MANA_POINT, 25, BonusType.ADD), m(Attribute.RECOVER_MP, 10, BonusType.ADD)),
    // region 30 天狐
    TENKO_SHIEN_I("天狐ノ思縁 I", 1, 1000, 1000, m(Attribute.LUCK, 3, BonusType.ADD), m(Attribute.INCDAMAGE, 0.01, BonusType.ADD)),
    TENKO_SHIEN_II("天狐ノ思縁 II", 2, 1000, 450, m(Attribute.LUCK, 6, BonusType.ADD), m(Attribute.INCDAMAGE, 0.02, BonusType.ADD)),
    TENKO_SHIEN_III("天狐ノ思縁 III", 3, 1000, 180, m(Attribute.LUCK, 9, BonusType.ADD), m(Attribute.INCDAMAGE, 0.03, BonusType.ADD)),
    TENKO_SHIEN_IV("天狐ノ思縁 IV", 4, 1000, 45, m(Attribute.LUCK, 12, BonusType.ADD), m(Attribute.INCDAMAGE, 0.04, BonusType.ADD)),
    TENKO_SHIEN_V("天狐ノ思縁 V", 5, 1000, 10, m(Attribute.LUCK, 15, BonusType.ADD), m(Attribute.INCDAMAGE, 0.05, BonusType.ADD)),
    // region 31 天鈿女
    AMENO_UZUME_I("天鈿女ノ舞 I", 1, 1000, 1000, m(Attribute.RECOVER_HP, 2, BonusType.ADD), m(Attribute.LUCK, 3, BonusType.ADD)),
    AMENO_UZUME_II("天鈿女ノ舞 II", 2, 1000, 450, m(Attribute.RECOVER_HP, 4, BonusType.ADD), m(Attribute.LUCK, 6, BonusType.ADD)),
    AMENO_UZUME_III("天鈿女ノ舞 III", 3, 1000, 180, m(Attribute.RECOVER_HP, 6, BonusType.ADD), m(Attribute.LUCK, 9, BonusType.ADD)),
    AMENO_UZUME_IV("天鈿女ノ舞 IV", 4, 1000, 45, m(Attribute.RECOVER_HP, 8, BonusType.ADD), m(Attribute.LUCK, 12, BonusType.ADD)),
    AMENO_UZUME_V("天鈿女ノ舞 V", 5, 1000, 10, m(Attribute.RECOVER_HP, 10, BonusType.ADD), m(Attribute.LUCK, 15, BonusType.ADD)),
    // region 32 狐
    KITSUNE_MEGUMI_I("狐ノ恵み I", 1, 1000, 1000, m(Attribute.RECOVER_MP, 2, BonusType.ADD), m(Attribute.LUCK, 2, BonusType.ADD)),
    KITSUNE_MEGUMI_II("狐ノ恵み II", 2, 1000, 450, m(Attribute.RECOVER_MP, 4, BonusType.ADD), m(Attribute.LUCK, 4, BonusType.ADD)),
    KITSUNE_MEGUMI_III("狐ノ恵み III", 3, 1000, 180, m(Attribute.RECOVER_MP, 6, BonusType.ADD), m(Attribute.LUCK, 6, BonusType.ADD)),
    KITSUNE_MEGUMI_IV("狐ノ恵み IV", 4, 1000, 45, m(Attribute.RECOVER_MP, 8, BonusType.ADD), m(Attribute.LUCK, 8, BonusType.ADD)),
    KITSUNE_MEGUMI_V("狐ノ恵み V", 5, 1000, 10, m(Attribute.RECOVER_MP, 10, BonusType.ADD), m(Attribute.LUCK, 10, BonusType.ADD)),
    // region 33 濡壁
    NUREKABE_KABE_I("濡壁ノ堅守 I", 1, 1000, 1000, m(Attribute.PHY_DEFENSE, 2, BonusType.ADD), m(Attribute.DAMAGE_REDUCTION, 0.01, BonusType.ADD)),
    NUREKABE_KABE_II("濡壁ノ堅守 II", 2, 1000, 450, m(Attribute.PHY_DEFENSE, 4, BonusType.ADD), m(Attribute.DAMAGE_REDUCTION, 0.02, BonusType.ADD)),
    NUREKABE_KABE_III("濡壁ノ堅守 III", 3, 1000, 180, m(Attribute.PHY_DEFENSE, 6, BonusType.ADD), m(Attribute.DAMAGE_REDUCTION, 0.03, BonusType.ADD)),
    NUREKABE_KABE_IV("濡壁ノ堅守 IV", 4, 1000, 45, m(Attribute.PHY_DEFENSE, 8, BonusType.ADD), m(Attribute.DAMAGE_REDUCTION, 0.04, BonusType.ADD)),
    NUREKABE_KABE_V("濡壁ノ堅守 V", 5, 1000, 10, m(Attribute.PHY_DEFENSE, 10, BonusType.ADD), m(Attribute.DAMAGE_REDUCTION, 0.05, BonusType.ADD)),
    // region 34 土蜘蛛
    TSUCHIGUMO_ASA_I("土蜘蛛ノ罠糸 I", 1, 1000, 1000, m(Attribute.BLEED_CHANCE, 0.02, BonusType.ADD), m(Attribute.POISON_CHANCE, 0.02, BonusType.ADD)),
    TSUCHIGUMO_ASA_II("土蜘蛛ノ罠糸 II", 2, 1000, 450, m(Attribute.BLEED_CHANCE, 0.04, BonusType.ADD), m(Attribute.POISON_CHANCE, 0.04, BonusType.ADD)),
    TSUCHIGUMO_ASA_III("土蜘蛛ノ罠糸 III", 3, 1000, 180, m(Attribute.BLEED_CHANCE, 0.06, BonusType.ADD), m(Attribute.POISON_CHANCE, 0.06, BonusType.ADD)),
    TSUCHIGUMO_ASA_IV("土蜘蛛ノ罠糸 IV", 4, 1000, 45, m(Attribute.BLEED_CHANCE, 0.08, BonusType.ADD), m(Attribute.POISON_CHANCE, 0.08, BonusType.ADD)),
    TSUCHIGUMO_ASA_V("土蜘蛛ノ罠糸 V", 5, 1000, 10, m(Attribute.BLEED_CHANCE, 0.10, BonusType.ADD), m(Attribute.POISON_CHANCE, 0.10, BonusType.ADD));
    // endregion

    // ------------------------------------------------------------
    private final String displayName;
    private final int rank;
    private final int affixWeight;
    private final int levelWeight;
    private final Modifier[] modifiers;

    EquipmentAffix(String displayName, int rank, int affixWeight, int levelWeight, Modifier... modifiers) {
        this.displayName = displayName;
        this.rank = rank;
        this.affixWeight = affixWeight;
        this.levelWeight = levelWeight;
        this.modifiers = modifiers;
    }

    /**
     * 按词条组affixWeight→组内levelWeight双层权重抽取
     */
    public static EquipmentAffix random(Random rng) {
        // Step1: 构建组列表和权重
        Map<String, List<EquipmentAffix>> groupMap = new LinkedHashMap<>();
        Map<String, Integer> groupWeight = new LinkedHashMap<>();
        for (EquipmentAffix a : values()) {
            String group = a.name().substring(0, a.name().lastIndexOf('_'));
            groupMap.computeIfAbsent(group, k -> new ArrayList<>()).add(a);
            groupWeight.put(group, a.affixWeight); // 每组所有等级affixWeight一样
        }
        // Step2: 按affixWeight随机一组
        int totalAffix = 0;
        for (Integer w : groupWeight.values()) {
            totalAffix += w;
        }
        int roll = rng.nextInt(totalAffix);
        int acc = 0;
        String chosenGroup = null;
        for (Map.Entry<String, Integer> entry : groupWeight.entrySet()) {
            acc += entry.getValue();
            if (roll < acc) {
                chosenGroup = entry.getKey();
                break;
            }
        }
        if (chosenGroup == null) {
            return values()[0];
        }
        // Step3: 组内按levelWeight随机
        List<EquipmentAffix> groupList = groupMap.get(chosenGroup);
        int totalLevel = 0;
        for (EquipmentAffix affix : groupList) {
            totalLevel += affix.levelWeight;
        }
        int roll2 = rng.nextInt(totalLevel);
        acc = 0;
        for (EquipmentAffix affix : groupList) {
            acc += affix.levelWeight;
            if (roll2 < acc) {
                return affix;
            }
        }
        return groupList.get(0);
    }

    /**
     * 获取词条显示名
     */
    public String getDisplayName() {
        return displayName;
    }

    public int getRank() {
        return rank;
    }

    /**
     * 获取指定属性的加成值, 未包含时返回0
     * <p>
     * 包含加成计算方式
     *
     */
    public double getAttributeValue(Attribute attribute, BonusType type) {
        double result = 0;
        for (Modifier modifier : modifiers) {
            if (modifier.attribute == attribute && modifier.type == type) {
                result += modifier.value;
            }
        }
        return result;
    }

    /**
     * 获取所有属性的加成值, 未包含时返回0
     * <p>
     * 包含加成计算方式
     */
    public Map<Attribute, Map<BonusType, Double>> getAttributeTypeMap() {
        Map<Attribute, Map<BonusType, Double>> map = new HashMap<>();
        for (Modifier modifier : modifiers) {
            map
                    .computeIfAbsent(modifier.attribute, k -> new HashMap<>())
                    .merge(modifier.type, modifier.value, Double::sum);
        }
        return map;
    }

    /**
     * 获取指定计算方式的加成值, 未包含时返回0
     * <p>
     * 包含加成计算方式
     */
    public Map<Attribute, Double> getAttributeMap(BonusType type) {
        Map<Attribute, Double> map = new HashMap<>();
        for (Modifier modifier : modifiers) {
            if (modifier.type == type) {
                map.merge(modifier.attribute, modifier.value, Double::sum);
            }
        }
        return map;
    }

    /**
     * 返回所有属性与数值Map
     */
    public Map<Attribute, Double> getAttributeMap() {
        Map<Attribute, Double> map = new HashMap<>();
        for (Modifier modifier : modifiers) {
            Attribute attr = modifier.attribute;
            double v = modifier.value;
            map.merge(attr, v, Double::sum);
        }
        return map;
    }

    /**
     * 属性加值结构体
     */
    private static class Modifier {

        Attribute attribute;
        double value;
        BonusType type;

        Modifier(Attribute attr, double v, BonusType type) {
            this.attribute = attr;
            this.value = v;
            this.type = type;
        }
    }

    private static Modifier m(Attribute attr, double val, BonusType type) {
        return new Modifier(attr, val, type);
    }

    /**
     * 装备属性类型
     */
    public enum Attribute {
        STRENGTH("力量", ""),
        AGILITY("敏捷", ""),
        INTELLIGENCE("智力", ""),
        MAX_HEALTH_POINT("最大生命", ""),
        MAX_MANA_POINT("最大魔法", ""),
        PHY_DEFENSE("物理防御", ""),
        MAGIC_DEFENSE("魔法防御", ""),
        RECOVER_HP("生命恢复", "/回合"),
        RECOVER_MP("魔法恢复", "/回合"),
        CRITICAL_HIT_RATE("暴击率", "%"),
        EVASION("闪避", "%"),
        INCDAMAGE("命中", "%"),
        LUCK("幸运", ""),
        FORTITUDE("坚韧", ""),
        BLEED_CHANCE("出血几率", "%"),
        POISON_CHANCE("中毒几率", "%"),
        BURN_CHANCE("灼烧几率", "%"),
        FREEZE_CHANCE("冰冻几率", "%"),
        SPIRIT_POWER("灵力", ""),
        DAMAGE_REDUCTION("伤害减免", "%"),
        HEALING_EFFECTIVENESS("治疗效果", "%"),
        GOLD_FIND("金币获取", "%");

        private final String displayNameZh;
        private final String unit;

        Attribute(String displayNameZh, String unit) {
            this.displayNameZh = displayNameZh;
            this.unit = unit;
        }

        public String getDisplayNameZh() {
            return displayNameZh;
        }

        public String getUnit() {
            return unit;
        }
    }

    public enum BonusType {
        ADD, // 直接加算
        MULTIPLY   // 百分比乘算（如0.15表示+15%）
    }
}
