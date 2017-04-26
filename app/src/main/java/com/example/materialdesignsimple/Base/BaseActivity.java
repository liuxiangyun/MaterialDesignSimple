package com.example.materialdesignsimple.Base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.materialdesignsimple.application.ActivityController;
import com.example.materialdesignsimple.helper.ActivityHelper;

import butterknife.ButterKnife;

/**
 * Created by xiangyun_liu on 2017/4/6.
 */

public abstract class BaseActivity extends AppCompatActivity implements Runnable {
    protected Handler mHandler;
    protected ActivityHelper mActivityHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mHandler = new Handler();
        ActivityController.addActivity(this);
        mActivityHelper = new ActivityHelper(this);
    }

    /**
     * 状态栏透明，5.0及以上系统有效
     */
    protected void transparentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public Handler getHandler() {
        return mHandler;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    /**
     * 淡入淡出
     *
     * @param intent
     */
    public void startFadeActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /**
     * 淡入淡出
     *
     * @param intent
     * @param requestCode
     */
    public void startFadeActivityForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void run() {

    }
}
