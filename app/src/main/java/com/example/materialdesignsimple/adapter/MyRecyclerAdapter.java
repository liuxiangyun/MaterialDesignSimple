package com.example.materialdesignsimple.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.materialdesignsimple.BaseActivity;
import com.example.materialdesignsimple.R;
import com.example.materialdesignsimple.VideoDetailActivity;
import com.example.materialdesignsimple.bean.CoverBean;

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

    public void addAll(List<CoverBean> covers) {
        mCovers.addAll(covers);
    }

    public void clear() {
        mCovers.clear();
    }

    public MyRecyclerAdapter(BaseActivity baseActivity, Fragment fragment) {
        mFragment = fragment;
        mBaseActivity = baseActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_cover, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        if (measuredWidth < 1) {
            measure(holder);
        }
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
    private int measuredWidth;

    private void measure(final MyViewHolder holder) {
        measuredWidth = holder.mIvMainCover.getMeasuredWidth();
        if (measuredWidth < 1)
            measuredWidth = holder.mIvMainCover.getWidth();
        if (measuredWidth < 1) {
            holder.mIvMainCover.measure(0, 0);
            measuredWidth = holder.mIvMainCover.getMeasuredWidth();
            if (measuredWidth < 1)
                measuredWidth = holder.mIvMainCover.getHeight();
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CoverBean item = getItem(position);
        Glide.with(mBaseActivity).load(item.getUrl()).fitCenter()
                .override(measuredWidth, (int) ((double) measuredWidth / item.getWidth() * item.getHeight()))
                .into(holder.mIvMainCover);
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

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_main_cover)
        ImageView mIvMainCover;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
