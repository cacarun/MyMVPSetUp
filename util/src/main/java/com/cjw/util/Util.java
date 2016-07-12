package com.cjw.util;

import android.content.Context;

/**
 * Created by cjw on 2016/7/11.
 */
public class Util {

    public static Context context;

    public static boolean isCusLog = false; // 是否需要打印bug 自定义日志控制

    public static final void init(Context ctx) {
        context = ctx.getApplicationContext();
    }
}
