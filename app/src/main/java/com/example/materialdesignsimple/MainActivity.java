package com.example.materialdesignsimple;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.example.materialdesignsimple.Base.BaseActivity;
import com.example.materialdesignsimple.Fragment.MainFragment;
import com.example.materialdesignsimple.adapter.MainFragmentAdapter;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.btn_publish)
    FloatingActionButton mBtnPublish;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    //变形菜单
    private MaterialMenuDrawable mMaterialMenu;
    //抽屉状态
    private boolean mIsDrawerOpen;

    private MainFragmentAdapter mMainFragmentAdapter;

    public static void actionStartActivity(BaseActivity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startFadeActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        //初始化变形菜单,设置菜单颜色，和粗细
        mMaterialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        //判断Gravity为start的抽屉是否打开
        mIsDrawerOpen = mDrawerLayout.isDrawerOpen(GravityCompat.START);

        //设置Toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(mMaterialMenu);

        mToolbar.setNavigationOnClickListener(onNavigationClickListener);
        mDrawerLayout.addDrawerListener(drawerListener);
        mBtnPublish.setOnClickListener(onPublishClickListener);

        initTabLayout();
    }

    private void initTabLayout() {
        mMainFragmentAdapter = new MainFragmentAdapter(this, getSupportFragmentManager());
        mMainFragmentAdapter.add(MainFragment.newInstance(getString(R.string.attention)));
        mMainFragmentAdapter.add(MainFragment.newInstance(getString(R.string.hot)));
        mMainFragmentAdapter.add(MainFragment.newInstance(getString(R.string.nearby)));
        mViewPager.setAdapter(mMainFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager, true);
    }

    /**
     * 监听抽屉事件
     */
    private final DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
        //滑动抽屉的时候调用该方法
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            //根据抽屉滑动的偏移量设置动画
            mMaterialMenu.setTransformationOffset(
                    MaterialMenuDrawable.AnimationState.BURGER_ARROW,
                    mIsDrawerOpen ? 2 - slideOffset : slideOffset
            );
        }

        //抽屉打开调用
        @Override
        public void onDrawerOpened(View drawerView) {
            mIsDrawerOpen = true;
            //更改icon
            mMaterialMenu.setIconState(MaterialMenuDrawable.IconState.ARROW);
        }

        //抽屉关闭调用
        @Override
        public void onDrawerClosed(View drawerView) {
            mIsDrawerOpen = false;
            //更改icon
            mMaterialMenu.setIconState(MaterialMenuDrawable.IconState.BURGER);
        }

        //抽屉状态该变调用
        @Override
        public void onDrawerStateChanged(int newState) {
        }
    };

    private final View.OnClickListener onNavigationClickListener = v -> {
        if (mIsDrawerOpen) {
            //关闭指定抽屉
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            //打开指定抽屉
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    };

    private final View.OnClickListener onPublishClickListener = v -> {
        //第一个View传入CoordinatorLayout布局或其子布局，根据子布局就会找到CoordinatorLayout布局，当显示Snackbar时该布局上的FloatingActionButton会上移调整
        final Snackbar snackbar = Snackbar.make(mBtnPublish, "确定拍摄视频?", Snackbar.LENGTH_LONG);
        snackbar.setAction("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        }).show();
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_search:
                SearchActivity.actionStartActivity(this);
                break;
            case R.id.item_message:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
