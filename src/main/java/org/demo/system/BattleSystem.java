package org.demo.system;

import java.util.Map;

import org.demo.calculator.EnemyAttackMain;
import org.demo.calculator.PlayerAttackMain;
import org.demo.dto.ItemModelDto;
import org.demo.dto.SkillModelDto;
import org.demo.factory.Enemy;
import org.demo.factory.Player;
import org.demo.list.ActionType;
import org.demo.repository.PlayerSkillRepository;
import org.demo.util.SimpleLogger;

/**
 * 战斗系统类, 处理玩家与敌人的回合制战斗。
 */
public class BattleSystem {

    /**
     * 开始回合制战斗, 直到玩家或敌人一方死亡。
     *
     * @param player 玩家对象
     * @param enemy 敌人对象
     */
    public static boolean startBattle(Player player, Enemy enemy) {
        // PlayerModelDto player = playerModel.getModel();
        int round = 1;
        boolean winFlg = true;
        log("战斗开始！敌人是: " + enemy.getName() + System.lineSeparator());

        // log(enemy.getModel().toString());
        // 主战斗循环
        while (player.isAlive() && enemy.isAlive()) {

            log("==== 第 " + round + " 回合 ====");
            log("玩家血量: " + player.getCurrentHealthPoint() + " / " + player.getMaxHealthPoint());
            log("敌人血量: " + enemy.getCurrentHp() + " / " + enemy.getMaxHp() + System.lineSeparator());

            // 玩家回合, 检查敌人是否死亡
            boolean enemyDefeated = handlePlayerTurn(player, enemy);

            // 若玩家死亡 -玩家使用扣除生命值技能死亡的场合
            if (!player.isAlive()) {
                log("你被击败了...");
                player.setCurrentHealthPoint(player.getMaxHealthPoint());
                player.setCurrentManaPoint(player.getMaxManaPoint());
                winFlg = false;
                player.gainExp(-50);
                break;
            }

            // 若滴人死亡
            if (enemyDefeated) {
                // 经验、道具获取
                handleVictory(player, enemy);
                // 结束战斗
                break;
            }

            // 敌人回合
            handleEnemyTurn(enemy, player);

            // 若玩家死亡
            if (!player.isAlive()) {
                log("你被击败了...");
                player.setCurrentHealthPoint(player.getMaxHealthPoint());
                player.setCurrentManaPoint(player.getMaxManaPoint());
                winFlg = false;
                player.gainExp(-50);
                break;
            }

            round++;
        }
        return winFlg;
    }

    /**
     * 处理玩家回合的逻辑。
     *
     * @param player 玩家对象
     * @param enemy 敌人对象
     */
    private static boolean handlePlayerTurn(Player player, Enemy enemy) {
        log("【玩家回合】");
        SkillModelDto skill = PlayerSkillRepository.getSkillById("Skill0003");
        while (true) {
            // log("玩家第 " + i + " 次行动");

            // 1:普通攻击, 2:技能, 3:使用道具, 4:防御
            // 模拟玩家输入的技能类型
            int inputAct = 2;
            // 检查玩家操作(攻击、防御、使用道具等)
            ActionType actionType = ActionType.checkAct(inputAct);

            // SkillList skill = SkillList.Skill0004;
            if (player.getCurrentManaPoint() >= skill.getCost()) {
                switch (actionType) {
                    case NormalAttack -> {
                        SimpleLogger.log.info(player.getFirstName() + " 选择了普通攻击");
                        skill = PlayerSkillRepository.getSkillById("Skill0001");
                        enemy = PlayerAttackMain.skillAttack(player, enemy, skill);
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
                log("敌人血量: " + enemy.getCurrentHp() + " / " + enemy.getMaxHp() + System.lineSeparator());
                // PlayerAttackMain.skillAttack(player, enemy, skill);
                if (!enemy.isAlive()) {
                    log(enemy.getName() + " 已被击败！");
                    log("敌人血量: " + enemy.getCurrentHp() + " / " + enemy.getMaxHp());
                    log("玩家血量: " + player.getCurrentHealthPoint() + " / " + player.getMaxHealthPoint());
                    return true; // 敌人死亡, 提前结束玩家回合
                } else {
                    return false;
                }
            } else {
                SimpleLogger.log.info("魔力值不足, 重新选择行动!");
                skill = PlayerSkillRepository.getSkillById("Skill0001");
            }
        }
    }

    /**
     * 处理敌人回合的逻辑。
     *
     * @param enemy 敌人对象
     * @param player 玩家对象
     */
    private static void handleEnemyTurn(Enemy enemy, Player player) {
        log("【敌人回合】");
        SkillModelDto skill = enemy.getEnemyRandomSkill();
        // SkillList skill = Enemy.getEnemyRandomSkill(enemy.getEnemySkills());
        SimpleLogger.log.info(enemy.getName() + " 选择了技能 [ " + skill.getName()
                + " - " + skill.getTypes() + " ]");
        EnemyAttackMain.skillAttack(enemy, player, skill);

        log("玩家血量: " + player.getCurrentHealthPoint() + " / " + player.getMaxHealthPoint() + System.lineSeparator());
    }

    /**
     * 玩家获胜后的处理, 掉落经验和物品。
     *
     * @param player 玩家对象
     * @param enemy 敌人对象
     */
    private static void handleVictory(Player player, Enemy enemy) {
        int exp = enemy.getDropExp();
        player.gainExp(exp);
        log("获得经验: " + exp);

        Map<ItemModelDto, Integer> drops = enemy.generateDrops(enemy);
        if (drops.isEmpty()) {
            SimpleLogger.log.info("没有掉落物品。");
        } else {
            SimpleLogger.log.info("获得物品: " + enemy.formatDropItems(drops));
            // Backpack bp = player.getBackpack();
            for (Map.Entry<ItemModelDto, Integer> item : drops.entrySet()) {
                player.addItem(item.getKey(), item.getValue());
                // player.addItem(item.getKey(), item.getValue());
            }
            // player.showInventory();

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
