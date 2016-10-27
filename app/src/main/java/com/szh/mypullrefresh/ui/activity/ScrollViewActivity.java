package com.szh.mypullrefresh.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;

import com.szh.mypullrefresh.R;
import com.szh.mypullrefresh.ui.view.PullToRefreshScrollView;
import com.szh.mypullrefresh.ui.view.PullToRefreshWebView;

public class ScrollViewActivity extends AppCompatActivity {

    private PullToRefreshScrollView mScrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);

        mScrollView=(PullToRefreshScrollView) findViewById(R.id.refresh_scrollview);

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
