package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Adapters.BindingAdapters;

import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.zhangziyu.healthmonitorwithbluetooth.R;

/**
 * Created by zhangziyu on 2017/7/7.
 */

public class BluetoothTextBindingAdapter {
    /**
     * 用于实现数据接收时显示的清除
     * @param textView
     * @param clear 是否清空显示
     */
    @android.databinding.BindingAdapter("isClear")
    public static void clearReceiveText(TextView textView, boolean clear) {
        if (clear) {
            textView.setText(R.string.empty);
        }
    }

    /**
     * 用于实现数据接收时切换接收按钮
     * @param textView
     * @param newData   待显示新数据
     * @param receive   是否完成接收
     */
    @android.databinding.BindingAdapter({"newReceiveData", "isReceiveing"})
    public static void updateReceiveText(TextView textView, String newData, boolean receive) {
        if (receive) {
            textView.append(newData);
        } else {
            textView.setText(R.string.empty);
        }
    }

    /**
     * 用于实现开关蓝牙时界面的变换
     * @param view
     * @param found 是否已经发现设备
     */
    @android.databinding.BindingAdapter("btLinearLayoutHandover")
    public static void setLinearLayoutHandover(LinearLayout view, boolean found) {
        RelativeLayout.LayoutParams paramsr = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        if (found) {
            adjustLinearLayout(view, paramsr);
        } else {
            recoverLinearLayout(view, paramsr);
        }
        view.setLayoutParams(paramsr);
    }

    /**
     * 当发现设备时，界面进行相应的界面调整
     * @param view
     * @param paramsr
     */
    private static void adjustLinearLayout(LinearLayout view, RelativeLayout.LayoutParams paramsr) {
        paramsr.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        final int paddingTop = (int)(view.getWidth()*0.2);
        paramsr.setMargins(0, paddingTop, 0, 0);
        view.setLayoutParams(paramsr);
        view.setOrientation(LinearLayout.HORIZONTAL);		//设置为水平排列
        view.setGravity(Gravity.CENTER_VERTICAL);
    }

    /**
     * 当清空发现设备时，界面进行相应的界面恢复
     * @param view
     * @param paramsr
     */
    private static void recoverLinearLayout(LinearLayout view, RelativeLayout.LayoutParams paramsr) {
        paramsr.addRule(RelativeLayout.CENTER_IN_PARENT);
        view.setLayoutParams(paramsr);
        view.setOrientation(LinearLayout.VERTICAL);
        view.setGravity(Gravity.CENTER_HORIZONTAL);
    }
}
