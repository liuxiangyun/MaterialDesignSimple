package com.example.materialdesignsimple;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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

    public void startFadeActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void startFadeActivityForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void run() {

    }
}
