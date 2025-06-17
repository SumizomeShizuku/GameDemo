package org.demo.util;

import java.util.Random;

import org.demo.dto.PlayerModelDto;
import org.demo.list.ExpList;

public class LevelUpHandler {

    /**
     * 在经验更新之后调用，自动处理升级与属性成长
     */
    public void handleExpGain(PlayerModelDto player, int gainedExp) {
        if (!(player.getExp() >= ExpList.LEVEL_100.getMinExp())) {
            int oldLevel = ExpList.getLevelByExp(player.getExp()).getLevel();
            if (player.getExp() + gainedExp >= ExpList.LEVEL_100.getMinExp()) {
                player.setExp(ExpList.LEVEL_100.getMinExp());
            } else {
                player.setExp(player.getExp() + gainedExp);
            }
            int newLevel = ExpList.getLevelByExp(player.getExp()).getLevel();
            int levelGained = newLevel - oldLevel;
            if (levelGained > 0) {
                player.setLevel(newLevel);
                SimpleLogger.log.info(player.getFirstName() + " 升级了! 当前等级: " + newLevel);
                applyGrowth(player, levelGained);
            }
        }

    }

    /**
     * 应用属性成长逻辑
     */
    private void applyGrowth(PlayerModelDto player, int levelGained) {
        Random random = new Random();
        double[] weights = player.getJob().getGrowthWeights();
        // int strength = player.getStrength();
        // int agility = player.getAgility();
        // int intelligence = player.getIntelligence();
        int strengthLeveUp = 0;
        int agilityLeveUp = 0;
        int intelligenceLeveUp = 0;

        for (int i = 0; i < levelGained * 3; i++) { // 每级3点属性
            double roll = random.nextDouble();
            if (roll < weights[0]) {
                strengthLeveUp++;
            } else if (roll < weights[0] + weights[1]) {
                agilityLeveUp++;
            } else {
                intelligenceLeveUp++;
            }
        }
        // strength = strength + strengthLeveUp;
        player.setStrength(player.getStrength() + strengthLeveUp);
        // agility = agility + agilityLeveUp;
        player.setAgility(player.getAgility() + agilityLeveUp);
        // intelligence = intelligence + intelligenceLeveUp;
        player.setIntelligence(player.getIntelligence() + intelligenceLeveUp);

        StringBuilder sb = new StringBuilder();
        sb.append("升级属性分布: 力量: ").append(strengthLeveUp)
                .append(" 敏捷: ").append(agilityLeveUp)
                .append(" 智力: ").append(intelligenceLeveUp);
        // log.info(sb.toString());
        SimpleLogger.log.info(sb.toString());
    }
}
