package org.demo.util;

import java.util.Random;

import org.demo.List.ExpList;
import org.demo.dto.ParameterDto;

public class LevelUpHandler {

    // @SuppressWarnings({"FieldMayBeFinal", "NonConstantLogger"})
    // private Logger log;
    // /**
    //  * このクラスの新しいインスタント生成し、必要なLoggerを初期化する ログパスは
    //  * {@link LoggerConfig#setLogPath(String)}で設定する必要がある。
    //  */
    // public LevelUpHandler() {
    //     LoggerConfig loggerConfig = LoggerConfig.getInstance();
    //     this.log = loggerConfig.getLogger("Log");
    // }
    /**
     * 在经验更新之后调用，自动处理升级与属性成长
     */
    public void handleExpGain(ParameterDto player, int gainedExp) {
        int oldLevel = ExpList.getLevelByExp(player.getExp()).getLevel();
        player.setExp(player.getExp() + gainedExp);
        int newLevel = ExpList.getLevelByExp(player.getExp()).getLevel();

        int levelGained = newLevel - oldLevel;
        if (levelGained > 0) {
            player.setLevel(newLevel);
            applyGrowth(player, levelGained);
        }
    }

    /**
     * 应用属性成长逻辑
     */
    private void applyGrowth(ParameterDto player, int levelGained) {
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
