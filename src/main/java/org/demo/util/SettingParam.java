// package org.demo.util;

// import org.demo.data.Constants;
// import org.demo.dto.PlayerModelDto;
// import org.demo.list.EthnicityList;
// import org.demo.list.JobList;

// public class SettingParam {

//     public static PlayerModelDto setPlayerEthnicity(int ethnicityNum, PlayerModelDto player) {
//         EthnicityList ethnicity = Constants.DEFAULT_ETHNICITY;
//         if (ethnicityNum > 0 && ethnicityNum <= Constants.TOTAL_ETHNICITY_NUM) {
//             ethnicity = EthnicityList.getEthnicity(ethnicityNum);
//         }
//         player.setMaxHealthPoint(ethnicity.getHealthPoint());
//         player.setMaxManaPoint(ethnicity.getManaPoint());
//         player.setStrength(ethnicity.getStrength());
//         player.setAgility(ethnicity.getAgility());
//         player.setIntelligence(ethnicity.getIntelligence());
//         player.setEthnicity(ethnicity);
//         return player;
//     }

//     public static PlayerModelDto setPlayerJob(int jobNum, PlayerModelDto player) {
//         JobList job = Constants.DEFAULT_JOB;
//         if (jobNum > 0 && jobNum <= Constants.TOTAL_JOB_NUM) {
//             job = JobList.getJob(jobNum);
//         }
//         player.setExp(job.getExp());
//         player.setLevel(job.getLevel());
//         player.setMaxHealthPoint(player.getMaxHealthPoint() + job.getHealthPoint());
//         player.setMaxManaPoint(player.getMaxManaPoint() + job.getManaPoint());
//         player.setMoveSpeed(job.getMoveSpeed());
//         player.setJob(job);
//         player.setCommonCoolDown(Constants.DEFAULT_COOL_DOWN);
//         player.setCriticalHitRate(Constants.DEFAULT_CRITICAL_HIT_RATE);
//         return player;
//     }

// }
