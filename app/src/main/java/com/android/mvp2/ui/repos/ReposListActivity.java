package com.android.mvp2.ui.repos;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.mvp2.R;
import com.android.mvp2.data.model.User;
import com.android.mvp2.ui.BaseActivity;

import butterknife.BindView;

public class ReposListActivity extends BaseActivity implements ReposContract.View {

    private static final String TAG = ReposListActivity.class.getName();

    @BindView(R.id.show_name)
    TextView mTVShowMe;

    @BindView(R.id.repos_rv_list)
    RecyclerView mRvList;

    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;


    ReposPresenter mReposPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mReposPresenter = new ReposPresenter(this);
        mReposPresenter.attachView(this);

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mReposPresenter.detachView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_repo_list;
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(manager);

        loadData();
    }

    private void loadData() {

        mReposPresenter.login();
    }

    @Override
    public void fillData(User user) {
        mTVShowMe.setText("user name: " + user.getLoginName());
    }
}