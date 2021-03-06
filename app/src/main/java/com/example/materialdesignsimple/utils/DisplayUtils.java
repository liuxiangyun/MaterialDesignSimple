package com.example.materialdesignsimple.utils;

import com.example.materialdesignsimple.application.MDApplication;

/**
 * Created by xiangyun_liu on 2017/4/9.
 * <p>
 * 尺寸显示相关工具类
 */

public class DisplayUtils {
    private static float density;
    private static float scaledDensity;

    static {
        density = MDApplication.getMDApplicationContext().getResources().getDisplayMetrics().density;
        scaledDensity = MDApplication.getMDApplicationContext().getResources().getDisplayMetrics().scaledDensity;
    }

    /**
     * dp 转换成 px
     *
     * @param dpValue dp值
     * @return
     */
    public static int dp2px(int dpValue) {
        return (int) (dpValue * density + 0.5f);
    }

    /**
     * px 转换成 dp
     *
     * @param pxValue px值
     * @return
     */
    public static int px2dp(int pxValue) {
        return (int) (pxValue / density + 0.5f);
    }

    /**
     * sp 转换成 px
     *
     * @param spValue sp值
     * @return
     */
    public static int sp2px(int spValue) {
        return (int) (spValue * scaledDensity + 0.5f);
    }

    /**
     * sp 转换成 px
     *
     * @param pxValue px值
     * @return
     */
    public static int px2sp(int pxValue) {
        return (int) (pxValue / scaledDensity + 0.5f);
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        return MDApplication.getMDApplicationContext().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏的高度(单位：px)
     */
    public static float getStatusBarHeight() {
        float result = 0;
        int resourceId = MDApplication.getMDApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = MDApplication.getMDApplicationContext().getResources().getDimension(resourceId);
        }
        return result;
    }

    /**
     * 获取导航栏高度
     *
     * @return 导航栏的高度(单位：px)
     */
    public static float getNavigationBarHeight() {
        float result = 0;
        int resourceId = MDApplication.getMDApplicationContext().getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = MDApplication.getMDApplicationContext().getResources().getDimension(resourceId);
        }
        return result;
    }
}
