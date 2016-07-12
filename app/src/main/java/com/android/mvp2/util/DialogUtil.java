package com.android.mvp2.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mvp2.R;
import com.cjw.util.screenscale.DeviceSizeUtil;
import com.cjw.util.screenscale.Scale;
import com.cjw.util.screenscale.TextSize;
import com.cjw.view.progressbar.ProgressBarCircular;

public class DialogUtil {


    /**
     * 得到自定义的progressDialog
     *
     * @param context
     * @param msg
     * @return
     */
    public static Dialog createLoadingDialog(Context context, String msg) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_layout);// 加载布局
        // main.xml中的ImageView
        ProgressBarCircular progressbar = (ProgressBarCircular) v.findViewById(R.id.pro_dialog_loading_circular);
        progressbar.setBackgroundColor(context.getResources().getColor(R.color.theme_color));
        TextView tipTextView = (TextView) v.findViewById(R.id.tv_dialog_loading_text);// 提示文字
        tipTextView.setText(msg);// 设置加载信息


        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog

        loadingDialog.setCancelable(false);// 不可以用“返回键”取消
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
        DeviceSizeUtil.getInstance().setHeight(Scale.DialogLoadingH, v);
        DeviceSizeUtil.getInstance().setPadding(Scale.DialogLoadingPLR, 0, Scale.DialogLoadingPLR, 0, v);
        DeviceSizeUtil.getInstance().setWidthHeight(Scale.DialogLoadingProBarWH, Scale.DialogLoadingProBarWH, progressbar);
        DeviceSizeUtil.getInstance().setPadding(Scale.DialogLoadingMsgPL, 0, 0, 0, tipTextView);
        DeviceSizeUtil.getInstance().setTextSize(TextSize.TSSize44, tipTextView);
        return loadingDialog;

    }
}
