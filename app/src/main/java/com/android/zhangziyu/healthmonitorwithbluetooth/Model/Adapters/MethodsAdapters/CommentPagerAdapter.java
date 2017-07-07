package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Adapters.MethodsAdapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 自定义Pager的适配器
 */
public class CommentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> data;

    public CommentPagerAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }
}
