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
import com.example.materialdesignsimple.utils.LogUtils;

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

    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
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

    private List<CoverBean> getData() {
        List<CoverBean> beans = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int rdInt = new Random().nextInt(3);
            LogUtils.i("MainFragment random int", rdInt + "");
            switch (rdInt) {
                case 0:
                    CoverBean beanOne = new CoverBean();
                    beanOne.setNickname("提莫队长");
                    beanOne.setWidth(547);
                    beanOne.setHeight(388);
                    beanOne.setUrl("file:///android_asset/img_android_logo_1.png");
                    beans.add(beanOne);
                    break;
                case 1:
                    CoverBean beanTwo = new CoverBean();
                    beanTwo.setNickname("影流之主");
                    beanTwo.setWidth(640);
                    beanTwo.setHeight(480);
                    beanTwo.setUrl("file:///android_asset/img_android_logo_2.png");
                    beans.add(beanTwo);
                    break;
                case 2:
                    CoverBean beanThree = new CoverBean();
                    beanThree.setNickname("秋名山车神");
                    beanThree.setWidth(500);
                    beanThree.setHeight(481);
                    beanThree.setUrl("file:///android_asset/img_android_logo_3.png");
                    beans.add(beanThree);
                    break;
            }
        }
        return beans;
    }

    /*  GAP_HANDLING_NONE 不为隐藏布局边缘差距做任何处理。
        GAP_HANDLING_LAZY 已经过期的变量。
        GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS  通过item之间互换位置重新调整布局。
        当我设置了layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);之后，
        发现item之间互换位置的情况解决了，但是却出现了从下往上滑动的时候，第一行的图片距离顶端有一定的空白区域。
        问题出现只能继续解决，等我看了3遍源码，确实毫无头绪的时候就大胆试了一下，可能能解决这个问题的办法，
        就在recycleView.addOnScrollListener的 onScrollStateChanged()方法中调用了staggeredGridLayoutManagerlayoutManager.invalidateSpanAssignments()。
        这样虽然完全符合了需求，item之间不会调换位置，从下往上滑动的时候，第一行的图片距离顶端无空白区域。但是又认真的看到源码中对此方法的注释，
        总是会觉得有点不妥当。*/

    private void initView() {
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //RecyclerView滑动过程中不断请求layout的Request，不断调整item见的间隙，
        // 并且是在item尺寸显示前预处理，因此用此代码解决RecyclerView滑动到顶部时出现移动问题
        mStaggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mMyRecyclerAdapter = new MyRecyclerAdapter((BaseActivity) getActivity(), this);
        mMyRecyclerAdapter.addAll(getData());
        mRecyclerView.setAdapter(mMyRecyclerAdapter);
        mRecyclerView.addOnScrollListener(onScrollListener);

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
            //防止第一行到顶部有空白区域
            mStaggeredGridLayoutManager.invalidateSpanAssignments();

            if (newState == RecyclerView.SCROLL_STATE_IDLE && isScrollBottom()) {
                mMyRecyclerAdapter.addAll(getData());
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
                    mMyRecyclerAdapter.addAll(getData());
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
