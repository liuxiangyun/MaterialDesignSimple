package com.example.materialdesignsimple.utils;

import android.widget.Toast;

import com.example.materialdesignsimple.application.MDApplication;

/**
 * Created by xiangyun_liu on 2017/4/14.
 * <p>
 * Toast 显示
 */

public class ToastUtils {
    public static final void shortToast(String msg) {
        Toast.makeText(MDApplication.getMDApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static final void shortToast(int resId) {
        Toast.makeText(MDApplication.getMDApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }

    public static final void longToast(String msg) {
        Toast.makeText(MDApplication.getMDApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    public static final void longToast(int resId) {
        Toast.makeText(MDApplication.getMDApplicationContext(), resId, Toast.LENGTH_LONG).show();
    }
}
