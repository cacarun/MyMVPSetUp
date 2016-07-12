package com.android.mvp2.ui.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mvp2.R;
import com.android.mvp2.data.model.User;
import com.android.mvp2.ui.BaseFragment;
import com.android.mvp2.ui.repos.ReposListActivity;
import com.cjw.util.CusLog;

import butterknife.OnClick;

public class MessageFragment extends BaseFragment implements MessageContract.View {
    private static String TAG = MessageFragment.class.getName();

    MessagePresenter mMessagePresenter;

    @OnClick(R.id.id_test_button)
    public void onShowRepositoriesClick() {
        CusLog.e(TAG, "1. click");
        mMessagePresenter.getClick();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CusLog.e(TAG, "Message Fragment on Create");

        mMessagePresenter = new MessagePresenter(getActivity());
        mMessagePresenter.attachView(this);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        fragView = inflater.inflate(R.layout.fragment_message,
                (ViewGroup) getActivity().findViewById(R.id.viewpager_main),
                false);
        if (getArguments() != null) {
            int color = getArguments().getInt("color", 0);
            if (color != 0)
                fragView.setBackgroundColor(getResources().getColor(color));
        }

        bindView();

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
        mMessagePresenter.detachView();
    }


    @Override
    public void toReposListActivity() {
        CusLog.e(TAG, "3. click back");

        startActivity(new Intent(getActivity(), ReposListActivity.class));
    }

    @Override
    public void fillData(User user) {

    }

}