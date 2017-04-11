package com.example.materialdesignsimple.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by xiangyun_liu on 2017/4/9.
 */

public class BaseFragment extends Fragment {
    protected View mContentView;
    protected Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, mContentView);
        mHandler = new Handler();
        return mContentView;
    }
}
