package com.android.mvp2.ui.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.mvp2.R;
import com.android.mvp2.data.model.DemoData;
import com.android.mvp2.ui.view.swiperefresh.RefreshFragment;
import com.cjw.util.CusLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyFragment extends RefreshFragment implements MyContract.View {
    private static String TAG = MyFragment.class.getName();

    MyPresenter mMyPresenter;

    @BindView(R.id.my_list)
    ListView myListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CusLog.e(TAG, "My Fragment on Create");

        mMyPresenter = new MyPresenter(getActivity());
        mMyPresenter.attachView(this);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        fragView = inflater.inflate(R.layout.fragment_my,
                (ViewGroup) getActivity().findViewById(R.id.viewpager_main),
                false);
        if (getArguments() != null) {
            int color = getArguments().getInt("color", 0);
            if (color != 0)
                fragView.setBackgroundColor(getResources().getColor(color));
        }

        bindView();

        // init view
        MyListAdapter listAdapter = new MyListAdapter(getActivity(), getTestData());
        myListView.setAdapter(listAdapter);

        // init refresh
        initReFreshLayout(myListView);
    }

    private List<DemoData> getTestData() {
        List<DemoData> demoDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DemoData data = new DemoData("name: " + i, "detail " + i);
            demoDatas.add(data);
        }
        return demoDatas;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup p = (ViewGroup) fragView.getParent();
        if (p != null) {
            p.removeAllViewsInLayout();
        }
        return fragView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMyPresenter.detachView();
    }


    @Override
    public void onEndLoading() {
        super.onEndLoading();
        // end loading
        endLoading();
    }

    /////////////////////////////////////

    @Override
    public void onLoad() {
        super.onLoad();
        CusLog.e(TAG, "onLoad");

        // 按页加载
//        int size = informationList != null ? informationList.size() : 0;
//        if (messageTotalCount == size || size % Constants.PageSize != 0)
//            isLoadAllData = true;
//
//        if (!isLoadAllData) {
//            int idx = size / Constants.PageSize + 1;
//            mRefreshLayout.setRefreshing(true);
//            if (idx == 0)
//                getHomeWorkList(1, Constants.PageSize, true);
//            else
//                getHomeWorkList(idx, Constants.PageSize, false);
//        } else {
//            ToastUtil.showShortToast(AppApplication.getInstance().getString(R.string.fragment_list_data_no_more), Gravity.BOTTOM);
//        }

        // 从服务器取数据
        mRefreshLayout.setRefreshing(true);
        loadData();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        CusLog.e(TAG, "onRefresh");

        // 从服务器取数据
        loadData();
    }

    @Override
    public void manualRefresh() {
        super.manualRefresh();
        CusLog.e(TAG, "manualRefresh");

        mRefreshLayout.setRefreshing(true);

        // 从服务器取数据
        loadData();
    }

    @Override
    public void onInitFinish(boolean isLoadFirst) {
        super.onInitFinish(false);
    }

    private void loadData() {
        mMyPresenter.login();
    }
}