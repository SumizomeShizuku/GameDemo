package org.demo.data;

import org.demo.list.EthnicityList;
import org.demo.list.JobList;

public class Constants {

    // 默认等级
    public static final int DEFAULT_EXP = 0;
    // 基础冷却
    public static final double DEFAULT_COOL_DOWN = 1;
    // 基础暴击
    public static final double DEFAULT_CRITICAL_HIT_RATE = 0.025;
    // 总种族数
    // 获取种族列表的长度
    public static final int TOTAL_ETHNICITY_NUM = EthnicityList.values().length;
    // 默认种族
    // 默认人族
    public static final EthnicityList DEFAULT_ETHNICITY = EthnicityList.GRAVENTH;
    // 总职业数
    // 获取职业列表的长度
    public static final int TOTAL_JOB_NUM = JobList.values().length;
    // 默认职业
    public static final JobList DEFAULT_JOB = JobList.WARRIOR;
}
