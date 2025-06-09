package org.demo.list;

public enum ExpList {
    LEVEL_1(1, 0),
    LEVEL_2(2, 191),
    LEVEL_3(3, 466),
    LEVEL_4(4, 867),
    LEVEL_5(5, 1394),
    LEVEL_6(6, 2047),
    LEVEL_7(7, 2826),
    LEVEL_8(8, 3731),
    LEVEL_9(9, 4762),
    LEVEL_10(10, 5919),
    LEVEL_11(11, 7202),
    LEVEL_12(12, 8611),
    LEVEL_13(13, 10146),
    LEVEL_14(14, 11807),
    LEVEL_15(15, 13594),
    LEVEL_16(16, 15507),
    LEVEL_17(17, 17546),
    LEVEL_18(18, 19711),
    LEVEL_19(19, 22002),
    LEVEL_20(20, 24419),
    LEVEL_21(21, 26962),
    LEVEL_22(22, 29631),
    LEVEL_23(23, 32426),
    LEVEL_24(24, 35347),
    LEVEL_25(25, 38394),
    LEVEL_26(26, 41567),
    LEVEL_27(27, 44866),
    LEVEL_28(28, 48291),
    LEVEL_29(29, 51842),
    LEVEL_30(30, 55519),
    LEVEL_31(31, 59322),
    LEVEL_32(32, 63251),
    LEVEL_33(33, 67306),
    LEVEL_34(34, 71487),
    LEVEL_35(35, 75794),
    LEVEL_36(36, 80227),
    LEVEL_37(37, 84786),
    LEVEL_38(38, 89471),
    LEVEL_39(39, 94282),
    LEVEL_40(40, 99219),
    LEVEL_41(41, 104282),
    LEVEL_42(42, 109471),
    LEVEL_43(43, 114786),
    LEVEL_44(44, 120227),
    LEVEL_45(45, 125794),
    LEVEL_46(46, 131487),
    LEVEL_47(47, 137306),
    LEVEL_48(48, 143251),
    LEVEL_49(49, 149322),
    LEVEL_50(50, 155519),
    LEVEL_51(51, 161842),
    LEVEL_52(52, 168291),
    LEVEL_53(53, 174866),
    LEVEL_54(54, 181567),
    LEVEL_55(55, 188394),
    LEVEL_56(56, 195347),
    LEVEL_57(57, 202426),
    LEVEL_58(58, 209631),
    LEVEL_59(59, 216962),
    LEVEL_60(60, 224419),
    LEVEL_61(61, 232002),
    LEVEL_62(62, 239711),
    LEVEL_63(63, 247546),
    LEVEL_64(64, 255507),
    LEVEL_65(65, 263594),
    LEVEL_66(66, 271807),
    LEVEL_67(67, 280146),
    LEVEL_68(68, 288611),
    LEVEL_69(69, 297202),
    LEVEL_70(70, 305919),
    LEVEL_71(71, 314762),
    LEVEL_72(72, 323731),
    LEVEL_73(73, 332826),
    LEVEL_74(74, 342047),
    LEVEL_75(75, 351394),
    LEVEL_76(76, 360867),
    LEVEL_77(77, 370466),
    LEVEL_78(78, 380191),
    LEVEL_79(79, 390042),
    LEVEL_80(80, 400019),
    LEVEL_81(81, 410122),
    LEVEL_82(82, 420351),
    LEVEL_83(83, 430706),
    LEVEL_84(84, 441187),
    LEVEL_85(85, 451794),
    LEVEL_86(86, 462527),
    LEVEL_87(87, 473386),
    LEVEL_88(88, 484371),
    LEVEL_89(89, 495482),
    LEVEL_90(90, 506719),
    LEVEL_91(91, 518082),
    LEVEL_92(92, 529571),
    LEVEL_93(93, 541186),
    LEVEL_94(94, 552927),
    LEVEL_95(95, 564794),
    LEVEL_96(96, 576787),
    LEVEL_97(97, 588906),
    LEVEL_98(98, 601151),
    LEVEL_99(99, 613522),
    LEVEL_100(100, 626019);

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
}
