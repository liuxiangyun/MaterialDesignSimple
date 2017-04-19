package com.example.materialdesignsimple.utils;

import android.util.Log;

import com.example.materialdesignsimple.BuildConfig;

/**
 * Created by xiangyun_liu on 2017/4/14.
 * <p>
 * 日志工具类
 */

public class LogUtils {
    private static final int VERBOSE = 0;
    private static final int DEBUG = 1;
    private static final int INFO = 2;
    private static final int WARNING = 3;
    private static final int ERROR = 4;
    //日志等级
    private static int LEVEL = VERBOSE;


    public static final void v(String tag, String msg) {
        if (BuildConfig.DEBUG && LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static final void d(String tag, String msg) {
        if (BuildConfig.DEBUG && LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static final void i(String tag, String msg) {
        if (BuildConfig.DEBUG && LEVEL <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static final void w(String tag, String msg) {
        if (BuildConfig.DEBUG && LEVEL <= WARNING) {
            Log.w(tag, msg);
        }
    }

    public static final void e(String tag, String msg) {
        if (BuildConfig.DEBUG && LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
    }
}
