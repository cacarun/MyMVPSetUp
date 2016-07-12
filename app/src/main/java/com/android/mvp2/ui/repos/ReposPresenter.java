package com.android.mvp2.ui.repos;

import android.content.Context;

import com.android.mvp2.AppApplication;
import com.android.mvp2.data.model.User;
import com.android.mvp2.reactive.function.HttpResultErrorFunc;
import com.android.mvp2.reactive.function.HttpResultGenerateFunc;
import com.android.mvp2.http.manager.HttpClientManager;
import com.android.mvp2.http.SchedulersCompat;
import com.android.mvp2.reactive.observer.ProgressSubscriber;
import com.cjw.util.CusLog;
import com.cjw.util.DeviceUtil;
import com.cjw.util.MD5Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.Subscription;

/**
 * Created by cjw on 2016/6/27.
 */
public class ReposPresenter implements ReposContract.Presenter {

    private ReposContract.View mView;

    private Context context;

    private Subscription subscription;

    public ReposPresenter(Context context) {
        this.context = context;

    }


    @Override
    public void attachView(ReposContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;

        // cancel
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

    }

    @Override
    public void login() {

        Map<String, String> map = new HashMap<>();
        map.put("account" , "wuyu");
        map.put("password" , MD5Util.md5("11111"));
        map.put("device_token" , DeviceUtil.getDeviceInfo());
        map.put("device_type" , "1");
        map.put("visit_type" , "1");

        /**
         * onErrorResumeNext 拦截 onError事件（有可能是在请求网络的过程中发出的，
         * 也有可能是 HttpResultFunc 在解析服务器返回数据的时候发现错误而发出的）
         * 主要负责判断并处理 onError 事件里面的错误，然后发出一个"能让view层知道"的错误信息
         */
        subscription = HttpClientManager.getInstance().getMainAPI().login(map)
            .map(new HttpResultGenerateFunc<User>() {

                @Override
                public User generateModelFromJSONObject(JSONObject dataObj) throws JSONException {
                    return User.generateUserFromJSONObject(dataObj);
                }
            })
            .onErrorResumeNext(new HttpResultErrorFunc<User>()) // 异常转换器
            //.subscribeOn(Schedulers.io())
            //.observeOn(AndroidSchedulers.mainThread())
            .compose(SchedulersCompat.<User>applyIoSchedulers())
            .subscribe(new ProgressSubscriber<User>(context) {
                @Override
                public void onNext(User user) {
                    CusLog.e("onNext", Thread.currentThread().getName());

                    if (user != null) {
                        mView.fillData(user);
                    }
                }
            });

    }

}
