package com.example.materialdesignsimple.utils;

import android.widget.Toast;

import com.example.materialdesignsimple.application.MDApplication;

/**
 * Created by xiangyun_liu on 2017/4/14.
 * <p>
 * Toast 显示
 */

public class ToastUtils {
    public static final void toast(String msg) {
        Toast.makeText(MDApplication.getMDApplicationContext(), msg, Toast.LENGTH_SHORT);
    }

    public static final void toast(int resId) {
        Toast.makeText(MDApplication.getMDApplicationContext(), resId, Toast.LENGTH_SHORT);
    }
}
