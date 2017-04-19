package com.example.materialdesignsimple;

import android.content.Intent;
import android.os.Bundle;

import com.example.materialdesignsimple.Base.BaseActivity;

public class SearchActivity extends BaseActivity {

    public static void actionStartActivity(BaseActivity activity) {
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
