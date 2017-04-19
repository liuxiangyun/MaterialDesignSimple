package com.example.materialdesignsimple.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.materialdesignsimple.Base.BaseActivity;
import com.example.materialdesignsimple.R;
import com.example.materialdesignsimple.VideoDetailActivity;
import com.example.materialdesignsimple.bean.CoverBean;
import com.example.materialdesignsimple.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiangyun_liu on 2017/4/7.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private BaseActivity mBaseActivity;
    private Fragment mFragment;
    private List<CoverBean> mCovers = new ArrayList<>();
    private int mItemWidth;

    public void addAll(List<CoverBean> covers) {
        mCovers.addAll(covers);
    }

    public void clear() {
        mCovers.clear();
    }

    public MyRecyclerAdapter(BaseActivity baseActivity, Fragment fragment) {
        mFragment = fragment;
        mBaseActivity = baseActivity;
        //根据margin和列数计算item的宽度
        mItemWidth = (DisplayUtils.getScreenWidth() - DisplayUtils.dp2px(5) * 4) / 2;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_cover, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Object obj = v.getTag(R.id.tag_bean);
            if (obj != null && obj instanceof CoverBean) {
                VideoDetailActivity.actionStartActivity(mBaseActivity, (CoverBean) obj);
            }
        }
    };

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CoverBean item = getItem(position);

        //ImageView大小根据图片大等比例设置
        int height = (int) ((double) mItemWidth / item.getWidth() * item.getHeight());
        ViewGroup.LayoutParams layoutParams = holder.mIvMainCover.getLayoutParams();
        layoutParams.width = mItemWidth;
        layoutParams.height = height;
        holder.mIvMainCover.setLayoutParams(layoutParams);

        Glide.with(mFragment).load(item.getUrl()).placeholder(R.mipmap.ic_placeholder).override(mItemWidth, height).into(holder.mIvMainCover);
        holder.itemView.setTag(R.id.tag_bean, item);
        holder.itemView.setOnClickListener(onClickListener);
    }

    public CoverBean getItem(int position) {
        return mCovers.get(position);
    }

    @Override
    public int getItemCount() {
        return mCovers.size();
    }

    public List<CoverBean> getAll() {
        return mCovers;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_main_cover)
        ImageView mIvMainCover;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
