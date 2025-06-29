package org.demo.dto;

import java.util.List;

import org.demo.list.EnemyEthnicityList;

/**
 * 用于读取敌人JSON配置的数据结构
 */
public class EnemyJsonDto {

    public String id;
    public String name;
    public EnemyEthnicityList ethnicity;
    public int level;
    public int maxHp;
    public int attack;
    public int strength;
    public int agility;
    public int intelligence;
    public double criticalHitRate;
    public int phyDefense;
    public int magicDefense;
    public int dropExp;
    public double dropRate;
    public List<DropEntry> drops;
    public List<String> areas;
    public List<String> skills;

    /**
     * 敌人掉落物配置，用于JSON映射
     */
    public static class DropEntry {

        public String itemId;
        public double weight;
        public int minQty;
        public int maxQty;
    }
}
