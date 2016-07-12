package com.android.mvp2.ui.view.swiperefresh;

import android.os.Handler;
import android.widget.ListView;

import com.android.mvp2.R;
import com.android.mvp2.ui.BaseFragment;

// ManualRefreshLayout.OnRefreshListener, ManualRefreshLayout.OnInitFinishListener 下拉刷新 和 第一次刷新
// RefreshLayout.OnLoadListener 加载更多
public class RefreshFragment extends BaseFragment implements RefreshLayout.OnLoadListener,
        ManualRefreshLayout.OnRefreshListener, ManualRefreshLayout.OnInitFinishListener {

    protected RefreshLayout mRefreshLayout;
    protected boolean isLoadAllData = false;

    protected void initReFreshLayout(ListView mListView) {
        mRefreshLayout = (RefreshLayout) fragView.findViewById(R.id.swipe_container);
        mRefreshLayout.setChildView(mListView);
        mRefreshLayout.setColorSchemeResources(R.color.swipe_one,
                R.color.swipe_two,
                R.color.swipe_three,
                R.color.swipe_four);

        //使用SwipeRefreshLayout的下拉刷新监听
        //use SwipeRefreshLayout OnRefreshListener
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadListener(this);
        mRefreshLayout.setOnInitFinishListener(this);

    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onRefresh() {
        isLoadAllData = false;
    }

    /**
     * 控制是否第一次页面出现自动加载数据
     * init first load data ,can reset on Fragment or Activity
     * default is load data first
     *
     * @param isLoadFirst
     */
    @Override
    public void onInitFinish(boolean isLoadFirst) {
        if (isLoadFirst) {
            manualRefresh();
        }
    }

    public void manualRefresh() {
        isLoadAllData = false;
    }

    public void endLoading() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setLoading(false);
                mRefreshLayout.setRefreshing(false);
            }
        }, 500);

//        CusLog.e("Refresh Fragment", "");
//
//        mRefreshLayout.setLoading(false);
//        mRefreshLayout.setRefreshing(false);
    }

}