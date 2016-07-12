package com.cjw.util.screenscale;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * must be init on Application
 */
public class DeviceSizeUtil {

    private int deviceHeight = 1920;
    private int deviceWidth = 1080;
    private float deviceDHeight = 640;
    private float deviceDWidth = 360;
    private static Context context;

    private static class SingletonHolder {
        private static DeviceSizeUtil instance = new DeviceSizeUtil();
    }

    public static void init(Context applicationContext) {
        context = applicationContext;
    }

    private DeviceSizeUtil() {
        initDeviceSize();
    }

    public static DeviceSizeUtil getInstance() {
        return SingletonHolder.instance;
    }

    public int getDeviceHeight() {
        return deviceHeight;
    }

    public int getWidthByScale(int width) {
        return getSize(width);
    }

    private void initDeviceSize() {
        if (context != null) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(dm);
            this.deviceWidth = dm.widthPixels;
            this.deviceHeight = dm.heightPixels;

            this.deviceDWidth = dm.widthPixels / dm.density;
            this.deviceDHeight = dm.heightPixels / dm.density;
        }
    }

    public void setWidth(int width, View... views) {
        for (View object : views) {
            if (object != null) {
                object.getLayoutParams().width = getSize(width);
            }
        }
    }

    public void setHeight(int height, View... views) {
        for (View object : views) {
            if (object != null) {
                object.getLayoutParams().height = getSize(height);
            }
        }
    }

    /**
     * 设置高度/宽度
     */
    public void setWidthHeight(int width, int height, View... views) {
        for (View object : views) {
            if (object != null && object.getLayoutParams() != null) {
                object.getLayoutParams().height = getSize(height);
                object.getLayoutParams().width = getSize(width);

            }
        }
    }

    public void setPadding(int left, int top, int right, int bottom, View... views) {
        for (View object : views) {
            if (object != null) {
                object.setPadding(getSize(left), getSize(top), getSize(right), getSize(bottom));
            }
        }
    }

    public void setMargins(int left, int top, int right, int bottom, View... views) {

        for (View view : views) {
            if (view != null) {
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                lp.setMargins(getSize(left), getSize(top), getSize(right), getSize(bottom));
            }
        }
    }

    public void setTextSize(int textSize, TextView... textViews) {
        for (TextView object : textViews) {
            if (object != null) {
                object.setTextSize(TypedValue.COMPLEX_UNIT_PX, getSize(textSize));
            }
        }
    }

    public LinearLayout.LayoutParams initLayout(int width, int height) {
        return new LinearLayout.LayoutParams(getSize(width), getSize(height));
    }

    public void setDividerHeight(ListView listview, int height) {
        if (listview != null) {
            listview.setDividerHeight(getSize(height));
        }
    }

    /**
     * 大小转换
     *
     * @param size
     * @return
     */
    private int getSize(int size) {
        if (size < 0) {
            return size;
        }
        return deviceWidth * size / 1080;
    }

    /**
     * 重新设置
     * width
     * height
     */
    public void resetSize(View... views){
        for (View object : views) {
            if (object != null && object.getLayoutParams() != null) {
                //width height
                object.getLayoutParams().height = getSize( object.getLayoutParams().height);
                object.getLayoutParams().width = getSize(object.getLayoutParams().width);
                //padding
                int leftPadding = object.getPaddingLeft();
                int topPadding = object.getPaddingTop();
                int rightPadding =object.getPaddingRight();
                int bottomPadding = object.getPaddingBottom();
                object.setPadding(getSize(leftPadding), getSize(topPadding), getSize(rightPadding), getSize(bottomPadding));
                //margin
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) object.getLayoutParams();
                int leftMargin = lp.leftMargin;
                int topMargin = lp.topMargin;
                int rightMargin = lp.rightMargin;
                int bottomMargin = lp.bottomMargin;
                lp.setMargins(getSize(leftMargin), getSize(topMargin), getSize(rightMargin), getSize(bottomMargin));
                //TextView textSize
                if (object instanceof TextView) {
                    TextView textView = (TextView) object;
                    int textSize = (int) textView.getTextSize();
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getSize(textSize));
                }

            }
        }
    }
    //Unused Methods

    public int getDeviceWidth() {
        return deviceWidth;
    }

    public int getHeightByScale(int height) {
        return (deviceHeight * height / 1920);
    }

    public float getDeviceDHeight() {
        return deviceDHeight;
    }

    public float getDeviceDWidth() {
        return deviceDWidth;
    }

    /**
     * 设置最小高度
     *
     * @param object
     * @param minHeight
     */
    public void setMinHeight(View object, int minHeight) {
        if (object != null) {
            object.setMinimumHeight(getSize(minHeight));
        }
    }

    public void setMinWidth(View object, int minWidth) {
        if (object != null) {
            object.setMinimumWidth(getSize(minWidth));
        }
    }

    public void setMinWidth(int minWidth, View... views) {
        for (View object : views) {
            if (object != null) {
                object.setMinimumWidth(getSize(minWidth));
            }
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = context != null ? context.getResources().getDisplayMetrics().density : 1;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = context != null ? context.getResources().getDisplayMetrics().density : 1;
        return (int) (pxValue / scale + 0.5f);
    }

    //Deprecated Methods

  /*  *//**
     * 设置边框
     *
     * @param object
     * @param left
     * @param top
     * @param right
     * @param bottom
     *//*
    @Deprecated
    public void setPadding(View object, int left, int top, int right,
                           int bottom) {
        if (object != null) {
            object.setPadding(getSize(left), getSize(top), getSize(right),
                    getSize(bottom));
        }
    }

    *//**
     * 设置高度
     *
     * @param object
     * @param height
     *//*
    @Deprecated
    public void setHeight(View object, int height) {
        if (object != null) {
            object.getLayoutParams().height = getSize(height);
        }
    }

    *//**
     * 设置字体大小
     *
     * @param object
     * @param textSize
     *//*
    @Deprecated
    public void setTextSize(TextView object, int textSize) {
        if (object != null) {
            object.setTextSize(TypedValue.COMPLEX_UNIT_PX, getSize(textSize));
        }
    }

    @Deprecated
    public void setTextSize(int textSize, EditText... editTexts) {
        for (EditText object : editTexts) {
            if (object != null) {
                object.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getSize(textSize));
            }
        }
    }

    @Deprecated
    public void setTextSize(Button object, int textSize) {
        if (object != null) {
            object.setTextSize(TypedValue.COMPLEX_UNIT_PX, getSize(textSize));
        }
    }

    @Deprecated
    public void setTextSize(int textSize, Button... buttons) {
        for (Button object : buttons) {
            if (object != null) {
                object.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getSize(textSize));
            }
        }
    }

    *//**
     * 设置宽度
     *
     * @param object
     * @param width
     *//*
    @Deprecated
    public void setWidth(View object, int width) {
        if (object != null) {
            object.getLayoutParams().width = getSize(width);
        }
    }*/
}
