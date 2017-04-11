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

import com.example.materialdesignsimple.R;
import com.example.materialdesignsimple.adapter.MyRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

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

    private List<Integer> mResIds = new ArrayList<>();

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
        for (int i = 0; i < 20; i++) {
            mResIds.add(R.mipmap.image_card);
        }
    }

    private void initView() {
        initData();

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mMyRecyclerAdapter = new MyRecyclerAdapter();
        mMyRecyclerAdapter.addAll(mResIds);
        mRecyclerView.setAdapter(mMyRecyclerAdapter);
        mRecyclerView.setOnScrollListener(onScrollListener);

        int colors[] = {R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorPrimaryDarkD, R.color.colorPrimaryDarkDD};
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
                mMyRecyclerAdapter.addAll(mResIds);
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
                    mMyRecyclerAdapter.addAll(mResIds);
                    mMyRecyclerAdapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }, 5000);
        }
    };

    public String getTitle() {
        return mTitle;
    }
}
