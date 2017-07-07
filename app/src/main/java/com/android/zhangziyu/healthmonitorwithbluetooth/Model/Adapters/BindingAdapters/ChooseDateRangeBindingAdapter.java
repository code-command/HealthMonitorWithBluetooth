package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Adapters.BindingAdapters;

import android.databinding.BindingAdapter;
import android.widget.Button;

import com.android.zhangziyu.healthmonitorwithbluetooth.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by zhangziyu on 2017/7/5.
 */

public class ChooseDateRangeBindingAdapter {

    /**
     * 用于控制显示选择时间范围的控件的显示
     * @param button    被操作按键控件
     * @param chooseDate    是否完成时间范围选择
     * @param data  已经选择的时间范为List
     */
    @BindingAdapter({"isChooseDate", "ChooseDateList"})
    public static void setDoneButtonText(Button button, boolean chooseDate, List data) {
        if (data.isEmpty()) {
            button.setText(R.string.choose_date_hint);
            return;
        }

        if (!chooseDate) {
            return;
        }

        SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd");
        String str = ft.format(data.get(0)) + " --- " + ft.format(data.get(data.size() - 1));
        button.setText(str);
    }
}
