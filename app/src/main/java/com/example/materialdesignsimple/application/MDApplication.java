package com.example.materialdesignsimple.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by xiangyun_liu on 2017/4/9.
 */

public class MDApplication extends Application {
    private static MDApplication mInstance;
    private static Context mApplicationContext;

    @Override
    public void onCreate() {
        mInstance = this;
        mApplicationContext = getApplicationContext();
    }

    public final static MDApplication getInstance() {
        return mInstance;
    }

    public final static Context getMDApplicationContext() {
        return mApplicationContext;
    }

}
