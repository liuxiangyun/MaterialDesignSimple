package com.example.materialdesignsimple.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.materialdesignsimple.Fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangyun_liu on 2017/4/9.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<MainFragment> mFragments = new ArrayList<>();

    public MainFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    public void addAll(List<MainFragment> fragments) {
        mFragments.addAll(fragments);
    }

    public void add(MainFragment fragment) {
        mFragments.add(fragment);
    }

    public void clear() {
        mFragments.clear();
    }


    @Override
    public MainFragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        //此时只是创建了MainFragment的实例，还没有调用OnCreate方法，所以title属性为空，只能使用getArguments方法获取title属性
        Bundle args = getItem(position).getArguments();
        if (args != null) {
            title = args.getString(MainFragment.EXTRA_TITLE);
        }
        return title;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
