package org.demo.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SimpleLogger {

    private static final String LOG_DIR_PATH = "Logs";
    // private static final DateTimeFormatter TIME_FORMAT
    //         = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    // private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final Log log = new Log();

    public static class Log {

        public void info(String message) {
            logWithLevel("INFO", message);
        }

        public void error(String message) {
            logWithLevel("ERROR", message);
        }

        public void warn(String message) {
            logWithLevel("WARN", message);
        }

        public void debug(String message) {
            logWithLevel("DEBUG", message);
        }

        private void logWithLevel(String level, String message) {
            // String timestamp = LocalDateTime.now().format(TIME_FORMAT);
            // String formatted = String.format("[%s][%s] %s", timestamp, level, message);
            String formatted = String.format("[%s] %s", level, message);

            // 输出到控制台
            // System.out.println(formatted);
            // 输出到当天的日志文件
            // String logFileName = LocalDate.now().format(DATE_FORMAT) + ".log";
            String logFileName = "Log.log";
            File logDir = new File(LOG_DIR_PATH);
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            File logFile = new File(logDir, logFileName);
            // 输出到日志文件
            // 使用追加模式写入日志文件(true 为追加模式, false 为覆盖模式)
            boolean append = true;
            try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, append))) {
                writer.println(formatted);
            } catch (IOException e) {
                System.err.println("日志写入失败: " + e.getMessage());
            }
        }
    }
}
