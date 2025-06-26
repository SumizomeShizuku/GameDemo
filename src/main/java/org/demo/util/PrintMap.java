package org.demo.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 将地图打印至Map.log 每次打印覆盖旧纪录，模拟真实的游戏地图
 */
public class PrintMap {

    private static final String LOG_DIR_PATH = "Logs";
    private static final DateTimeFormatter LOG_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // 日志名
    private static final String MAPNAME = "Map.log";

    /**
     * 将地图打印至文件
     *
     * @param map 地图
     */
    public static void printMap(String map) {
        LocalDateTime now = LocalDateTime.now();

        // 地图格式
        String timestamp = now.format(LOG_TIME_FORMATTER);
        String formatted = String.format("%s [%s] %s", timestamp, "MAP", map);

        // 确保目录存在
        File logDir = new File(LOG_DIR_PATH);
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        // 写入地图( 覆盖模式 )
        File logFile = new File(logDir, MAPNAME);
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, false))) {
            writer.println(formatted);
        } catch (IOException e) {
            System.err.println("地图写入失败: " + e.getMessage());
        }
    }

}
