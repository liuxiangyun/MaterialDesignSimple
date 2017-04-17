package com.example.materialdesignsimple.helper;

import android.view.View;

import com.example.materialdesignsimple.BaseActivity;

/**
 * Created by xiangyun_liu on 2017/4/17.
 */

public class ActivityHelper {
    public BaseActivity mActivity;

    public ActivityHelper(BaseActivity activity) {
        mActivity = activity;
    }


    public final View.OnClickListener onBackClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            mActivity.finish();
        }
    };
}
