package org.demo.factory;

import java.util.EnumSet;
import java.util.Random;
import java.util.stream.Collectors;

import org.demo.dto.EnemyModelDto;
import org.demo.list.EnemyRarity;
import org.demo.util.LevelUpHandler;

public class EnemyFactory {

    /**
     * 创建一个敌人实例。
     * <p>
     * 暂时废弃
     *
     * @param enemy 敌人模板
     * @param player 玩家实例
     *
     * @return 新创建的敌人实例
     */
    public static Enemy enemyFactory(EnemyModelDto enemy, Player player) {

        if (enemy == null) {
            return null;
        }

        EnemyModelDto variantDto = enemy;
        // Enemy variantEnemy;

        Enemy variantEnemy = new Enemy(variantDto);

        int playerLevel = player.getLevel();
        int minLevel = playerLevel - 3;
        int maxLevel = playerLevel + 3;
        if (minLevel < 1) {
            minLevel = 1;
        }
        Random rand = new Random();
        int randomLevel = minLevel + rand.nextInt(maxLevel - minLevel + 1);

        int levelGained = randomLevel - variantEnemy.getLevel();

        LevelUpHandler enemyUp = new LevelUpHandler();
        enemyUp.enemyGrowth(variantEnemy, levelGained);

        int levelCheck = randomLevel - player.getLevel();

        variantEnemy = enemyRarityUp(variantEnemy, levelCheck);

        return variantEnemy;
    }

    /**
     * 提升敌人稀有度
     *
     * @param enemy 敌人实例
     * @return 新的敌人实例
     */
    private static Enemy enemyRarityUp(Enemy enemy, int levelCheck) {
        EnumSet<EnemyRarity> rarities = EnemyRarity.randomExcludeHouchou();
        double powerBonus = 1.0;
        double hpBonus = 1.0;
        double dropBonus = 1.0;
        double tPowerBonus = 1.0;
        double tHpBonus = 1.0;
        double tDropBonus = 1.0;
        double levelExpBonus = 1.0;

        if (levelCheck > 0) {
            levelExpBonus = Math.pow(1.2, Math.abs(levelCheck));
        } else if (levelCheck < 0) {
            levelExpBonus = Math.pow(0.9, Math.abs(levelCheck));
        }

        boolean hasJouto = rarities.contains(EnemyRarity.JOUTO);
        boolean hasIso = rarities.contains(EnemyRarity.ISO);
        boolean hasKyouga = rarities.contains(EnemyRarity.KYOUGA);
        boolean hasJasui = rarities.contains(EnemyRarity.JASUI);
        boolean hasMagatsukami = rarities.contains(EnemyRarity.MAGATSUKAMI);

        boolean hasHouchou = rarities.contains(EnemyRarity.HOUCHOU);

        if (hasJouto) {
            powerBonus *= 1.0;
            hpBonus *= 1.0;
            dropBonus *= 1.0;
        }

        if (hasIso) {
            powerBonus *= 1.15;
            hpBonus *= 1.10;
            dropBonus *= 1.1;
        }
        if (hasKyouga) {
            powerBonus *= 1.3;
            hpBonus *= 1.2;
            dropBonus *= 1.2;
        }
        if (hasJasui) {
            powerBonus *= 1.45;
            hpBonus *= 1.35;
            dropBonus *= 1.5;
        }
        if (hasMagatsukami) {
            powerBonus *= 1.6;
            hpBonus *= 1.5;
            dropBonus *= 2.0;
        }

        // 宝兆特殊加成
        if (hasHouchou) {
            tPowerBonus *= 0.85;
            tHpBonus *= 1.10;
            tDropBonus *= 3;
        }

        String rare = rarities.stream()
                .map(EnemyRarity::getDisplayNameZh)
                .collect(Collectors.joining(" · "));

        // // 可以复制属性后生成变种
        EnemyModelDto variantDto = new EnemyModelDto(
                enemy.getModel().getId(),
                enemy.getName(),
                enemy.getModel().getEthnicity(),
                rare,
                enemy.getLevel(),
                (int) Math.ceil(enemy.getMaxHp() * hpBonus * tHpBonus),
                (int) Math.ceil(enemy.getStrength() * powerBonus * tPowerBonus),
                (int) Math.ceil(enemy.getAgility() * powerBonus * tPowerBonus),
                (int) Math.ceil(enemy.getIntelligence() * powerBonus * tPowerBonus),
                enemy.getCriticalHitRate() * powerBonus,
                (int) Math.ceil(enemy.getPhyDefense() * hpBonus * tHpBonus),
                (int) Math.ceil(enemy.getMagicDefense() * hpBonus * tHpBonus),
                (int) Math.ceil(enemy.getDropExp() * dropBonus * tDropBonus * levelExpBonus),
                Math.min(enemy.getDropRate() * dropBonus * tDropBonus, 1.0),
                enemy.getDropItems(),
                enemy.getAreas(),
                enemy.getEnemySkills(),
                enemy.getModel().getProbability(),
                enemy.getGrowthWeights());

        Enemy variantEnemy = new Enemy(variantDto);
        variantEnemy.setRarity(rarities);

        return variantEnemy;
    }
}
