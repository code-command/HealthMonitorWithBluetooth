package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Adapters.BindingAdapters;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/6/20 0020.
 */

public class ListViewBindingAdapter {

    /**
     * 用于控制ListView是否显示头尾分割线
     * @param listView  被操作的ListView控件
     * @param isHeader  是否显示头部分割线
     * @param isFooter  是否显示尾部分割线
     */
    @BindingAdapter({"isShowHeader", "isShowFooter"})
    public static void setListViewBorderLine(ListView listView, boolean isHeader, boolean isFooter) {
        if (isHeader && listView.getHeaderViewsCount()==0) {
            listView.addHeaderView(new View(listView.getContext()));
        } else if (listView.getHeaderViewsCount()>0) {
            listView.removeHeaderView(new View(listView.getContext()));
        }
        if (isFooter && listView.getFooterViewsCount()==0) {
            listView.addFooterView(new View(listView.getContext()));
        } else if (listView.getFooterViewsCount()>0) {
            listView.removeFooterView(new View(listView.getContext()));
        }
    }
}
