package com.android.mvp2.ui.message;

import com.android.mvp2.data.model.User;
import com.android.mvp2.ui.BasePresenter;
import com.android.mvp2.ui.BaseView;

/**
 * Created by cjw on 2016/6/28.
 */
public interface MessageContract {

    interface Presenter extends BasePresenter<View> {
        void getClick();
    }

    interface View extends BaseView {
        void toReposListActivity();
        void fillData(User user);
    }

}
