package com.android.mvp2.ui.view.tabhost;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mvp2.R;
import com.cjw.util.TypefacesUtil;
import com.cjw.util.screenscale.DeviceSizeUtil;
import com.cjw.view.textview.FlagTextView;

import java.util.ArrayList;


public class CusTabHost extends LinearLayout {

    private LinearLayout llTab;
    private LayoutInflater layoutInflater;
    private Context context;
    private OnCusTabChangeListener onCusTabChangeListener;
    private int currentTab = 0;
    private ArrayList<TabInfo> tabInfoList;

    public CusTabHost(Context context) {
        super(context);
    }

    public CusTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context, R.layout.tabhost);
        this.context = context;

    }

    protected void initLayout(Context context, int resourceId) {
        this.context = context;
        LayoutInflater.from(context).inflate(resourceId, this, true);
        initView();
    }

    /**
     * 初始化页面
     */
    private void initView() {
        this.llTab = (LinearLayout) findViewById(R.id.ll_tab);
        DeviceSizeUtil.getInstance().resetSize(llTab);
    }

    public void initTabHost(ArrayList<TabInfo> tabInfoList) {
        this.tabInfoList = tabInfoList;
        // 实例化布局对象
        layoutInflater = LayoutInflater.from(context);
        if (llTab != null) {
            llTab.removeAllViews();
            for (int i = 0, size = tabInfoList.size(); i < size; i++) {
                // 为每一个Tab按钮设置图标、文字和内容
                View itemView = getTabItemView(tabInfoList.get(i));
                itemView.setTag(i);
                LayoutParams layoutParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.weight = 1;
                itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setCurrentTab((Integer) v.getTag());
                    }
                });
                llTab.addView(itemView, i, layoutParams);

            }

            setCurrentTab(currentTab);
        }

    }

    public void setCurrentTab(int tabId) {

        int count = llTab.getChildCount();
        for (int i = 0; i < count; i++) {
            View itemView = llTab.getChildAt(i);
            if (tabId == i) {
                currentTab = tabId;
                itemView.setSelected(true);
                if (tabInfoList != null) {
                    TextView tvIcon = (TextView) itemView.findViewById(R.id.tab_icon);
                    tvIcon.setTypeface(TypefacesUtil.get(context));
                    tvIcon.setText(tabInfoList.get(i).getIconSelected());
                }

            } else {

                itemView.setSelected(false);
                if (tabInfoList != null) {
                    TextView tvIcon = (TextView) itemView.findViewById(R.id.tab_icon);
                    tvIcon.setTypeface(TypefacesUtil.get(context));
                    tvIcon.setText(tabInfoList.get(i).getIconUnselected());
                }
            }
        }
        if (onCusTabChangeListener != null)
            onCusTabChangeListener.onCusTabChanged(currentTab);
    }

    public void setOnTabChangedListener(OnCusTabChangeListener onCusTabChangeListener) {
        this.onCusTabChangeListener = onCusTabChangeListener;
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(TabInfo tabInfo) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, this, false);

        LinearLayout llTab = (LinearLayout) view.findViewById(R.id.tab_layout);
        FrameLayout flTabIcon = (FrameLayout) view.findViewById(R.id.fl_tab_icon);
        TextView tvIcon = (TextView) view.findViewById(R.id.tab_icon);
        tvIcon.setTypeface(TypefacesUtil.get(context));
        tvIcon.setText(tabInfo.getIconUnselected());
        TextView tvText = (TextView) view.findViewById(R.id.tab_icon_text);
        tvText.setText(tabInfo.getName());

        DeviceSizeUtil.getInstance().resetSize(llTab, flTabIcon, tvIcon, tvText);
        return view;
    }

    /**
     * @param position tabInfoList position
     * @param isNew    if true is show flag by dot, else hide dot
     * @param newCount if newCount > 0 show flag with count ,else hide flag
     */
    public void updateFlagNew(int position, boolean isNew, int newCount) {
        if (llTab != null && tabInfoList != null && position < tabInfoList.size()) {
            int count = llTab.getChildCount();
            View v;
            for (int i = 0; i < count; i++) {
                v = llTab.getChildAt(i);

                if (v.getTag() != null) {
                    int tag = (int) v.getTag();
                    if (tag == position) {
                        FlagTextView flagTextView = (FlagTextView) v.findViewById(R.id.tv_tab_flag_new);
                        if (newCount > 0)
                            flagTextView.setFlagCount(newCount);
                        else
                            flagTextView.setFlag(isNew);
                        break;
                    }
                }
            }
        }

    }

    public interface OnCusTabChangeListener {

        void onCusTabChanged(int position);
    }
}
