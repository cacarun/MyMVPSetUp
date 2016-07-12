package com.android.mvp2.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.android.mvp2.R;
import com.android.mvp2.ui.BaseActivity;
import com.android.mvp2.ui.my.ViewPagerAdapter;
import com.android.mvp2.ui.view.tabhost.CusTabHost;
import com.android.mvp2.ui.view.tabhost.TabInfo;
import com.android.mvp2.ui.view.viewpager.CusViewPager;
import com.cjw.util.ExitClickUtil;
import com.cjw.util.ToastUtil;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements MainContract.View, ViewPager.OnPageChangeListener, CusTabHost.OnCusTabChangeListener {

    private static final String TAG = MainActivity.class.getName();

    private CusViewPager viewPager;
    private CusTabHost cusTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //init tabHost
        ArrayList<TabInfo> tabInfoList = TabInfo.initMainTabs(-1);
        cusTabHost = (CusTabHost) findViewById(R.id.ll_tab_host);
        cusTabHost.initTabHost(tabInfoList);
        cusTabHost.setOnTabChangedListener(this);
        //init viewPaper
        ArrayList<Fragment> fragList = new ArrayList<>();
        for (TabInfo tabInfo : tabInfoList) {
            Fragment fragment = (Fragment) tabInfo.getFragment();
            if (tabInfo.getTabType() == TabInfo.TAB_TYPE.MESSAGE) {
                Bundle bundle = new Bundle();
                bundle.putInt("color", R.color.orange);
                fragment.setArguments(bundle);
            }
            fragList.add(fragment);
        }
        viewPager = (CusViewPager) findViewById(R.id.viewpager_main);
        viewPager.setPagingEnabled(false);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragList));
        viewPager.addOnPageChangeListener(this);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            boolean exitNeeded = ExitClickUtil.isClickAgain();
            if (!exitNeeded) {

                ToastUtil.showShortToastExit();

                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onCusTabChanged(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        cusTabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
