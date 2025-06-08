package org.demo.util;

// import java.time.LocalDate;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * グローバル ログ初期化
 */
public class LoggerConfig {

    private static LoggerConfig instance;

    private final Map<String, Logger> loggerMap;
    private final List<ConsoleHandler> consoleHandlers;
    private final List<FileHandler> fileHandlers;
    private Level handlerLevel = Level.INFO;
    private String logPath = "";

    private LoggerConfig() {
        loggerMap = new ConcurrentHashMap<>();
        consoleHandlers = new CopyOnWriteArrayList<>();
        fileHandlers = new CopyOnWriteArrayList<>();
        // currentConsoleHandlerLevel = Level.ALL;

        Handler[] handlers = Logger.getLogger("").getHandlers();
        for (Handler handler : handlers) {
            Logger.getLogger("").removeHandler(handler);
        }
    }

    /**
     * インスタント生成
     */
    public static synchronized LoggerConfig getInstance() {
        if (instance == null) {
            instance = new LoggerConfig();
        }
        return instance;
    }

    /**
     * 動的にログファイル出力パスを設定
     *
     * @param path ログファイル出力パス
     */
    public void setLogPath(String path) {
        this.logPath = path;
    }

    /**
     * logのフォーマットを設定する ファイル 出力フォーマット：日付（yyyy-MM-dd HH:mm:ss） レベル（漢字） [メソッド名：行]
     * メッセージ コンソール出力フォーマット：日付（yyyy-MM-dd HH:mm:ss） レベル（漢字） [メソッド名：行] メッセージ
     *
     * @param type orgとFNCログの設定
     * @return ログ内容
     */
    public Logger getLogger(String type) {

        if (loggerMap.containsKey(type)) {
            return loggerMap.get(type);
        }

        Logger logger = Logger.getLogger(type);

        logger.setUseParentHandlers(false);
        for (Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }
        logger.setLevel(Level.ALL);

        try {
            // DateTimeFormatter data = DateTimeFormatter.ofPattern("yyyyMMddHH");
            //String fileDate = LocalDateTime.now().format(data);
            // ファイルに保存するログ設定
            // String fileName = logPath + type + "_" + fileDate + ".log";
            String fileName = logPath + type + ".log";
            FileHandler fileHandler = new FileHandler(fileName, false);
            fileHandler.setLevel(handlerLevel);
            // ログフォーマットを変更
            fileHandler.setFormatter(new SimpleFormatter() {
                // 日付フォマードを年-月-日 時-分-秒に指定
                private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");

                @Override
                public synchronized String format(LogRecord record) {
                    // 指定された日付フォーマットで日付を設定する
                    String date = LocalDateTime.now().format(dateFormatter);
                    // classから情報を取得
                    // StackTraceElement[] stackTrance = Thread.currentThread().getStackTrace();
                    // クラス名を取得
                    // String className = record.getSourceClassName();
                    // ログ出力時、メソッドの位置を設定（初期化：-1）
                    // int lineNumber = -1;

                    // for (StackTraceElement element : stackTrance) {
                    //     if (element.getClassName().equals(className)) {
                    //         // メソッドの位置を設定
                    //         lineNumber = element.getLineNumber();
                    //         break;
                    //     }
                    // }
                    // ログに日付 ログレベル メソッド名 メソッドの位置 ログメッセージ の順で出力
                    // return String.format("%s,%s,[%s],[%d],%s%n",
                    return String.format("%s, %s%n",
                            date,
                            // "[Get_newsscript]",
                            // record.getLevel().getLocalizedName(),
                            // record.getSourceClassName(),
                            // lineNumber,
                            record.getMessage());
                }
            });
            fileHandler.setEncoding("UTF-8");
            logger.addHandler(fileHandler);
            fileHandlers.add(fileHandler);

            // // コンソールログ設定
            // ConsoleHandler consoleHandler = new ConsoleHandler();
            // consoleHandler.setLevel(handlerLevel);
            // // ログフォーマットを変更
            // consoleHandler.setFormatter(new SimpleFormatter() {
            //     // 日付フォマードを年-月-日 時-分-秒に指定
            //     private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            //     @Override
            //     public synchronized String format(LogRecord record) {
            //         // 指定された日付フォーマットで日付を設定する
            //         String date = LocalDateTime.now().format(dateFormatter);
            //         // classから情報を取得
            //         StackTraceElement[] stackTrance = Thread.currentThread().getStackTrace();
            //         // クラス名を取得
            //         String className = record.getSourceClassName();
            //         // ログ出力時、メソッドの位置を設定（初期化：-1）
            //         int lineNumber = -1;
            //         for (StackTraceElement element : stackTrance) {
            //             if (element.getClassName().equals(className)) {
            //                 // メソッドの位置を設定
            //                 lineNumber = element.getLineNumber();
            //                 break;
            //             }
            //         }
            //         // ログに日付 ログレベル メソッド名 メソッドの位置 ログメッセージ の順で出力
            //         return String.format("%s %s [%s] [%d] %s%n",
            //                 date,
            //                 record.getLevel().getLocalizedName(),
            //                 record.getSourceClassName(),
            //                 lineNumber,
            //                 record.getMessage());
            //     }
            // });
            // fileHandler.setEncoding("UTF-8");
            // logger.addHandler(consoleHandler);
            // consoleHandlers.add(consoleHandler);
        } catch (IOException | SecurityException e) {
            // e.printStackTrace();
        }

        loggerMap.put(type, logger);

        return logger;
    }

    /**
     * mainメソッドに設定したboolean値によって、ConsoleHandlerのレベルを変更
     *
     * @param debugMode true : CONFIGレベル出力 | false : INFOレベル出力
     */
    public void setConsolHandlerLevel(boolean debugMode) {
        if (debugMode) {
            handlerLevel = Level.CONFIG;
        } else {
            handlerLevel = Level.INFO;
        }
        for (ConsoleHandler ch : consoleHandlers) {
            ch.setLevel(handlerLevel);
        }
        for (FileHandler fh : fileHandlers) {
            fh.setLevel(handlerLevel);
        }
    }

    /**
     * すべてのhandlersをクローズ
     */
    public void closeAllHandlers() {
        // ConsoleHandlersをクローズ メモリ解放
        for (ConsoleHandler ch : consoleHandlers) {
            ch.close();
        }
        for (FileHandler fh : fileHandlers) {
            fh.close();
        }
        consoleHandlers.clear();
        fileHandlers.clear();
        // FileHandlerをクローズ メモリ解放
        for (Logger logger : loggerMap.values()) {
            for (Handler handler : logger.getHandlers()) {
                if (handler instanceof FileHandler) {
                    handler.close();
                }
            }
        }
        loggerMap.clear();
    }

}
