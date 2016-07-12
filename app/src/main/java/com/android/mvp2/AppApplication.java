package com.android.mvp2;

import android.app.Application;

import com.android.mvp2.constant.AppConfig;
import com.cjw.util.Util;

public class AppApplication extends Application {

    private static AppApplication instance;

    public static AppApplication getInstance() {
        return instance;
    }

//    public static AppApplication get(@NonNull  Context context) {
//        return (AppApplication) context.getApplicationContext();
//    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // set app config
        Util.init(this);
        Util.isCusLog = AppConfig.IS_CUS_LOG;
    }

}
