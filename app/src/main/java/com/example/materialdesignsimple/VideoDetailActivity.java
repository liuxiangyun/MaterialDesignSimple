package com.example.materialdesignsimple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.materialdesignsimple.bean.CoverBean;
import com.example.materialdesignsimple.constant.ExtraConstant;
import com.example.materialdesignsimple.utils.ToastUtils;

import butterknife.BindView;

public class VideoDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_cover)
    ImageView mIvCover;

    private CoverBean mBean;

    public static void actionStartActivity(BaseActivity activity, CoverBean bean) {
        Intent intent = new Intent(activity, VideoDetailActivity.class);
        intent.putExtra(ExtraConstant.EXTRA_OBJECT_BEAN, bean);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_video_detail);
        super.onCreate(savedInstanceState);
        mBean = (CoverBean) getIntent().getSerializableExtra(ExtraConstant.EXTRA_OBJECT_BEAN);
        if (mBean == null) {
            ToastUtils.toast(this, R.string.error_video);
            finish();
            return;
        }

        initView();
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mBean.getNickname());
        mToolbar.setNavigationOnClickListener(mActivityHelper.onBackClickListener);
        Glide.with(this).load(mBean.getUrl()).into(mIvCover);
    }
}
