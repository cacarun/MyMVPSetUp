package com.cjw.view.titlebar;

import android.content.Context;
import android.util.AttributeSet;

import com.cjw.view.R;

public class TitleBarNormal extends TitleBar {


    public TitleBarNormal(Context context) {
        super(context);
    }

    public TitleBarNormal(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initSize() {
        super.initSize();
        mLayout.setBackgroundColor(getResources().getColor(R.color.theme_color));
        confirmClickable(true);
    }

}
