package net.jetensky.twa.util;

public class Logger {
    public void info(String msg) {
        System.out.println(msg);
    }

    public void error(String problem, Exception e) {
        System.out.println(problem);
        e.printStackTrace();
    }

    public void debug(String msg) {
        System.out.println(msg);
    }
}
