package com.android.mvp2.reactive.function;

import com.android.mvp2.exception.manager.ExceptionManager;
import com.cjw.util.CusLog;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by cjw on 2016/7/6.
 */
public class HttpResultErrorFunc<T> implements Func1<Throwable, Observable<T>> {

    @Override
    public Observable<T> call(Throwable throwable) {

        CusLog.e("HttpResultErrorFunc", Thread.currentThread().getName());

        return Observable.error(ExceptionManager.handleException(throwable));
    }
}
