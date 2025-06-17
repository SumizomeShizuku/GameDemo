package org.demo.list;

public enum ExpList {
    LEVEL_1(1, 0),
    LEVEL_2(2, 190),
    LEVEL_3(3, 470),
    LEVEL_4(4, 870),
    LEVEL_5(5, 1400),
    LEVEL_6(6, 2050),
    LEVEL_7(7, 2830),
    LEVEL_8(8, 3730),
    LEVEL_9(9, 4760),
    LEVEL_10(10, 5920),
    LEVEL_11(11, 7200),
    LEVEL_12(12, 8610),
    LEVEL_13(13, 10150),
    LEVEL_14(14, 11800),
    LEVEL_15(15, 13600),
    LEVEL_16(16, 15500),
    LEVEL_17(17, 17550),
    LEVEL_18(18, 19700),
    LEVEL_19(19, 22000),
    LEVEL_20(20, 24420),
    LEVEL_21(21, 26960),
    LEVEL_22(22, 29630),
    LEVEL_23(23, 32430),
    LEVEL_24(24, 35350),
    LEVEL_25(25, 38400),
    LEVEL_26(26, 41570),
    LEVEL_27(27, 44900),
    LEVEL_28(28, 48290),
    LEVEL_29(29, 51840),
    LEVEL_30(30, 55520),
    LEVEL_31(31, 59320),
    LEVEL_32(32, 63250),
    LEVEL_33(33, 67310),
    LEVEL_34(34, 71500),
    LEVEL_35(35, 75800),
    LEVEL_36(36, 8020),
    LEVEL_37(37, 84800),
    LEVEL_38(38, 89500),
    LEVEL_39(39, 94300),
    LEVEL_40(40, 99200),
    LEVEL_41(41, 104300),
    LEVEL_42(42, 109500),
    LEVEL_43(43, 114800),
    LEVEL_44(44, 120200),
    LEVEL_45(45, 125800),
    LEVEL_46(46, 131500),
    LEVEL_47(47, 137300),
    LEVEL_48(48, 143250),
    LEVEL_49(49, 149300),
    LEVEL_50(50, 155500),
    LEVEL_51(51, 161800),
    LEVEL_52(52, 168300),
    LEVEL_53(53, 174900),
    LEVEL_54(54, 181600),
    LEVEL_55(55, 188400),
    LEVEL_56(56, 195500),
    LEVEL_57(57, 202400),
    LEVEL_58(58, 209700),
    LEVEL_59(59, 217000),
    LEVEL_60(60, 224500),
    LEVEL_61(61, 232000),
    LEVEL_62(62, 239800),
    LEVEL_63(63, 247500),
    LEVEL_64(64, 255500),
    LEVEL_65(65, 263600),
    LEVEL_66(66, 271800),
    LEVEL_67(67, 280100),
    LEVEL_68(68, 288600),
    LEVEL_69(69, 297200),
    LEVEL_70(70, 306000),
    LEVEL_71(71, 314800),
    LEVEL_72(72, 323700),
    LEVEL_73(73, 332800),
    LEVEL_74(74, 342000),
    LEVEL_75(75, 351400),
    LEVEL_76(76, 360900),
    LEVEL_77(77, 370500),
    LEVEL_78(78, 380200),
    LEVEL_79(79, 390000),
    LEVEL_80(80, 400000),
    LEVEL_81(81, 410100),
    LEVEL_82(82, 420400),
    LEVEL_83(83, 430700),
    LEVEL_84(84, 441200),
    LEVEL_85(85, 451800),
    LEVEL_86(86, 462500),
    LEVEL_87(87, 473400),
    LEVEL_88(88, 484300),
    LEVEL_89(89, 495500),
    LEVEL_90(90, 506700),
    LEVEL_91(91, 518000),
    LEVEL_92(92, 529600),
    LEVEL_93(93, 541200),
    LEVEL_94(94, 553000),
    LEVEL_95(95, 564800),
    LEVEL_96(96, 576700),
    LEVEL_97(97, 588900),
    LEVEL_98(98, 601100),
    LEVEL_99(99, 613500),
    LEVEL_100(100, 626000);

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
