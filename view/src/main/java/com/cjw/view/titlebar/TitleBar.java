package com.cjw.view.titlebar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cjw.util.TypefacesUtil;
import com.cjw.util.screenscale.DeviceSizeUtil;
import com.cjw.util.screenscale.Scale;
import com.cjw.util.screenscale.TextSize;
import com.cjw.view.R;

public class TitleBar extends FrameLayout {

    protected TextView mTxtBack;
    protected TextView mTxtConfirm;
    protected TextView mTxtTitle;
    protected RelativeLayout mRlLayout;
    protected LinearLayout mLayout;
    protected Context mContext;

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context, R.layout.titlebar);
        this.mContext = context;
        initView();
        initTypeFaces();
        initSize();
    }

    protected void initLayout(Context context, int resourceId) {
        LayoutInflater.from(context).inflate(resourceId, this, true);

    }

    protected void initTypeFaces() {
        mTxtBack.setTypeface(TypefacesUtil.get(this.mContext));
        //mTxtConfirm.setTypeface(TypefacesUtil.get(this.mContext));
        //mTxtTitle.setTypeface(TypefacesUtil.get(this.mContext));

    }

    /**
     * 初始化页面
     */
    protected void initView() {
        mTxtBack = (TextView) findViewById(R.id.typeface_back);
        mTxtConfirm = (TextView) findViewById(R.id.typeface_confirm);
        mTxtTitle = (TextView) findViewById(R.id.typeface_title);
        mRlLayout = (RelativeLayout) findViewById(R.id.header_layout);
        mLayout = (LinearLayout) findViewById(R.id.li_header_layout);
    }

    protected void initSize() {
        DeviceSizeUtil.getInstance().setHeight(Scale.TitleBarH, mRlLayout);
        DeviceSizeUtil.getInstance().setTextSize(TextSize.TSSize66, mTxtBack);
        DeviceSizeUtil.getInstance().setTextSize(TextSize.TSSize54, mTxtTitle);
        DeviceSizeUtil.getInstance().setTextSize(TextSize.TSSize44, mTxtConfirm);
        DeviceSizeUtil.getInstance().setPadding(Scale.NormalPLR, 0, Scale.NormalPLR, 0, mTxtBack, mTxtConfirm);
        DeviceSizeUtil.getInstance().setPadding(Scale.TitleBarTitlePLR, 0, Scale.TitleBarTitlePLR, 0, mTxtTitle);
    }

    public void setOnClickListener(OnClickListener ls) {
        if (mTxtBack != null) {
            mTxtBack.setOnClickListener(ls);
        }
        if (mTxtConfirm != null) {
            mTxtConfirm.setOnClickListener(ls);
        }
    }

    public void lockConfirm(boolean isLock) {

    }

    public void setText(String left, String center, String right) {

        setTextViewText(mTxtBack, left);
        setTextViewText(mTxtTitle, center);
        setTextViewText(mTxtConfirm, right);
        reSetTitlePLR(right);
    }

    private void reSetTitlePLR(String right) {
        int strLength = right != null ? right.length() : 0;
        strLength = strLength < 1 ? 1 : strLength;
        int plr = 96 + strLength * 38;
        DeviceSizeUtil.getInstance().setPadding(plr, 0, plr, 0, mTxtTitle);
    }

    public void setTextLeft(String left) {

        setTextViewText(mTxtBack, left);
    }

    public void setTextCenter(String center) {

        setTextViewText(mTxtTitle, center);
    }

    public void setTextRight(String right) {

        setTextViewText(mTxtConfirm, right);
        reSetTitlePLR(right);
    }

    public void setTextRightPadding(int pr,int textsize){
        DeviceSizeUtil.getInstance().setPadding(Scale.NormalPLR, 0, pr, 0, mTxtConfirm);
        DeviceSizeUtil.getInstance().setTextSize(textsize, mTxtConfirm);
    }

    public void setTextRightColor(int color) {

        setTextViewColor(mTxtConfirm, color);
    }

    public void confirmClickable(boolean clickable) {
        if (mTxtConfirm != null) {
            mTxtConfirm.setClickable(clickable);
            mTxtConfirm.setEnabled(clickable);
            mTxtConfirm.setSelected(clickable);
        }
    }

    private void setTextViewText(TextView tv, String text) {
        if (text == null || (text != null && "".equals(text.trim()))) {
            tv.setVisibility(View.GONE);
        } else {
            tv.setText(text);
            tv.setVisibility(View.VISIBLE);
        }
    }

    private void setTextViewColor(TextView tv, int color) {
        if (tv != null && color != 0)
            tv.setTextColor(color);
    }

    public void setViewPagerChanged(int position){

    }
}
