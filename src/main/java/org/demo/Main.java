package org.demo;

import org.demo.util.SimpleLogger;

public class Main {

    // 版本信息
    public static final String VERSION = "0.0.4";

    public static void main(String[] args) {
        SimpleLogger.log.info("当前版本: " + VERSION);

        // Map10x10 maze = new Map10x10(20, 30);
        // System.err.println(maze.renderFancy());
        Play.playerInit();
        SimpleLogger.log.info("---------------运行终了---------------" + System.lineSeparator());
    }

}
