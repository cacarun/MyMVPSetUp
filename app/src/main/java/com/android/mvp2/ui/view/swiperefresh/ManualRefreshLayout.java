package com.android.mvp2.ui.view.swiperefresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

/**
 * SwipeRefreshLayout 增加第一次自动加载数据
 * support manualRefresh  Fragment onCreated
 */
public class ManualRefreshLayout extends SwipeRefreshLayout {

    private OnInitFinishListener mOnInitFinishListener;
    //Fragment onCreate  setRefreshing(true)  no Showing
    private boolean isInitFinish = false;
    private boolean isLoadDataFirst =true;// default load data first

    public ManualRefreshLayout(Context context) {
        this(context, null);
    }

    public ManualRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!isInitFinish) {
            isInitFinish = true;
            if (mOnInitFinishListener != null) {
                // 第一次自动加载数据
                mOnInitFinishListener.onInitFinish(isLoadDataFirst);
            }
        }
    }

    /**
     *
     * @param isLoadDataFirst
     */
    public void setLoadDataFirst(boolean isLoadDataFirst){
        this.isLoadDataFirst = isLoadDataFirst;
    }
    public void setOnInitFinishListener(OnInitFinishListener mOnInitFinishListener) {
        this.mOnInitFinishListener = mOnInitFinishListener;
    }

    public interface OnInitFinishListener {
        void onInitFinish(boolean isLoadFirst);
    }
}