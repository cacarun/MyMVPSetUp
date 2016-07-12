package com.android.mvp2.ui.view.viewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * solution:
 *
 * java.lang.IllegalArgumentException: pointerIndex out of range
 * nativeGetAxisValue
 *
 * add catch
 */
@SuppressLint("ClickableViewAccessibility")
public class CusViewPager extends ViewPager {

    private boolean isPagingEnabled = true;

    public CusViewPager(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public CusViewPager(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            return this.isPagingEnabled && super.onTouchEvent(event);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        try {
            return this.isPagingEnabled && super.onInterceptTouchEvent(event);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }
}
