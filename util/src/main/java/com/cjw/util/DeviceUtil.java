package com.cjw.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

public class DeviceUtil {

    private static String devToken;

    public synchronized static String getDeviceInfo() {

        if (devToken == null) {
            TelephonyManager tm = (TelephonyManager) Util.context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if (tm.getDeviceId() != null) {
                devToken = tm.getDeviceId();
            } else {
                devToken = Secure.getString(Util.context.getContentResolver(), Secure.ANDROID_ID);
            }
            // ANDROID_ID = 9774d56d682e549c android 2.2以下 或部分山寨机返回空的
            if (devToken == null || "".equals(devToken) || "9774d56d682e549c".equals(devToken)) {
//                devToken = JPushInterface.getUdid(EHelperApplication.getAppContext());
                if (devToken == null || "".equals(devToken)) {
                    devToken = "35" + //we make this look like a valid IMEI
                            Build.BOARD.length() % 10 +
                            Build.BRAND.length() % 10 +
                            Build.CPU_ABI.length() % 10 +
                            Build.DEVICE.length() % 10 +
                            Build.DISPLAY.length() % 10 +
                            Build.HOST.length() % 10 +
                            Build.ID.length() % 10 +
                            Build.MANUFACTURER.length() % 10 +
                            Build.MODEL.length() % 10 +
                            Build.PRODUCT.length() % 10 +
                            Build.TAGS.length() % 10 +
                            Build.TYPE.length() % 10 +
                            Build.USER.length() % 10; //13 digits
                }
            }
        }

        return devToken;
    }

}
