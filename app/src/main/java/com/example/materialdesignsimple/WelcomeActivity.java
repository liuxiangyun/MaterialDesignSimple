package com.example.materialdesignsimple;

import android.os.Bundle;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mHandler.postDelayed(this, 3000);
    }

    @Override
    public void run() {
        super.run();
        MainActivity.startActivity(this);
        finish();
    }
}
