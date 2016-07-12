package com.android.mvp2.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.android.mvp2.R;
import com.cjw.util.CusLog;
import com.cjw.view.titlebar.TitleBar;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements BaseView, View.OnClickListener {

    private static final String TAG = BaseActivity.class.getName();

    protected TitleBar titleBar;
    protected MyHandler myHandler;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getLayoutId() != 0)
            setContentView(getLayoutId());

        myHandler = new MyHandler(this);
        unbinder = ButterKnife.bind(this);

    }

    protected abstract int getLayoutId();

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.typeface_back:
                goBack();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * 返回
     */
    public void goBack() {

        // 统一这里处理返回
        CusLog.e(TAG, "go back.");

        goBack(100);
    }

    private void goBack(int dur) {
        myHandler.sendEmptyMessageDelayed(MyHandler.MSG_GO_BACK, dur);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (myHandler != null) {
            myHandler.removeCallbacksAndMessages(null);
        }

        unbinder.unbind();
    }

    // base view implement

    @Override
    public void onEndLoading() {

    }

    public static class MyHandler extends Handler {

        public static final int MSG_GO_BACK = 1000;
        WeakReference<BaseActivity> mActivity;

        public MyHandler(BaseActivity activity) {
            mActivity = new WeakReference<BaseActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            BaseActivity theActivity = mActivity.get();
            if (theActivity == null)
                return;
            theActivity.handlerCallBack(msg.what);
        }
    }

    protected void handlerCallBack(int what) {
        switch (what) {
            case MyHandler.MSG_GO_BACK:

                this.finish();
                break;
        }
    }
}