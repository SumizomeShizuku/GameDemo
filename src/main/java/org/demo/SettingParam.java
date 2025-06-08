package org.demo;

import org.demo.List.EthnicityList;
import org.demo.List.JobList;
import org.demo.data.Constants;
import org.demo.dto.ParameterDto;

public class SettingParam {

    public static ParameterDto setPlayerEthnicity(int ethnicityNum, ParameterDto player) {
        EthnicityList ethnicity = Constants.DEFAULT_ETHNICITY;
        if (ethnicityNum > 0 && ethnicityNum <= Constants.TOTAL_ETHNICITY_NUM) {
            ethnicity = EthnicityList.getEthnicity(ethnicityNum);
        }
        player.setHealthPoint(ethnicity.getHealthPoint());
        player.setManaPoint(ethnicity.getManaPoint());
        player.setStrength(ethnicity.getStrength());
        player.setAgility(ethnicity.getAgility());
        player.setIntelligence(ethnicity.getIntelligence());
        player.setEthnicity(ethnicity);
        return player;
    }

    public static ParameterDto setPlayerJob(int jobNum, ParameterDto player) {
        JobList job = Constants.DEFAULT_JOB;
        if (jobNum > 0 && jobNum <= Constants.TOTAL_ETHNICITY_NUM) {
            job = JobList.getJob(jobNum);
        }
        player.setExp(job.getExp());
        player.setHealthPoint(player.getHealthPoint() + job.getHealthPoint());
        player.setManaPoint(player.getManaPoint() + job.getManaPoint());
        player.setMoveSpeed(job.getMoveSpeed());
        player.setStrength(player.getStrength() + job.getStrength());
        player.setAgility(player.getAgility() + job.getAgility());
        player.setIntelligence(player.getIntelligence() + job.getIntelligence());
        player.setJob(job);
        player.setCommonCoolDown(Constants.DEFAULT_COOL_DOWN);
        player.setCriticalHitRate(Constants.DEFAULT_CRITICAL_HIT_RATE);
        return player;
    }

}
