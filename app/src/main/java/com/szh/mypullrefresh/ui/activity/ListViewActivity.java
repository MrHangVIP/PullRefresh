package com.szh.mypullrefresh.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.szh.mypullrefresh.R;
import com.szh.mypullrefresh.base.PullToRefreshBase;
import com.szh.mypullrefresh.ui.view.PullToRefreshListView;
import com.szh.mypullrefresh.ui.view.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {

    private int flag=0;

    private PullToRefreshListView mListView;

    private  List<Map<String,String>> myData;

    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        mListView=(PullToRefreshListView) findViewById(R.id.refresh_listview);

        myData=new ArrayList<Map<String, String>>();
        for (int i=0;i<20;i++){
            Map<String,String> map=new HashMap<>();
            map.put("name","钟淑贤");
            myData.add(map);
        }

        simpleAdapter=new SimpleAdapter(this,myData, R.layout.item_list,new String[]{"name"},new int[]{R.id.item_name});
        mListView.getRefreshableView().setAdapter(simpleAdapter);

        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(final PullToRefreshBase<ListView> refreshView) {
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                refreshView.getRefreshableView().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (flag ==0){
                                            for (int i=0;i<20;i++
                                                 ) {
                                                myData.get(i).put("name","宋智航");
                                            }
                                            flag=1;
                                        }else{
                                            for (int i=0;i<20;i++
                                                    ) {
                                                myData.get(i).put("name","钟淑贤");
                                            }

                                            flag=0;
                                        }
                                        simpleAdapter.notifyDataSetChanged();
                                        Toast.makeText(ListViewActivity.this
                                                ,"刷新完成",Toast.LENGTH_LONG).show();
                                        mListView.onRefreshComplete();

                                    }
                                }, 1500);
                            }
                        }

                ).start();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
