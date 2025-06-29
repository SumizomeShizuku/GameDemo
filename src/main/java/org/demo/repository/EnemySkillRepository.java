package org.demo.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.demo.dto.SkillModelDto;
import org.demo.list.SkillType;

import java.io.InputStream;
import java.util.*;

public class EnemySkillRepository {

    private static final Map<String, SkillModelDto> skillMap = new LinkedHashMap<>();

    /**
     * 从 JSON 文件加载技能列表
     *
     * @param filePath 文件路径，如 "skills.json"
     */
    public static void loadFromJson(String filePath) {
        try (InputStream is = PlayerSkillRepository.class.getClassLoader().getResourceAsStream(filePath)) {
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> rawList = mapper.readValue(is, new TypeReference<List<Map<String, Object>>>() {
            });
            skillMap.clear();
            for (Map<String, Object> map : rawList) {
                String id = (String) map.get("id");
                String name = (String) map.get("name");
                String description = (String) map.get("description");
                int baseDamage = (int) map.get("baseDamage");
                int cost = (int) map.get("cost");
                @SuppressWarnings("unchecked")
                List<String> typeStrs = (List<String>) map.get("types");
                EnumSet<SkillType> types = EnumSet.noneOf(SkillType.class);
                for (String t : typeStrs) {
                    types.add(SkillType.valueOf(t));
                }
                SkillModelDto dto = new SkillModelDto(id, name, types, description, baseDamage, cost);
                skillMap.put(id, dto);
            }
        } catch (Exception e) {
            throw new RuntimeException("技能数据加载失败: " + filePath, e);
        }
    }

    /**
     * 按技能id获取技能
     */
    public static SkillModelDto getSkillById(String id) {
        return skillMap.get(id);
    }

    /**
     * 获取全部技能
     */
    public static Collection<SkillModelDto> getAllSkills() {
        return skillMap.values();
    }
}
