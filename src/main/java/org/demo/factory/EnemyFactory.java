package org.demo.factory;

import org.demo.dto.EnemyModelDto;
import org.demo.list.EnemyRepository;

public class EnemyFactory {

    /**
     * 创建一个敌人实例。 暂时废弃
     *
     * @param enemyId 敌人id
     *
     * @return 新创建的敌人实例
     */
    public static Enemy createEnemy(String enemyId) {
        EnemyModelDto dto = EnemyRepository.getEnemyById(enemyId);
        if (dto == null) {
            return null;
        }

        // 可以复制属性后生成变种
        EnemyModelDto variantDto = new EnemyModelDto(
                dto.getId(),
                dto.getName() + "·变种",
                dto.getMaxHp() + (int) (Math.random() * 20 - 10),
                dto.getAttack() + (int) (Math.random() * 5),
                dto.getPhyDefense(),
                dto.getMagicDefense(),
                dto.getDropExp(),
                dto.getDropRate(),
                dto.getDropItems(),
                dto.getAreas(),
                dto.getEnemySkills());
        return new Enemy(variantDto);
    }
}
