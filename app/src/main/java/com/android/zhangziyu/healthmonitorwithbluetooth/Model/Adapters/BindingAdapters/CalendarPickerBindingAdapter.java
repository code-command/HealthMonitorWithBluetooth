package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Adapters.BindingAdapters;

import android.databinding.BindingAdapter;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class CalendarPickerBindingAdapter {

    /**
     * 用于初始化CalendarPickerView控件
     * @param calendar  被初始化控件
     * @param initDateBoundary 控件显示的日历范围
     * @param initDateRange 控件选择的日历区间范围
     */
    @BindingAdapter({"initDateBoundary", "initDateRange"})
    public static void setCalendarPickerInit(CalendarPickerView calendar, List initDateBoundary, List initDateRange) {
        calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
        calendar.init( ((Calendar)initDateBoundary.get(0)).getTime(),
                ((Calendar)initDateBoundary.get(initDateBoundary.size()-1)).getTime() )
                .inMode(CalendarPickerView.SelectionMode.RANGE)
                .withSelectedDates(initDateRange);
    }
}
