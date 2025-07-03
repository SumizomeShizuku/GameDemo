package org.demo.factory;

import org.demo.constants.Constants;
import org.demo.dto.PlayerModelDto;
import org.demo.list.EthnicityList;
import org.demo.list.JobList;

public class PlayerFactory {

    /**
     * 根据玩家种族和职业编号创建玩家对象。
     *
     * @param firstName 玩家名
     * @param lastName 玩家姓
     * @param ethnicityNum 种族编号( 1~4 )
     * @param jobNum 职业编号( 1~4 )
     * @return 构建完成的玩家数据传输对象
     */
    public static Player createPlayer(String firstName, String lastName, int ethnicityNum, int jobNum) {

        PlayerModelDto playerModelDto = new PlayerModelDto();
        Player player = new Player(playerModelDto);

        EthnicityList ethnicity = Constants.DEFAULT_ETHNICITY;
        if (ethnicityNum > 0 && ethnicityNum <= Constants.TOTAL_ETHNICITY_NUM) {
            ethnicity = EthnicityList.getEthnicity(ethnicityNum);
        }

        JobList job = Constants.DEFAULT_JOB;
        if (jobNum > 0 && jobNum <= Constants.TOTAL_JOB_NUM) {
            job = JobList.getJob(jobNum);
        }
        playerModelDto.setFirstName(firstName);
        playerModelDto.setLastName(lastName);
        playerModelDto.setEthnicity(ethnicity);
        playerModelDto.setJob(job);

        // 设置基础经验和等级( 来自职业 )
        playerModelDto.setExp(job.getExp());
        playerModelDto.setLevel(job.getLevel());

        // HP / MP = 职业基础值 + 种族加成
        playerModelDto.setMaxHealthPoint(job.getHealthPoint() + ethnicity.getHealthPoint());
        playerModelDto.setMaxManaPoint(job.getManaPoint() + ethnicity.getManaPoint());
        playerModelDto.setMoveSpeed(job.getMoveSpeed());

        playerModelDto.setStrength(ethnicity.getStrength());
        playerModelDto.setAgility(ethnicity.getAgility());
        playerModelDto.setIntelligence(ethnicity.getIntelligence());

        // 通用基础属性
        // playerModel.setMaxHealthPoint(player.getMaxHealthPoint());
        // playerModel.setMaxManaPoint(player.getMaxHealthPoint());
        // playerModelDto.setPhyDefense(ethnicityNum);
        // playerModelDto.setMagicDefense(ethnicityNum);
        player.setCurrentHealthPoint(playerModelDto.getMaxHealthPoint());
        player.setCurrentManaPoint(playerModelDto.getMaxManaPoint());
        playerModelDto.setRecoverHP(0);
        playerModelDto.setRecoverMP(0);
        playerModelDto.setCommonCoolDown(Constants.DEFAULT_COOL_DOWN);
        playerModelDto.setCriticalHitRate(Constants.DEFAULT_CRITICAL_HIT_RATE);
        playerModelDto.setActionsPerTurn(1);
        player.refreshTotalAttributes();
        return player;
    }

    public static Player createPlayerTemplate(String firstName, String lastName, EthnicityList ethnicity, JobList job) {
        PlayerModelDto playerModelDto = new PlayerModelDto();
        Player player = new Player(playerModelDto);

        playerModelDto.setFirstName(firstName);
        playerModelDto.setLastName(lastName);
        playerModelDto.setEthnicity(ethnicity);
        playerModelDto.setJob(job);

        // 设置基础经验和等级( 来自职业 )
        playerModelDto.setExp(job.getExp());
        playerModelDto.setLevel(job.getLevel());

        // HP / MP = 职业基础值 + 种族加成
        playerModelDto.setMaxHealthPoint(job.getHealthPoint() + ethnicity.getHealthPoint());
        playerModelDto.setMaxManaPoint(job.getManaPoint() + ethnicity.getManaPoint());
        playerModelDto.setMoveSpeed(job.getMoveSpeed());

        playerModelDto.setStrength(ethnicity.getStrength());
        playerModelDto.setAgility(ethnicity.getAgility());
        playerModelDto.setIntelligence(ethnicity.getIntelligence());

        // 通用基础属性
        player.setCurrentHealthPoint(playerModelDto.getMaxHealthPoint());
        player.setCurrentManaPoint(playerModelDto.getMaxManaPoint());
        playerModelDto.setRecoverHP(0);
        playerModelDto.setRecoverMP(0);
        playerModelDto.setCommonCoolDown(Constants.DEFAULT_COOL_DOWN);
        playerModelDto.setCriticalHitRate(Constants.DEFAULT_CRITICAL_HIT_RATE);
        playerModelDto.setActionsPerTurn(1);
        player.refreshTotalAttributes();
        return player;
    }

    // 示例快捷方法
    public static Player createDefaultArcherElf(String firstName, String lastName) {
        return createPlayerTemplate(firstName, lastName, EthnicityList.SYLVARIN, JobList.ARCHER);
    }

    public static Player createDefaultMageUndead(String firstName, String lastName) {
        return createPlayerTemplate(firstName, lastName, EthnicityList.THARNYX, JobList.MAGE);
    }

}
