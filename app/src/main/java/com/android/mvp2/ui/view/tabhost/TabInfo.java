package com.android.mvp2.ui.view.tabhost;

import android.app.Fragment;
import android.content.Context;

import com.android.mvp2.AppApplication;
import com.android.mvp2.R;
import com.android.mvp2.ui.message.MessageFragment;
import com.android.mvp2.ui.my.MyFragment;

import java.util.ArrayList;

public class TabInfo {


    public Object getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public enum TAB_TYPE {HOMEWORK, EXPLORATION, MESSAGE, MY, OTHER}

    private String name;
    private String iconUnselected;
    private String iconSelected;
    private TAB_TYPE tabType;
    private Object fragment;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUnselected() {
        return iconUnselected;
    }

    public void setIconUnselected(String iconUnselected) {
        this.iconUnselected = iconUnselected;
    }

    public String getIconSelected() {
        return iconSelected;
    }

    public void setIconSelected(String iconSelected) {
        this.iconSelected = iconSelected;
    }

    public TAB_TYPE getTabType() {
        return tabType;
    }

    public void setTabType(TAB_TYPE tabType) {
        this.tabType = tabType;
    }


    public static ArrayList<TabInfo> initMainTabs(int type) {
        ArrayList<TabInfo> tabInfoList;
        switch (type) {
            case 1:
                tabInfoList = initTabs(TAB_TYPE.EXPLORATION, TAB_TYPE.MESSAGE, TAB_TYPE.MY);
                break;
            default:
                tabInfoList = initTabs(TAB_TYPE.HOMEWORK, TAB_TYPE.EXPLORATION, TAB_TYPE.MESSAGE, TAB_TYPE.MY, TAB_TYPE.OTHER);
                break;
        }
        return tabInfoList;
    }

    private static ArrayList<TabInfo> initTabs(TAB_TYPE... tab_types) {
        ArrayList<TabInfo> tabInfoList = new ArrayList<>();
        for (TAB_TYPE tab_type : tab_types) {
            TabInfo mainTabs = new TabInfo();
            Context context = AppApplication.getInstance();
            switch (tab_type) {
                case HOMEWORK:
                    mainTabs.name = context.getString(R.string.activity_main_title_homework);
                    mainTabs.iconUnselected = context.getString(R.string.typeface_homework);
                    mainTabs.iconSelected = context.getString(R.string.typeface_homework_selected);
                    mainTabs.tabType = TAB_TYPE.HOMEWORK;
                    mainTabs.fragment = new MyFragment();
                    break;
                case EXPLORATION:
                    mainTabs.name = context.getString(R.string.activity_main_title_exploration);
                    mainTabs.iconUnselected = context.getString(R.string.typeface_exploration);
                    mainTabs.iconSelected = context.getString(R.string.typeface_exploration_selected);
                    mainTabs.tabType = TAB_TYPE.EXPLORATION;
                    mainTabs.fragment = new MyFragment();
                    break;
                case MESSAGE:
                    mainTabs.name = context.getString(R.string.activity_main_title_message);
                    mainTabs.iconUnselected = context.getString(R.string.typeface_message);
                    mainTabs.iconSelected = context.getString(R.string.typeface_message_selected);
                    mainTabs.tabType = TAB_TYPE.MESSAGE;
                    mainTabs.fragment = new MessageFragment();
                    break;
                case MY:
                    mainTabs.name = context.getString(R.string.activity_main_title_my);
                    mainTabs.iconUnselected = context.getString(R.string.typeface_my);
                    mainTabs.iconSelected = context.getString(R.string.typeface_my_selected);
                    mainTabs.tabType = TAB_TYPE.MY;
                    mainTabs.fragment = new MyFragment();
                    break;
                case OTHER:
                    mainTabs.name = context.getString(R.string.activity_main_title_my);
                    mainTabs.iconUnselected = context.getString(R.string.typeface_my);
                    mainTabs.iconSelected = context.getString(R.string.typeface_my_selected);
                    mainTabs.tabType = TAB_TYPE.OTHER;
                    mainTabs.fragment = new MyFragment();
                    break;
            }
            tabInfoList.add(mainTabs);
        }

        return tabInfoList;
    }
}
