package com.cjw.util;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cjw.util.screenscale.DeviceSizeUtil;
import com.cjw.util.screenscale.Scale;
import com.cjw.util.screenscale.TextSize;

public class ToastUtil {


    /**
     * instead of  showNetworkErrorToast(Activity context)
     */
    public static void showNetworkErrorToast() {
        showShortToast(Util.context.getResources().getString(R.string.error_network));

    }

    /**
     * App Crash Toast
     *
     * @param content
     */
    public static void showCenterShortToast(String content) {
        Toast toast = Toast.makeText(Util.context, content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 自定义 Toast 提示
     * only HttpManager
     */
    @Deprecated
    public static void showShortToast(Activity context, String content) {

        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_exit, null);
        TextView textView = (TextView) layout.findViewById(R.id.tv_toast_text);
        DeviceSizeUtil.getInstance().setPadding(Scale.ToastLayoutPLR, Scale.ToastLayoutPTB, Scale.ToastLayoutPLR, Scale.ToastLayoutPTB, textView);
        DeviceSizeUtil.getInstance().setTextSize(TextSize.TSSize44, textView);
        textView.setBackgroundResource(R.drawable.shape_bg_toast_normal);
        textView.setText(content);

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(layout);
        toast.show();
    }

    public static void showShortToast(String content) {

        showShortToast(content, Gravity.CENTER);
    }

    /**
     * 自定义 Toast 样式
     *
     * @param content
     */
    public static void showShortToast(String content, int gravity) {

        LayoutInflater inflater = LayoutInflater.from(Util.context);
        View layout = inflater.inflate(R.layout.toast_exit, null);
        TextView textView = (TextView) layout.findViewById(R.id.tv_toast_text);
        DeviceSizeUtil.getInstance().setPadding(Scale.ToastLayoutPLR, Scale.ToastLayoutPTB, Scale.ToastLayoutPLR, Scale.ToastLayoutPTB, textView);
        DeviceSizeUtil.getInstance().setTextSize(TextSize.TSSize44, textView);
        textView.setBackgroundResource(R.drawable.shape_bg_toast_normal);
        textView.setText(content);

        Toast toast = new Toast(Util.context);
        toast.setDuration(Toast.LENGTH_SHORT);
        if (gravity == Gravity.BOTTOM) {
            float height = DeviceSizeUtil.getInstance().getDeviceHeight();
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0,
                    (int) height / 5);
        } else
            toast.setGravity(gravity, 0, 0);

        toast.setView(layout);
        toast.show();
    }


    /**
     * back keyboard exit app toast
     */
    public static void showShortToastExit() {

        float height = DeviceSizeUtil.getInstance().getDeviceHeight();
        LayoutInflater inflater = LayoutInflater.from(Util.context);
        View layout = inflater.inflate(R.layout.toast_exit, null);
        TextView textView = (TextView) layout.findViewById(R.id.tv_toast_text);
        DeviceSizeUtil.getInstance().setPadding(Scale.ToastLayoutPLR, 0, Scale.ToastLayoutPLR, 0, textView);
        DeviceSizeUtil.getInstance().setHeight(Scale.ToastLayoutH, textView);
        DeviceSizeUtil.getInstance().setTextSize(TextSize.TSSize44, textView);
        Toast toast = new Toast(Util.context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0,
                (int) height / 2);
        toast.setView(layout);
        toast.show();
    }
}
