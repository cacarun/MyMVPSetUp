package com.android.mvp2.ui.main;

import com.android.mvp2.ui.BasePresenter;
import com.android.mvp2.ui.BaseView;

/**
 * Created by cjw on 2016/6/28.
 */
public interface MainContract {

    interface Presenter extends BasePresenter<View> {
        void getClick();
    }

    interface View extends BaseView {

    }

}
