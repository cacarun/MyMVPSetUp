package com.cjw.view.textview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.cjw.util.screenscale.DeviceSizeUtil;
import com.cjw.util.screenscale.TextSize;
import com.cjw.view.R;


public class FlagTextView extends TextView {

    private int flagsize = 16;//Scale.FlagNewDotWH;

    public FlagTextView(Context context) {
        super(context);
        init();
    }

    public FlagTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = DeviceSizeUtil.getInstance().getWidthByScale(flagsize);
        this.setMeasuredDimension(size, size);

    }

    private void init() {

        this.setTextColor(getResources().getColor(R.color.white));
        this.setSingleLine(true);
        this.setEllipsize(TextUtils.TruncateAt.END);
        this.setGravity(Gravity.CENTER);
        this.setBackgroundResource(R.drawable.shape_oval_red_dot);
        setFlag(false);

    }

    //    public void initFlagCallBack(FLAG_MODE flag_mode) {
//        FlagNewCenter.getInstance().addCallBack(flag_mode, new FlagNewCallBack() {
//
//            @Override
//            public void onFlagCallBack(boolean isNew) {
//                setFlag(isNew);
//            }
//            @Override
//            public void onFlagCountCallBack(int newCount) {
//                setFlagCount(newCount);
//            }
//        });
//    }
    public void setFlagCount(int newCount) {
        if (newCount > 0) {
            if (this.getParent() != null)
                DeviceSizeUtil.getInstance().setWidthHeight(50, 50, (View) this.getParent());
            flagsize = 38;
            invalidate();
            DeviceSizeUtil.getInstance().setTextSize(TextSize.TSSize20, this);

            this.setText(newCount > 99 ? "•••" : "" + newCount);
            FlagTextView.this.setVisibility(View.VISIBLE);
        } else {
            FlagTextView.this.setVisibility(View.INVISIBLE);
        }
    }

    public void setFlag(boolean isNew) {
        if (this.getParent() != null)
            DeviceSizeUtil.getInstance().setWidthHeight(44, 40, (View) this.getParent());
        if (isNew) {
            FlagTextView.this.setVisibility(View.VISIBLE);
        } else {
            FlagTextView.this.setVisibility(View.INVISIBLE);
        }
    }

}
