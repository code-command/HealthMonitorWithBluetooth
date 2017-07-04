package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Adapters.BindingAdapters;

import android.databinding.BindingAdapter;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Widget.CustomViewPage;


/**
 * Created by zhangziyu on 2017/6/27.
 */

public class ViewPagerBindingAdapter {

    @BindingAdapter( "isShowPulse")
    public static void setViewPager(CustomViewPage customViewPage, boolean showPager) {
        if (showPager) {
            customViewPage.setCurrentItem(0);
        } else {
            customViewPage.setCurrentItem(1);
        }
    }

}
