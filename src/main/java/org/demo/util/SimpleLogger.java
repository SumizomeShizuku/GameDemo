package org.demo.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 简易日志工具类, 每15分钟自动切换日志文件。
 *
 * <p>
 * 日志文件以每小时的 00、15、30、45 分为分割点。任何日志都会被写入当前15分钟对应的日志文件。 日志文件名格式为
 * log_20250620_1115.txt, 表示2025年6月20日11:15起的15分钟内的所有日志。</p>
 * <p>
 * 日志行格式为: 2025-06-20 11:24:37 [INFO] 日志内容</p>
 */
public class SimpleLogger {

    private static final String LOG_DIR_PATH = "Logs";
    private static final DateTimeFormatter FILE_NAME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    private static final DateTimeFormatter LOG_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final long MAX_FILE_SIZE = 30L * 1024 * 1024;  // 30MB 上限
    private static final String BASE_LOG_FILE_NAME_BASE = "log_" + LocalDateTime.now().format(FILE_NAME_FORMATTER);
    private static int logFileIndex = 0;
    private static String currentLogFileName = BASE_LOG_FILE_NAME_BASE + ".log";

    public static final Log log = new Log();

    /**
     * 日志操作类, 提供多种日志级别方法。
     */
    public static class Log {

        /**
         * 记录 INFO 级别日志。
         *
         * @param message 日志内容
         */
        public void info(String message) {
            logWithLevel("INFO", message);
        }

        /**
         * 记录 ERROR 级别日志。
         *
         * @param message 日志内容
         */
        public void error(String message) {
            logWithLevel("ERROR", message);
        }

        /**
         * 记录 WARN 级别日志。
         *
         * @param message 日志内容
         */
        public void warn(String message) {
            logWithLevel("WARN", message);
        }

        /**
         * 记录 DEBUG 级别日志。
         *
         * @param message 日志内容
         */
        public void debug(String message) {
            logWithLevel("DEBUG", message);
        }

        /**
         * 按时间段切割日志文件, 并追加写入日志。
         *
         * @param level 日志级别
         * @param message 日志内容
         */
        private void logWithLevel(String level, String message) {
            // 忽略DEBUG级别
            if (!level.equals("DEBUG")) {
                LocalDateTime now = LocalDateTime.now();
                String timestamp = now.format(LOG_TIME_FORMATTER);
                String formatted = String.format("%s [%s] %s", timestamp, level, message);
                File logDir = new File(LOG_DIR_PATH);
                if (!logDir.exists()) {
                    logDir.mkdirs();
                }
                File logFile = new File(logDir, currentLogFileName);
                // 如果文件存在且大小超过30MB，则切换到新文件
                synchronized (SimpleLogger.class) {
                    if (logFile.exists() && logFile.length() > MAX_FILE_SIZE) {
                        logFileIndex++;
                        currentLogFileName = BASE_LOG_FILE_NAME_BASE + "_" + logFileIndex + ".log";
                        logFile = new File(logDir, currentLogFileName);
                    }
                }
                try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
                    writer.println(formatted);
                } catch (IOException e) {
                    System.err.println("日志写入失败: " + e.getMessage());
                }
            }
        }
    }
}
