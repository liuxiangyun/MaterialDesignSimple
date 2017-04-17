package com.example.materialdesignsimple.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.materialdesignsimple.BaseActivity;
import com.example.materialdesignsimple.R;
import com.example.materialdesignsimple.adapter.MyRecyclerAdapter;
import com.example.materialdesignsimple.bean.CoverBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * Created by xiangyun_liu on 2017/4/9.
 */

public class MainFragment extends BaseFragment {
    public static final String EXTRA_TITLE = "extra_title";
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private String mTitle;

    private List<CoverBean> mCovers = new ArrayList<>();

    private MyRecyclerAdapter mMyRecyclerAdapter;

    //留一个空参构造函数
    public MainFragment() {

    }

    //再此创建Fragment对象，在Bundle里面存放需要的构造参数
    public static MainFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString(EXTRA_TITLE, title);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mTitle = args.getString(EXTRA_TITLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_main, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        initView();
        return mContentView;
    }

    private void initData() {
        mCovers.clear();
        for (int i = 0; i < 20; i++) {
            switch (new Random().nextInt(3)) {
                case 0:
                    CoverBean beanOne = new CoverBean();
                    beanOne.setNickname("提莫队长");
                    beanOne.setWidth(547);
                    beanOne.setHeight(388);
                    beanOne.setUrl("file:///android_asset/img_android_logo_1.png");
                    mCovers.add(beanOne);
                    break;
                case 1:
                    CoverBean beanTwo = new CoverBean();
                    beanTwo.setNickname("影流之主");
                    beanTwo.setWidth(640);
                    beanTwo.setHeight(480);
                    beanTwo.setUrl("file:///android_asset/img_android_logo_2.png");
                    mCovers.add(beanTwo);
                    break;
                case 2:
                    CoverBean beanThree = new CoverBean();
                    beanThree.setNickname("秋名山车神");
                    beanThree.setWidth(500);
                    beanThree.setHeight(481);
                    beanThree.setUrl("file///android_asset/img_android_logo_3.png");
                    mCovers.add(beanThree);
                    break;
            }
        }
    }

    private void initView() {
        initData();

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mMyRecyclerAdapter = new MyRecyclerAdapter((BaseActivity) getActivity(), this);
        mMyRecyclerAdapter.addAll(mCovers);
        mRecyclerView.setAdapter(mMyRecyclerAdapter);
        mRecyclerView.setOnScrollListener(onScrollListener);

        int colors[] = {R.color.srl_btn_normal, R.color.srl_btn_dark, R.color.srl_btn_dark_d};
        mSwipeRefreshLayout.setColorSchemeResources(colors);
        mSwipeRefreshLayout.setOnRefreshListener(onRefreshListener);
    }

    /**
     * 自动加载下一页
     */
    private final RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && isScrollBottom()) {
                mMyRecyclerAdapter.addAll(mCovers);
                mMyRecyclerAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    /**
     * RecyclerView是否滑动到底部
     */
    private boolean isScrollBottom() {
        int offset = 2 * mRecyclerView.computeVerticalScrollExtent();

        //computeVerticalScrollExtent : RecyclerView 在屏幕中显示的高度
        //computeVerticalScrollOffset : RecyclerView 滚动的距离
        //computeVerticalScrollRange  : RecyclerView 内容总长度
        if (mRecyclerView.computeVerticalScrollExtent() + mRecyclerView.computeVerticalScrollOffset() + offset
                >= mRecyclerView.computeVerticalScrollRange()) {
            return true;
        }
        return false;
    }


    /**
     * 下拉刷新
     */
    private final SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mMyRecyclerAdapter.clear();
                    mMyRecyclerAdapter.addAll(mCovers);
                    mMyRecyclerAdapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }, (new Random().nextInt(5) + 1) * 1000);
        }
    };

    public String getTitle() {
        return mTitle;
    }
}
