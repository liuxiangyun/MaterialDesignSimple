package com.example.materialdesignsimple.Base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import butterknife.ButterKnife;

/**
 * Created by xiangyun_liu on 2017/4/9.
 */

public abstract class BaseFragment extends Fragment {
    protected View mContentView;
    protected Handler mHandler;

    /**
     * 当前Fragment要加载的布局资源
     *
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     * Fragment初始化操作
     */
    protected abstract void init();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*
          ViewPager默认只会缓存当前页的前一页和后一页，在滑动的时候Fragment可能会销毁重建，
          在此处进行判断处理,如果该Fragment创建过无需重新初始化。
         */
        if (mContentView == null) {
            mContentView = inflater.inflate(getLayoutResId(), container, false);
            ButterKnife.bind(this, mContentView);
            mHandler = new Handler();
            init();
        } else {
            ViewParent parent = mContentView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mContentView);
            }
        }
        return mContentView;
    }
}
