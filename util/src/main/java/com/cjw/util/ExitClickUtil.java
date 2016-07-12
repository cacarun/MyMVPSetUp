package com.cjw.util;

public class ExitClickUtil {
    private static long lastClickTime;

    public static boolean isClickAgain() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD > 1500) {
            lastClickTime = time;
            return false;
        }
        lastClickTime = 0;
        return true;
    }
}
