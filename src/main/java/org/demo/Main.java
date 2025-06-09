package org.demo;

import org.demo.dto.PlayerModelDto;
import org.demo.list.EnemyType;
import org.demo.util.AttackCalculator;
import org.demo.util.Enemy;
import org.demo.util.EnemyFactory;
import org.demo.util.LevelUpHandler;
import org.demo.util.SimpleLogger;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.net.URL;
// import java.util.concurrent.orgpletableFuture;
public class Main {

    public static void main(String[] args) {
        // LoggerConfig loggerConfig = LoggerConfig.getInstance();
        // Logger logLogger = loggerConfig.getLogger("Log");
        // orgpletableFuture<Void> task1 = orgpletableFuture.runAsync(() ->{
        // for (int i = 0; i < 10; i++){
        // System.out.println("task1:" + i);
        // }
        // });

        // orgpletableFuture<Void> task2 = orgpletableFuture.runAsync(() ->{
        // for (int i = 9; i >= 0; i--){
        // System.out.println("task2:" + i);
        // }
        // });
        // orgpletableFuture.allOf(task1, task2).join();
        PlayerModelDto layer = new PlayerModelDto();

        // 1:战士, 2:法师, 3:游侠, 4:圣职者
        int jobNum = 1;
        // 1:空尾族, 2:人族 3:林语族, 4:亡影族
        int ethnicityNum = 1;
        layer = SettingParam.setPlayerEthnicity(ethnicityNum, layer);
        layer = SettingParam.setPlayerJob(jobNum, layer);
        // layer.setExp(240000);
        layer.setFirstName("Anneliese");
        layer.setLastName("Wallis");
        LevelUpHandler levelUpHandler = new LevelUpHandler();
        levelUpHandler.handleExpGain(layer, 120000);
        // LevelUPCheck levelUPCheck = new LevelUPCheck();
        // levelUPCheck.checkAndHandleLevelUp(layer);

        layer.setStrength(layer.getStrength() + 10);

        // System.out.println("职业: " + layer.getJob().getNameZh());
        // System.out.println(layer.toString());
        // int level = ExpList.getLevelByExp(layer.getExp()).getLevel();
        // System.out.println("等级: " + level);
        AttackCalculator attackCalculator = new AttackCalculator();

        // for (int i = 0; i < 3; i++) {
        //     attackCalculator.printResult(attackCalculator, layer);
        // }
        // logLogger.info(layer.toString());
        SimpleLogger.log.info(layer.toString());

        Enemy enemy = EnemyFactory.createEnemy(EnemyType.ORC);
        SimpleLogger.log.info("你遇到了一只敌人：" + enemy);
        enemy.takeDamage(attackCalculator.result(attackCalculator, layer, enemy));
        SimpleLogger.log.info("你对它造成了伤害，现在它的状态是：" + enemy);

        // ExpList exp = ExpList.getLevelByExp(layer.getExp());
        // System.out.println("当前等级" + exp.getLevel());
        // System.out.println("当前经验" + layer.getExp());
        // System.out.println("升级剩余经验" + (exp.getTotalExp() - (layer.getExp())));
    }

}
