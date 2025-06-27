package org.demo.list;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.demo.dto.ItemModelDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 物品仓库类，负责从外部 JSON 加载所有物品并提供查询接口
 */
public class ItemRepository {

    private static final Map<String, ItemModelDto> itemMap = new HashMap<>();

    /**
     * 加载物品数据（建议在游戏启动时只加载一次）
     *
     * @param resourcePath JSON 文件路径, 如 "item_list.json"
     * @throws IOException 读取或解析异常
     */
    public static void loadFromJson(String filePath) {
        try (InputStream is = ItemRepository.class.getClassLoader().getResourceAsStream(filePath)) {
            ObjectMapper mapper = new ObjectMapper();
            List<ItemModelDto> items = mapper.readValue(is, new TypeReference<List<ItemModelDto>>() {
            });
            itemMap.clear();
            for (ItemModelDto dto : items) {
                itemMap.put(dto.getId(), dto);
            }
        } catch (Exception e) {
            throw new RuntimeException("物品数据加载失败: " + filePath, e);
        }
    }

    /**
     * 查询指定 ID 的物品
     *
     * @param id 物品 id
     * @return 对应物品数据或 null
     */
    public static ItemModelDto getItemById(String id) {
        return itemMap.get(id);
    }

    /**
     * 查询所有物品
     *
     * @return 物品列表
     */
    public List<ItemModelDto> getAllItems() {
        return new ArrayList<>(itemMap.values());
    }

    /**
     * 按物品类型查询
     *
     * @param type 物品类型
     * @return 包含指定类型的所有物品
     */
    public static List<ItemModelDto> findByType(ItemType type) {
        return itemMap.values().stream()
                .filter(item -> item.getType().contains(type))
                .collect(Collectors.toList());
    }

    /**
     * 重新加载/热更
     *
     * @param resourcePath JSON 文件路径
     * @throws IOException 读取异常
     */
    public static void reload(String resourcePath) throws IOException {
        itemMap.clear();
        loadFromJson(resourcePath);
    }
}
