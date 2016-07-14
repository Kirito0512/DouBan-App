package com.example.administrator.douban_app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/7/11.
 */
public class ScreenSlidePageFragment extends Fragment {

    private TextView text;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        //设置TextView的内容
        text = (TextView) view.findViewById(R.id.fragment_text);
        text.setText("fragment~~~~");
        return view;
    }
}
