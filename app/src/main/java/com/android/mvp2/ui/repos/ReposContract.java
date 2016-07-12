package com.android.mvp2.ui.repos;

import com.android.mvp2.data.model.User;
import com.android.mvp2.ui.BasePresenter;
import com.android.mvp2.ui.BaseView;

/**
 * Created by cjw on 2016/6/28.
 */
public interface ReposContract {

    interface Presenter extends BasePresenter<View> {

        void login();
    }

    interface View extends BaseView {
        void fillData(User user);
    }

}
