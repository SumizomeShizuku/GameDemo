package org.demo;

import org.demo.util.SimpleLogger;

public class Main {

    // 版本信息
    public static final String VERSION = "0.0.3";

    public static void main(String[] args) {
        SimpleLogger.log.info("当前版本: " + VERSION);
        Play.playerInit();

        SimpleLogger.log.info("---------------运行终了---------------" + System.lineSeparator());
    }

}
