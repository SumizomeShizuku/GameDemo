package org.demo.util;

import java.util.Scanner;

public class InputHelper {

    private static final Scanner scanner = new Scanner(System.in);

    // 获取整数( 自动处理换行 )
    public static int getInt(String prompt) {
        while (true) {
            try {
                ActionPrint.printAction(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); // 吸收换行符
                return value;
            } catch (Exception e) {
                ActionPrint.printAction("请输入有效的整数！");
                scanner.nextLine(); // 清空错误输入
            }
        }
    }

    // 获取字符串( 整行 )
    public static String getLine(String prompt) {
        ActionPrint.printAction(prompt);
        return scanner.nextLine();
    }

    // 获取小写字符串
    public static String getLowerCaseLine(String prompt) {
        return getLine(prompt).toLowerCase();
    }

    // 获取布尔值( yes/no )
    public static boolean getYesNo(String prompt) {
        while (true) {
            String input = getLine(prompt + " (yes/no): ").toLowerCase();
            if (input.equals("yes")) {
                return true;
            }
            if (input.equals("no")) {
                return false;
            }
            ActionPrint.printAction("请输入 yes 或 no。");
        }
    }

    // 获取单个字符
    public static char getChar(String prompt, String allowedChars) {
        while (true) {
            String input = getLine(prompt);
            if (input.length() == 1) {
                char c = Character.toLowerCase(input.charAt(0));
                if (allowedChars.toLowerCase().indexOf(c) != -1) {
                    return c;
                }
            }
            ActionPrint.printAction("请输入以下字符之一: " + allowedChars);
        }
    }
}
