package com.example.materialdesignsimple.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xiangyun_liu on 2017/4/14.
 */

public class ToastUtils {
    public static final void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT);
    }

    public static final void toast(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT);
    }
}
