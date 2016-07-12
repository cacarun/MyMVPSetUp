package com.android.mvp2.ui.my;

import com.android.mvp2.ui.BasePresenter;
import com.android.mvp2.ui.BaseView;

/**
 * Created by cjw on 2016/6/28.
 */
public interface MyContract {

    interface Presenter extends BasePresenter<View> {
        void login();
    }

    interface View extends BaseView {

    }

}
