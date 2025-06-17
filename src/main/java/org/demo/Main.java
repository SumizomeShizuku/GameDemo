package org.demo;

import org.demo.util.SimpleLogger;

public class Main {

    public static final String VERSION = "0.0.1";

    public static void main(String[] args) {
        SimpleLogger.log.info("当前版本: " + VERSION);
        Play.playerInit();
    }

}
