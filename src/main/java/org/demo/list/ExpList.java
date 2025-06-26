package org.demo.list;

public enum ExpList {
    LEVEL_1(1, 0),
    LEVEL_2(2, 19),
    LEVEL_3(3, 47),
    LEVEL_4(4, 87),
    LEVEL_5(5, 140),
    LEVEL_6(6, 205),
    LEVEL_7(7, 283),
    LEVEL_8(8, 373),
    LEVEL_9(9, 476),
    LEVEL_10(10, 592),
    LEVEL_11(11, 720),
    LEVEL_12(12, 861),
    LEVEL_13(13, 1015),
    LEVEL_14(14, 1180),
    LEVEL_15(15, 1360),
    LEVEL_16(16, 1550),
    LEVEL_17(17, 1755),
    LEVEL_18(18, 1970),
    LEVEL_19(19, 2200),
    LEVEL_20(20, 2442),
    LEVEL_21(21, 2696),
    LEVEL_22(22, 2963),
    LEVEL_23(23, 3243),
    LEVEL_24(24, 3535),
    LEVEL_25(25, 3840),
    LEVEL_26(26, 4157),
    LEVEL_27(27, 4490),
    LEVEL_28(28, 4829),
    LEVEL_29(29, 5184),
    LEVEL_30(30, 5552),
    LEVEL_31(31, 5932),
    LEVEL_32(32, 6325),
    LEVEL_33(33, 6731),
    LEVEL_34(34, 7150),
    LEVEL_35(35, 7580),
    LEVEL_36(36, 8020),
    LEVEL_37(37, 8480),
    LEVEL_38(38, 8950),
    LEVEL_39(39, 9430),
    LEVEL_40(40, 9920),
    LEVEL_41(41, 10430),
    LEVEL_42(42, 10950),
    LEVEL_43(43, 11480),
    LEVEL_44(44, 12020),
    LEVEL_45(45, 12580),
    LEVEL_46(46, 13150),
    LEVEL_47(47, 13730),
    LEVEL_48(48, 14325),
    LEVEL_49(49, 14930),
    LEVEL_50(50, 15550),
    LEVEL_51(51, 16180),
    LEVEL_52(52, 16830),
    LEVEL_53(53, 17490),
    LEVEL_54(54, 18160),
    LEVEL_55(55, 18840),
    LEVEL_56(56, 19550),
    LEVEL_57(57, 20240),
    LEVEL_58(58, 20970),
    LEVEL_59(59, 21700),
    LEVEL_60(60, 22450),
    LEVEL_61(61, 23200),
    LEVEL_62(62, 23980),
    LEVEL_63(63, 24750),
    LEVEL_64(64, 25550),
    LEVEL_65(65, 26360),
    LEVEL_66(66, 27180),
    LEVEL_67(67, 28010),
    LEVEL_68(68, 28860),
    LEVEL_69(69, 29720),
    LEVEL_70(70, 30600),
    LEVEL_71(71, 31480),
    LEVEL_72(72, 32370),
    LEVEL_73(73, 33280),
    LEVEL_74(74, 34200),
    LEVEL_75(75, 35140),
    LEVEL_76(76, 36090),
    LEVEL_77(77, 37050),
    LEVEL_78(78, 38020),
    LEVEL_79(79, 39000),
    LEVEL_80(80, 40000),
    LEVEL_81(81, 41010),
    LEVEL_82(82, 42040),
    LEVEL_83(83, 43070),
    LEVEL_84(84, 44120),
    LEVEL_85(85, 45180),
    LEVEL_86(86, 46250),
    LEVEL_87(87, 47340),
    LEVEL_88(88, 48430),
    LEVEL_89(89, 49550),
    LEVEL_90(90, 50670),
    LEVEL_91(91, 51800),
    LEVEL_92(92, 52960),
    LEVEL_93(93, 54120),
    LEVEL_94(94, 55300),
    LEVEL_95(95, 56480),
    LEVEL_96(96, 57670),
    LEVEL_97(97, 58890),
    LEVEL_98(98, 60110),
    LEVEL_99(99, 61350),
    LEVEL_100(100, 62600);

    private final int level;
    private final int minExp;

    /**
     * 构造一个经验等级列表。
     *
     * @param level 等级
     * @param minExp 当前等级所需的最小经验值
     */
    ExpList(int level, int minExp) {
        this.level = level;
        this.minExp = minExp;
    }

    /**
     * 获取当前等级。
     *
     * @return 等级
     */
    public int getLevel() {
        return level;
    }

    /**
     * 获取当前等级所需的最小经验值。
     *
     * @return 最小经验值
     */
    public int getMinExp() {
        return minExp;
    }

    /**
     * 根据总经验值获取对应的等级。
     *
     * @param totalExp 总经验值
     * @return 对应的等级
     */
    public static ExpList getLevelByExp(int totalExp) {
        for (int i = values().length - 1; i >= 0; i--) {
            ExpList lv = values()[i];
            if (totalExp >= lv.minExp) {
                return lv;
            }
        }
        return LEVEL_1; // 默认最低等级
    }

    /**
     * 根据等级获取对应的必要经验值。
     *
     * @param level 现在等级
     * @return 对应的最少经验值
     */
    public static ExpList getExpByLevel(int level) {
        for (int i = values().length - 1; i >= 0; i--) {
            ExpList lv = values()[i];
            if (level >= lv.level) {
                return lv;
            }
        }
        return LEVEL_1; // 默认最低等级
    }
}
