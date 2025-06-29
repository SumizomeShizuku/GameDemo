package org.demo;

import org.demo.util.MapPrint;
import org.demo.util.SimpleLogger;
import org.demo.util.StatusPrint;

public class Main {

    // 版本信息
    public static final String VERSION = "0.0.5";

    public static void main(String[] args) {
        SimpleLogger.log.info("---------------运行开始---------------" + System.lineSeparator());
        SimpleLogger.log.info("当前版本: " + VERSION);

        // 查询全部
        // System.out.println("所有物品：");
        // itemRepo.getAllItems().forEach(System.out::println);
        // ItemModelDto a = itemRepo.getItemById("ORC_AXE");
        // System.out.println(a.toString());
        // boolean input = InputHelper.getYesNo("是否进入游戏");
        if (true) {
            try {
                Play.playerInit();
            } catch (Exception e) {
                // e.printStackTrace();
                SimpleLogger.log.info("---------------运行终了---------------" + System.lineSeparator());
                System.exit(0);

            }
        }

        StatusPrint statusPrint = new StatusPrint();
        statusPrint.printStatus("尚未加载");
        MapPrint.printMap("尚未加载");
        SimpleLogger.log.info("---------------运行终了---------------" + System.lineSeparator());
    }

}
