package org.demo.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.demo.dto.EnemyJsonDto;
import org.demo.dto.EnemyModelDto;
import org.demo.dto.ItemModelDto;
import org.demo.dto.SkillModelDto;
import org.demo.backpack.DropInfo;

import java.io.InputStream;
import java.util.*;

/**
 * 敌人仓库（静态方法/属性版） 负责从 JSON 文件加载所有敌人，并提供全局查询接口。
 */
public class EnemyRepository {

    private static final Map<String, EnemyModelDto> enemyMap = new HashMap<>();

    /**
     * 从JSON文件加载敌人列表。
     *
     * @param filePath 文件路径（如 "enemy_list.json"）
     */
    public static void loadFromJson(String filePath) {
        try (InputStream is = EnemyRepository.class.getClassLoader().getResourceAsStream(filePath)) {
            ObjectMapper mapper = new ObjectMapper();
            List<EnemyJsonDto> rawList = mapper.readValue(is, new TypeReference<List<EnemyJsonDto>>() {
            });
            enemyMap.clear();
            for (EnemyJsonDto raw : rawList) {
                // 构建掉落物Map
                Map<ItemModelDto, DropInfo> dropMap = new HashMap<>();
                if (raw.drops != null) {
                    for (EnemyJsonDto.DropEntry entry : raw.drops) {
                        ItemModelDto item = ItemRepository.getItemById(entry.itemId);
                        if (item != null) {
                            dropMap.put(item, new DropInfo(entry.weight, entry.minQty, entry.maxQty));
                        }
                    }
                }
                // 技能映射
                Map<String, SkillModelDto> skillMap = new LinkedHashMap<>();
                if (raw.skills != null) {
                    for (String skillId : raw.skills) {
                        SkillModelDto skill = SkillRepository.getSkillById(skillId);
                        if (skill != null) {
                            skillMap.put(skillId, skill);
                        }
                    }
                }
                // 构造模板
                EnemyModelDto dto = new EnemyModelDto(
                        raw.id, raw.name, raw.maxHp, raw.attack, raw.phyDefense, raw.magicDefense,
                        raw.dropExp, raw.dropRate, dropMap, raw.areas, skillMap);
                enemyMap.put(raw.id, dto);
            }
        } catch (Exception e) {
            throw new RuntimeException("敌人数据加载失败: " + filePath, e);
        }
    }

    /**
     * 获取敌人
     *
     * @param id 敌人ID
     * @return 敌人模板
     */
    public static EnemyModelDto getEnemyById(String id) {
        return enemyMap.get(id);
    }

    /**
     * 获取所有敌人
     */
    public static Map<String, EnemyModelDto> getAllEnemies() {
        return enemyMap;
    }

    public static List<EnemyModelDto> getEnemiesByArea(String area) {
        List<EnemyModelDto> result = new ArrayList<>();
        for (EnemyModelDto e : enemyMap.values()) {
            List<String> areas = e.getAreas();
            if (areas != null && areas.contains(area)) {
                result.add(e);
            }
        }
        return result;
    }
}
