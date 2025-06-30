package org.demo.repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.demo.backpack.DropInfo;
import org.demo.dto.EnemyJsonDto;
import org.demo.dto.EnemyModelDto;
import org.demo.dto.ItemModelDto;
import org.demo.dto.SkillModelDto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
                        SkillModelDto skill = EnemySkillRepository.getSkillById(skillId);
                        if (skill != null) {
                            skillMap.put(skillId, skill);
                        }
                    }
                }
                if (raw.areas == null) {
                    List<String> area = Arrays.asList("全地区");
                    raw.areas = area;
                }
                // 构造模板
                EnemyModelDto dto = new EnemyModelDto(
                        raw.id,
                        raw.name,
                        raw.ethnicity,
                        raw.level,
                        raw.maxHp,
                        raw.strength,
                        raw.agility,
                        raw.intelligence,
                        raw.criticalHitRate,
                        raw.phyDefense,
                        raw.magicDefense,
                        raw.dropExp,
                        raw.dropRate,
                        dropMap,
                        raw.areas,
                        skillMap,
                        raw.probability,
                        raw.growthWeights);
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

    /**
     * 查找并返回指定地区内的所有敌人类型
     *
     * @param area 指定地区
     * @return 敌人类型列表
     */
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

    /**
     * 从指定地区所有可能的敌人中随机抽取一种敌人
     *
     * @param list
     * @return
     */
    public static EnemyModelDto getRandomEnemy(String area, int distance) {
        List<EnemyModelDto> list = getEnemiesByArea(area);
        list.addAll(getEnemiesByArea("全地区"));
        EnemyModelDto result = null;
        Random random = new Random();
        if (list.isEmpty()) {
            return null; // 或抛出异常，根据你的需求决定
        }

        int totalWeight = 0;
        for (EnemyModelDto enemy : list) {
            totalWeight += enemy.getProbability() + distance;
        }
        int rand = random.nextInt(totalWeight);
        int cumulative = 0;

        for (EnemyModelDto enemy : list) {
            cumulative += enemy.getProbability() + distance;
            if (rand < cumulative) {
                result = enemy;
                break;
            }
        }

        // int index = random.nextInt(list.size()); // 生成0到list.size()-1的随机整数
        // result = list.get(index);
        return result;
    }

    /**
     * 从指定地区所有可能的敌人中随机抽取N种敌人
     *
     * @param enemyList 敌人列表
     * @param n 要选取的数量
     * @return 随机N个敌人列表(可以重复)
     */
    public static List<EnemyModelDto> getRandomEnemiesWithRepeat(String area) {
        List<EnemyModelDto> list = getEnemiesByArea(area);
        list.addAll(getEnemiesByArea("全地区"));
        List<EnemyModelDto> result = new ArrayList<>();
        Random random = new Random();
        // 随机掉落1到5个敌人
        int enemyCount = random.nextInt(5 - 1 + 1) + 1;
        if (list.isEmpty() || enemyCount <= 0) {
            return result; // 返回空列表
        }
        for (int i = 0; i < enemyCount; i++) {
            int index = random.nextInt(list.size());
            result.add(list.get(index));
        }
        return result;
    }
}
