package com.android.mvp2.ui;

import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by cjw on 2016/7/8.
 */
public class BaseFragment extends Fragment implements BaseView {

    protected View fragView;

    private Unbinder unbinder;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }

    protected void bindView() {
        if (fragView != null) {
            unbinder = ButterKnife.bind(this, fragView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }

    @Override
    public void onEndLoading() {

    }
}
