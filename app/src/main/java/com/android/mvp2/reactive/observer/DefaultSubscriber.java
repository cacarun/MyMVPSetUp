package com.android.mvp2.reactive.observer;

import com.android.mvp2.exception.APIException;
import com.cjw.util.CusLog;

public abstract class DefaultSubscriber<T> extends BaseSubscriber<T> {

    @Override
    protected void onError(APIException ex) {
        String displayMessage = ex.getDisplayMessage();
        CusLog.e("onError", "Thread: " + Thread.currentThread().getName() + "  error msg: " + displayMessage);

    }

}
