package org.demo.list;

public enum EnemyEthnicityList {
    /**
     * 空尾族
     */
    KITSARIEL("空尾族"),
    /**
     * 人族
     */
    GRAVENTH("人族"),
    /**
     * 林语族
     */
    SYLVARIN("林语族"),
    /**
     * 亡影族
     */
    THARNYX("亡影族"),
    EE00001("哥布林"),
    EE00002("史莱姆"),
    EE00003("训练用假人");

    private final String ethnicityZh;

    /**
     * 构造一个带有基础属性的职业类型。
     *
     * @param ethnicityZh 种族名(中文)
     */
    private EnemyEthnicityList(String ethnicityZh) {
        this.ethnicityZh = ethnicityZh;
    }

    public String getEthnicityZh() {
        return ethnicityZh;
    }
}
