package org.demo.util;

import org.demo.list.EnemyList;

public class EnemyFactory {

    /**
     * 创建一个敌人实例。
     *
     * @param type 敌人类型
     * @return 新创建的敌人实例
     */
    public static Enemy createEnemy(EnemyList type) {
        return new Enemy(type.getTemplate());
    }
}
