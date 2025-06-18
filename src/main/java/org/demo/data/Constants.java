package org.demo.data;

import org.demo.list.EthnicityList;
import org.demo.list.JobList;

public class Constants {

    /**
     * 默认等级 : 0
     */
    public static final int DEFAULT_EXP = 0;
    /**
     * 基础冷却 : 1
     */
    public static final double DEFAULT_COOL_DOWN = 1;
    /**
     * 基础暴击 : 2.5%
     */
    public static final double DEFAULT_CRITICAL_HIT_RATE = 0.025;
    /**
     * 总种族数
     */
    public static final int TOTAL_ETHNICITY_NUM = EthnicityList.values().length;
    /**
     * 默认种族 : 人族
     */
    public static final EthnicityList DEFAULT_ETHNICITY = EthnicityList.GRAVENTH;
    /**
     * 总职业数
     */
    public static final int TOTAL_JOB_NUM = JobList.values().length;
    /**
     * 默认职业 : 战士
     */
    public static final JobList DEFAULT_JOB = JobList.WARRIOR;
    /**
     * 物理攻击
     */
    public static final String PHYSICS_DAMAGE = "物理伤害";
    /**
     * 魔法攻击
     */
    public static final String MAGIC_DAMAGE = "魔法伤害";
    /**
     * 混合伤害
     */
    public static final String MIX_DAMAGE = "混合伤害";
    /**
     * 未知类型伤害
     */
    public static final String UNDEFINED_DAMAGE = "未知类型伤害";
}
