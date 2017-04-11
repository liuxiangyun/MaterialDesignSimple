package com.example.materialdesignsimple;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by xiangyun_liu on 2017/4/6.
 */

public class BaseActivity extends AppCompatActivity {
    protected Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mHandler = new Handler();
    }
}
