package com.wasi.testapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {
    Fragment fragments [];
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new Fragment[3];
        for (int i = 0; i < fragments.length; i++){
            fragments[i] = new TestFragment();
        }

    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }
}