package org.demo.factory;

import org.demo.data.Constants;
import org.demo.dto.PlayerModelDto;
import org.demo.list.EthnicityList;
import org.demo.list.JobList;

public class PlayerFactory {

    public static PlayerModelDto createPlayer(String firstName, String lastName, int ethnicityNum, int jobNum) {
        PlayerModelDto player = new PlayerModelDto();

        EthnicityList ethnicity = Constants.DEFAULT_ETHNICITY;
        if (ethnicityNum > 0 && ethnicityNum <= Constants.TOTAL_ETHNICITY_NUM) {
            ethnicity = EthnicityList.getEthnicity(ethnicityNum);
        }

        JobList job = Constants.DEFAULT_JOB;
        if (jobNum > 0 && jobNum <= Constants.TOTAL_JOB_NUM) {
            job = JobList.getJob(jobNum);
        }
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

        player.setStrength(ethnicity.getStrength());
        player.setAgility(ethnicity.getAgility());
        player.setIntelligence(ethnicity.getIntelligence());

        // 通用基础属性
        player.setCurrentHealthPoint(player.getMaxHealthPoint());
        player.setCurrentManaPoint(player.getMaxManaPoint());
        player.setRecoverHP(0);
        player.setRecoverMP(0);
        player.setCommonCoolDown(Constants.DEFAULT_COOL_DOWN);
        player.setBaseAttribute(0.0);
        player.setCriticalHitRate(Constants.DEFAULT_CRITICAL_HIT_RATE);

        return player;
    }

    public static PlayerModelDto createPlayerTemplate(String firstName, String lastName, EthnicityList ethnicity, JobList job) {
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

        player.setStrength(ethnicity.getStrength());
        player.setAgility(ethnicity.getAgility());
        player.setIntelligence(ethnicity.getIntelligence());

        // 通用基础属性
        player.setCurrentHealthPoint(player.getMaxHealthPoint());
        player.setCurrentManaPoint(player.getMaxManaPoint());
        player.setRecoverHP(0);
        player.setRecoverMP(0);
        player.setCommonCoolDown(Constants.DEFAULT_COOL_DOWN);
        player.setBaseAttribute(0.0);
        player.setCriticalHitRate(Constants.DEFAULT_CRITICAL_HIT_RATE);

        return player;
    }

    // 示例快捷方法
    public static PlayerModelDto createDefaultArcherElf(String firstName, String lastName) {
        return createPlayerTemplate(firstName, lastName, EthnicityList.SYLVARIN, JobList.ARCHER);
    }

    public static PlayerModelDto createDefaultMageUndead(String firstName, String lastName) {
        return createPlayerTemplate(firstName, lastName, EthnicityList.THARNYX, JobList.MAGE);
    }

}
