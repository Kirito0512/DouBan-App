package com.example.administrator.douban_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends FragmentActivity {
    private static final int NUM_PAGES = 5;
    private ViewPager viewPager;
    private PagerSlidingTabStrip tabs;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //Initialize the ViewPager and set an adapter
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        // Bind the tabs to the ViewPager
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }


    private class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }
        String []TITLES = {"1","2","3","4","5"};
        //要使用PagerSlidingTabStrip,必须实现该函数
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
/*
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new ScreenSlidePageFragment();
            return fragment;
        }
        */


        //根据position返回对应的fragment
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            Fragment pagerFragment;
            if (position == 0) {
                //下拉刷新，app首页
                pagerFragment = new ListRefreshFragment();
            }
            else {
                pagerFragment = new ScreenSlidePageFragment();
            }
            bundle.putInt("page_num", position);
            pagerFragment.setArguments(bundle);
            return pagerFragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

    }
}

