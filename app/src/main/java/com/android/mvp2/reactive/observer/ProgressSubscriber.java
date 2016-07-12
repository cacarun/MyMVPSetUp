package com.android.mvp2.reactive.observer;

import android.app.Dialog;
import android.content.Context;

import com.android.mvp2.exception.APIException;
import com.android.mvp2.util.DialogUtil;
import com.cjw.util.CusLog;

public abstract class ProgressSubscriber<T> extends BaseSubscriber<T> {

    private Dialog dialog;

    public ProgressSubscriber(Context context) {
        dialog = DialogUtil.createLoadingDialog(context, "正在加载...");
    }

    @Override
    public void onStart() {
        CusLog.e("onStart", Thread.currentThread().getName());
        dialog.show();
        super.onStart();
    }

    @Override
    protected void onError(APIException ex) {
        String displayMessage = ex.getDisplayMessage();
        CusLog.e("onError", "Thread: " + Thread.currentThread().getName() + "  error msg: " + displayMessage);

        dialog.dismiss();
    }

    @Override
    public void onCompleted() {
        CusLog.e("onCompleted", Thread.currentThread().getName());
        dialog.dismiss();
    }

}
