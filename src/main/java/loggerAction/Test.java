package loggerAction;


import org.apache.log4j.Logger;


/**
 * @Author : gavin
 * @Date : 2019-11-1810:43
 * @DESC : log4j测试
 */
public class Test {

    private static Logger log = Logger.getLogger(Test.class);

    public static void main(String[] args) {

        new Thread(() -> {log.debug("this is debug message");}).start();
        new Thread(() -> {log.info("this is info message");}).start();
        new Thread(() -> {log.warn("this is warn message");}).start();
        new Thread(() -> {log.error("this is error message");}).start();


    }
}
