package com.android.mvp2.reactive.observer;


import com.android.mvp2.constant.ErrorCode;
import com.android.mvp2.exception.APIException;

import rx.Subscriber;

public abstract class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onError(Throwable e) {
        if (e instanceof APIException) {
            onError((APIException) e);
        } else {
            onError(new APIException(e, ErrorCode.UNKNOWN));
        }
    }

    /**
     * 错误回调
     */
    protected abstract void onError(APIException ex);
}
