package org.demo.util;

import java.util.Random;

import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;
import org.demo.factory.Player;
import org.demo.list.ExpList;

public class LevelUpHandler {

    /**
     * 在经验更新之后调用, 自动处理升级与属性成长
     */
    public static void handleExpGain(Player realPlayer, int gainedExp) {
        PlayerModelDto player = realPlayer.getModel();
        // 当玩家等级小于100
        if (player.getExp() < ExpList.LEVEL_100.getMinExp()) {
            // 获取升级前等级
            int oldLevel = ExpList.getLevelByExp(player.getExp()).getLevel();

            //  当玩家获得经验时
            if (gainedExp >= 0) {
                // 若当前经验值 + 获取经验值 >= 升级至100级所需经验值
                if (player.getExp() + gainedExp >= ExpList.LEVEL_100.getMinExp()) {
                    // 玩家经验值固定为100级时最低经验值 (不可再获取经验值)
                    player.setExp(ExpList.LEVEL_100.getMinExp());
                } else {
                    // 等级未满100, 正常获取经验值
                    player.setExp(player.getExp() + gainedExp);
                }

                // 计算并取得获取经验后等级
                int newLevel = ExpList.getLevelByExp(player.getExp()).getLevel();
                // 计算过经验获取前后等级差
                int levelGained = newLevel - oldLevel;
                // 若升级
                if (levelGained > 0) {
                    // 玩家设置新等级
                    player.setLevel(newLevel);
                    SimpleLogger.log.info(player.getFirstName() + " 升级了! 当前等级: " + newLevel);
                    // 自动加点
                    applyGrowth(player, levelGained);
                }

                // 当玩家失去经验时
            } else {
                // 获取当前等级必要经验
                int minExp = ExpList.getExpByLevel(player.getLevel()).getMinExp();
                int nowExp = player.getExp() + gainedExp;
                if (nowExp > minExp) {
                    player.setExp(nowExp);
                } else {
                    player.setExp(minExp);
                }
                SimpleLogger.log.info("玩家失去了经验....");
            }

        }
        realPlayer.syncBaseAttributesFromModel();
    }

    /**
     * 应用属性成长逻辑
     *
     * * @param player 玩家模型
     * @param levelGained 玩家升级时获得的等级差
     */
    private static void applyGrowth(PlayerModelDto player, int levelGained) {
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

        // **新增: 计算并应用生命值和魔法值成长**
        int hpIncrease = (int) Math.round(5 * levelGained + 0.1 * player.getStrength() * levelGained);
        int mpIncrease = (int) Math.round(5 * levelGained + 0.125 * player.getIntelligence() * levelGained);
        player.setMaxHealthPoint(player.getMaxHealthPoint() + hpIncrease);
        player.setMaxManaPoint(player.getMaxManaPoint() + mpIncrease);
        // 可选: 升级时恢复生命和魔法到新上限
        player.setCurrentHealthPoint(player.getMaxHealthPoint());
        player.setCurrentManaPoint(player.getMaxManaPoint());

        StringBuilder sb = new StringBuilder();
        sb.append("升级属性分布: 力量: ").append(strengthLeveUp)
                .append(" 敏捷: ").append(agilityLeveUp)
                .append(" 智力: ").append(intelligenceLeveUp);
        // log.info(sb.toString());
        SimpleLogger.log.info(sb.toString());
    }

    /**
     * 应用属性成长逻辑
     */
    public void enemyGrowth(Enemy enemy, int levelGained) {
        int newLevel = enemy.getLevel() + levelGained;
        enemy.setLevel(newLevel);
        if (levelGained < 0) {
            levelGained = 0;
        }
        Random random = new Random();
        double[] weights = enemy.getGrowthWeights();
        // int strength = enemy.getStrength();
        // int agility = enemy.getAgility();
        // int intelligence = enemy.getIntelligence();
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
        enemy.setStrength(enemy.getStrength() + strengthLeveUp);
        // agility = agility + agilityLeveUp;
        enemy.setAgility(enemy.getAgility() + agilityLeveUp);
        // intelligence = intelligence + intelligenceLeveUp;
        enemy.setIntelligence(enemy.getIntelligence() + intelligenceLeveUp);

        // **新增: 计算并应用生命值和魔法值成长**
        int hpIncrease = (int) Math.round(5 * levelGained + 0.1 * enemy.getStrength() * levelGained);
        enemy.setMaxHp(enemy.getMaxHp() + hpIncrease);
    }
}
