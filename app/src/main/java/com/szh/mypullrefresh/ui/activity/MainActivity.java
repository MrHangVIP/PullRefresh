package com.szh.mypullrefresh.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.szh.mypullrefresh.R;
import com.szh.mypullrefresh.ui.view.PullToRefreshWebView;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshWebView myWebView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        myWebView=(PullToRefreshWebView)findViewById(R.id.refresh_webview);

        WebSettings settings=myWebView.getRefreshableView().getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);

        myWebView.getRefreshableView().loadUrl("http://125.76.235.8/index");
        
        
    }

    @Override
    public void onBackPressed() {
        if (myWebView.getRefreshableView().canGoBack()){
            myWebView.getRefreshableView().goBack();
            return;
        }
        finish();
        super.onBackPressed();
    }
}
