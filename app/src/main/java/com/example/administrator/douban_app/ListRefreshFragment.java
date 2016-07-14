package com.example.administrator.douban_app;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/12.
 */
public class ListRefreshFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private SwipeRefreshLayout mRefreshLayout;
    private ListView lv;
    private TextView text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_refresh, container, false);

        text = (TextView)view.findViewById(R.id.text);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mRefreshLayout.setOnRefreshListener(this);
        //设置动画颜色
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        lv = (ListView) view.findViewById(R.id.listview);
        lv.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getData()));
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0)
                    mRefreshLayout.setEnabled(true);
                else
                    mRefreshLayout.setEnabled(false);
            }
        });
        return view;
    }



    @Override
    // 下拉时显示动画3秒
    // 一般在onRefresh()里面执行更新操作
    public void onRefresh() {
        mRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                // 通过setRefreshing(false)使动画停止
                mRefreshLayout.setRefreshing(false);
                double f = Math.random();
                text.setText("刷新生成随机数："+String.valueOf(f));
            }
        }, 3000);
    }

    public List<String> getData() {
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < 15; i++) {
            data.add("第"+i + "项");
        }
        return data;

    }
}
