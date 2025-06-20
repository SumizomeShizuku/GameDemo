package org.demo.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 简易日志工具类, 用于将日志消息记录到文件。
 *
 * <p>
 * 每次程序启动时会在 Logs 目录下创建一个带有日期和时间后缀的日志文件, 例如 log_20250620_112437.txt。整个程序运行期间,
 * 所有日志都追加写入此文件。 日志格式为：2025-06-20 11:24:37 [INFO] 日志内容</p>
 */
public class SimpleLogger {

    private static final String LOG_DIR_PATH = "Logs";
    // 日志文件名包含当前日期和时间, 唯一标识本次运行
    private static final String LOG_FILE_NAME
            = "log_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".log";
    // 日志时间戳格式
    private static final DateTimeFormatter LOG_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 公共 Log 实例
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
         * 以指定日志级别格式化消息并写入日志文件。 日志格式如：2025-06-20 11:24:37 [INFO] xxx
         *
         * @param level 日志级别
         * @param message 日志内容
         */
        private void logWithLevel(String level, String message) {
            // 获取当前时间字符串
            String timestamp = LocalDateTime.now().format(LOG_TIME_FORMATTER);
            // 格式化日志内容
            String formatted = String.format("%s [%s] %s", timestamp, level, message);

            // 创建日志目录（如不存在）
            File logDir = new File(LOG_DIR_PATH);
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            // 日志文件（本次运行唯一）
            File logFile = new File(logDir, LOG_FILE_NAME);

            // 写入日志文件（追加模式）
            try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
                writer.println(formatted);
            } catch (IOException e) {
                System.err.println("日志写入失败: " + e.getMessage());
            }
        }

        @Override
        public String toString() {
            return "Log{"
                    + "logFile='" + LOG_FILE_NAME + '\''
                    + ", logDir='" + LOG_DIR_PATH + '\''
                    + '}';
        }
    }
}
