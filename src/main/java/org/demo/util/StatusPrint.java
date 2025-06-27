package org.demo.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StatusPrint {

    /**
     * 将角色属性打印至Status.log 每次打印覆盖旧纪录
     */
    private static final String LOG_DIR_PATH = "Logs";
    private static final DateTimeFormatter LOG_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // 日志名
    private static final String MAPNAME = "Status.log";

    /**
     * 将角色属性打印至文件
     *
     * @param status 属性
     */
    public void printStatus(String status) {
        LocalDateTime now = LocalDateTime.now();

        // 属性格式
        String timestamp = now.format(LOG_TIME_FORMATTER);
        String formatted = String.format("%s [%s]%n%s", timestamp, "Status", status);

        // 确保目录存在
        File logDir = new File(LOG_DIR_PATH);
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        // 写入地图( 覆盖模式 )
        File logFile = new File(logDir, MAPNAME);
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, false))) {
            writer.print(formatted);
        } catch (IOException e) {
            System.err.println("属性写入失败: " + e.getMessage());
        }
    }

}
