package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Adapters.BindingAdapters;

import android.databinding.BindingAdapter;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhangziyu on 2017/7/5.
 */

public class CalendarPickerBindingAdapter {

    @BindingAdapter({"initDateBoundary", "initDateRange"})
    public static void setCalendarPickerInit(CalendarPickerView calendar, List initDateBoundary, List initDateRange) {
        calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
        calendar.init( ((Calendar)initDateBoundary.get(0)).getTime(),
                ((Calendar)initDateBoundary.get(initDateBoundary.size()-1)).getTime() )
                .inMode(CalendarPickerView.SelectionMode.RANGE)
                .withSelectedDates(initDateRange);
    }
}
