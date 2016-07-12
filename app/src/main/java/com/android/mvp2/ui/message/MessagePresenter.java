package com.android.mvp2.ui.message;

import android.content.Context;


import com.cjw.util.CusLog;

import rx.Subscription;

/**
 * Created by cjw on 2016/6/27.
 */
public class MessagePresenter implements MessageContract.Presenter {

    private MessageContract.View mView;

    private Context context;

    private Subscription subscription;

    public MessagePresenter(Context context) {
        this.context = context;
    }

    @Override
    public void getClick() {
        CusLog.e("MyPresenter", "2. in P");
        mView.toReposListActivity();
    }

    @Override
    public void attachView(MessageContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;

        // cancel
//        if (!subscription.isUnsubscribed()) {
//            subscription.unsubscribe();
//        }
    }
}
