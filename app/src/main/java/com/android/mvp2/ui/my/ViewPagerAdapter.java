package com.android.mvp2.ui.my;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentList = null;

    public ViewPagerAdapter(FragmentManager fm,
                            ArrayList<Fragment> fragmentList) {
        super(fm);
        if (this.fragmentList == null) {
            this.fragmentList = new ArrayList<>();
            this.fragmentList.addAll(fragmentList);
        }
    }

    @Override
    public Fragment getItem(int arg0) {
        return fragmentList.get(arg0);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

}