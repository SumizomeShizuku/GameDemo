package org.demo.calculator;

import org.demo.dto.PlayerModelDto;
import org.demo.factory.Enemy;

public class EnemyAttackMain {

    /**
     * 敌人进行一次普通攻击；如你后面要做技能系统，可在此处加分支
     */
    public static PlayerModelDto normalAttack(Enemy enemy, PlayerModelDto player) {
        EnemyNormalAttack.attack(enemy, player);
        return player;    // 方便链式调用
    }
}
