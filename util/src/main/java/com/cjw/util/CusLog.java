package com.cjw.util;


import android.util.Log;

/**
 * @author caiqikao
 */
public class CusLog {

    private CusLog() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static final String TAG = CusLog.class.getName();

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (Util.isCusLog) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (Util.isCusLog) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (Util.isCusLog) {
            Log.e(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (Util.isCusLog) {
            Log.v(TAG, msg);
        }
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (Util.isCusLog) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (Util.isCusLog) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (Util.isCusLog) {
            Log.e(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (Util.isCusLog) {
            Log.v(tag, msg);
        }
    }

}
