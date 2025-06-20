package org.demo.system;

import java.util.List;

import org.demo.calculator.EnemyAttackMain;
import org.demo.calculator.PlayerAttackMain;
import org.demo.dto.ItemModelDto;
import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;
import org.demo.list.ActionType;
import org.demo.list.SkillList;
import org.demo.util.LevelUpHandler;
import org.demo.util.SimpleLogger;

/**
 * 战斗系统类，处理玩家与敌人的回合制战斗。
 */
public class BattleSystem {

    /**
     * 开始回合制战斗，直到玩家或敌人一方死亡。
     *
     * @param player 玩家对象
     * @param enemy 敌人对象
     */
    public static void startBattle(PlayerModelDto player, Enemy enemy) {
        int round = 1;

        log("战斗开始！敌人是：" + enemy.getName());

        // 主战斗循环
        while (player.isAlive() && enemy.isAlive()) {
            log("==== 第 " + round + " 回合 ====");
            log("玩家血量: " + player.getCurrentHealthPoint() + " / " + player.getMaxHealthPoint());
            log("敌人血量: " + enemy.getCurrentHp() + " / " + enemy.getMaxHp());
            boolean enemyDefeated = handlePlayerTurn(player, enemy);

            if (enemyDefeated) {
                handleVictory(player, enemy);
                break;
            }

            handleEnemyTurn(enemy, player);

            if (!player.isAlive()) {
                log("你被击败了...");
                break;
            }

            round++;
        }
    }

    /**
     * 处理玩家回合的逻辑。
     *
     * @param player 玩家对象
     * @param enemy 敌人对象
     */
    private static boolean handlePlayerTurn(PlayerModelDto player, Enemy enemy) {
        log("【玩家回合】");
        while (true) {
            // log("玩家第 " + i + " 次行动");

            // 1:普通攻击, 2:技能, 3:使用道具, 4:防御
            // 模拟玩家输入的技能类型
            int inputAct = 2;
            // 检查玩家操作(攻击、防御、使用道具等)
            ActionType actionType = ActionType.checkAct(inputAct);

            SkillList skill = SkillList.Skill0004;

            switch (actionType) {
                case NormalAttack -> {
                    SimpleLogger.log.info(player.getFirstName() + " 选择了普通攻击");
                    enemy = PlayerAttackMain.skillAttack(player, enemy, SkillList.Skill0001);
                }
                case Skill -> {
                    SimpleLogger.log.info(player.getFirstName() + " 选择了技能" + skill.getName()
                            + " [ " + actionType.name() + " - " + skill.getTypes() + " ]");
                    enemy = PlayerAttackMain.skillAttack(player, enemy, skill);
                }
                case UseItem -> {
                    SimpleLogger.log.info(player.getFirstName() + " 选择了使用道具");

                    continue;
                }
                case Defend -> {
                    SimpleLogger.log.info(player.getFirstName() + " 选择了防御");
                    return false;
                }
                default -> {
                    SimpleLogger.log.error("未知的技能类型: " + actionType);
                    return false;
                }

            }

            // PlayerAttackMain.skillAttack(player, enemy, skill);
            if (!enemy.isAlive()) {
                log(enemy.getName() + " 已被击败！");
                log("敌人血量: " + enemy.getCurrentHp() + " / " + enemy.getMaxHp());
                log("玩家血量: " + player.getCurrentHealthPoint() + " / " + player.getMaxHealthPoint());
                return true; // 敌人死亡，提前结束玩家回合
            } else {
                return false;
            }
        }
    }

    /**
     * 处理敌人回合的逻辑。
     *
     * @param enemy 敌人对象
     * @param player 玩家对象
     */
    private static void handleEnemyTurn(Enemy enemy, PlayerModelDto player) {
        SkillList skill = SkillList.Skill0003;
        // SkillList skill = Enemy.getEnemyRandomSkill(enemy.getEnemySkills());
        EnemyAttackMain.skillAttack(enemy, player, skill);
    }

    /**
     * 玩家获胜后的处理，掉落经验和物品。
     *
     * @param player 玩家对象
     * @param enemy 敌人对象
     */
    private static void handleVictory(PlayerModelDto player, Enemy enemy) {
        int exp = enemy.getDropExp();
        // player.addExp(exp);
        LevelUpHandler.handleExpGain(player, exp);
        log("获得经验：" + exp);

        List<ItemModelDto> drops = enemy.generateDrops(enemy);
        if (drops.isEmpty()) {
            SimpleLogger.log.info("没有掉落物品。");
        } else {
            SimpleLogger.log.info("获得物品: " + enemy.formatDropItems(drops));
            // Backpack bp = player.getBackpack();
            for (ItemModelDto item : drops) {
                enemy.getRealDropItems().put(item.getId(), item);
                player.addItem(item, 1);
            }
            player.showInventory(enemy.getRealDropItems());
        }
    }

    /**
     * 日志打印工具方法。
     *
     * @param message 需要打印的信息
     */
    private static void log(String message) {
        SimpleLogger.log.info(message);
    }
}
