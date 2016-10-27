package com.szh.mypullrefresh.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.szh.mypullrefresh.R;
import com.szh.mypullrefresh.ui.view.PullToRefreshScrollView;

public class MyActivity extends AppCompatActivity {

    private PullToRefreshScrollView mScrollView;
    private Button webViewBT;
    private Button listviewBT;
    private Button mScrollViewBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mScrollView=(PullToRefreshScrollView) findViewById(R.id.refresh_scrollview);

        webViewBT=(Button)findViewById(R.id.button_webview);
        listviewBT=(Button)findViewById(R.id.button_listview);
        mScrollViewBT=(Button)findViewById(R.id.button_scrollview);

        webViewBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyActivity.this,MainActivity.class));
            }
        });

        listviewBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyActivity.this,ListViewActivity.class));
            }
        });

        mScrollViewBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyActivity.this,ScrollViewActivity.class));
            }
        });
    }
}
