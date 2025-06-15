package org.demo.util;

import org.demo.data.Constants;
import org.demo.dto.PlayerModelDto;
import org.demo.list.EthnicityList;
import org.demo.list.JobList;

public class PlayerFactory {

    public static PlayerModelDto createPlayer(String firstName, String lastName, EthnicityList ethnicity, JobList job) {
        PlayerModelDto player = new PlayerModelDto();
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setEthnicity(ethnicity);
        player.setJob(job);

        // 设置基础经验和等级（来自职业）
        player.setExp(job.getExp());
        player.setLevel(job.getLevel());

        // HP / MP = 职业基础值 + 种族加成
        player.setMaxHealthPoint(job.getHealthPoint() + ethnicity.getHealthPoint());
        player.setMaxManaPoint(job.getManaPoint() + ethnicity.getManaPoint());
        player.setMoveSpeed(job.getMoveSpeed());

        // 三维属性 = 职业成长权重 * 分配点数 + 种族加成
        double[] weights = job.getGrowthWeights();
        int totalPoints = 30;

        int strength = (int) (weights[0] * totalPoints) + ethnicity.getStrength();
        int agility = (int) (weights[1] * totalPoints) + ethnicity.getAgility();
        int intelligence = (int) (weights[2] * totalPoints) + ethnicity.getIntelligence();

        player.setStrength(strength);
        player.setAgility(agility);
        player.setIntelligence(intelligence);

        // 通用基础属性
        player.setRecoverHP(0);
        player.setRecoverMP(0);
        player.setCommonCoolDown(Constants.DEFAULT_COOL_DOWN);
        player.setBaseAttribute(0.0);
        player.setCriticalHitRate(Constants.DEFAULT_CRITICAL_HIT_RATE);

        return player;
    }

    // 示例快捷方法
    public static PlayerModelDto createDefaultArcherElf(String firstName, String lastName) {
        return createPlayer(firstName, lastName, EthnicityList.SYLVARIN, JobList.ARCHER);
    }

    public static PlayerModelDto createDefaultMageUndead(String firstName, String lastName) {
        return createPlayer(firstName, lastName, EthnicityList.THARNYX, JobList.MAGE);
    }

}
