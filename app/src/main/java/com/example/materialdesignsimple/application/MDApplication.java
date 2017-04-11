package com.example.materialdesignsimple.application;

import android.app.Application;

/**
 * Created by xiangyun_liu on 2017/4/9.
 */

public class MDApplication extends Application {
    private static MDApplication mInstance;

    @Override
    public void onCreate() {
        mInstance = this;
    }

    public final static MDApplication getInstance() {
        return mInstance;
    }

}
