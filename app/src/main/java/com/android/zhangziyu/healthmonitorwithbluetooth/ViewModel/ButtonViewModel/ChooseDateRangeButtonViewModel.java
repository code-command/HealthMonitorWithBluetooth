package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.SystemInfo;
import com.android.zhangziyu.healthmonitorwithbluetooth.R;
import com.android.zhangziyu.healthmonitorwithbluetooth.databinding.DialogCalenderBinding;
import com.squareup.timessquare.CalendarPickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChooseDateRangeButtonViewModel implements OnClickListener {

    private ArrayList<Calendar> initBoundary = new ArrayList<>();
    private ArrayList<Date> initRange = new ArrayList<>();
    private ArrayList<Date> chooseRange;

    public ChooseDateRangeButtonViewModel(ArrayList<Date> chooseRange) {
        Calendar currentCalender = Calendar.getInstance();
        currentCalender.add(Calendar.YEAR, -2);
        initBoundary.add(currentCalender);
        currentCalender = Calendar.getInstance();
        currentCalender.add(Calendar.MONTH, 1);
        initBoundary.add(currentCalender);

        this.chooseRange = chooseRange;
    }

    @Override
    public void onClick(View v) {
        initData();
        initDialog(v);
    }

    private void initData() {
        if (chooseRange.isEmpty()) {
            Calendar currentCalender = Calendar.getInstance();
            currentCalender = Calendar.getInstance();
            currentCalender.add(Calendar.DATE, -7);
            initRange.add(currentCalender.getTime());
            currentCalender = Calendar.getInstance();
            initRange.add(currentCalender.getTime());
        } else {
            initRange.clear();
            initRange.add(chooseRange.get(0));
            initRange.add(chooseRange.get(chooseRange.size()-1));
        }
    }

    private void initDialog(View v) {
        DialogCalenderBinding binding;
        binding = DataBindingUtil.inflate(LayoutInflater.from(v.getContext()), R.layout.dialog_calender, null, false);
        binding.setInitDateBoundary(initBoundary);
        binding.setInitDataRange(initRange);

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setView(binding.getRoot());
        setPositiveButton(builder, binding.calendarView, chooseRange);
        setNegativeButton(builder)
                .create()
                .show();
    }

    private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder,
                                                  final CalendarPickerView calendarPickerView,
                                                  final List<Date> chooseRange) {
        return builder.setPositiveButton(R.string.units_options_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chooseRange.clear();
                chooseRange.addAll(calendarPickerView.getSelectedDates());
                SystemInfo systemInfo = SystemInfo.getSystemInfo();
                systemInfo.setChooseDateRange(true);
            }
        });
    }

    private AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder) {
        return builder.setNegativeButton(R.string.units_options_cancel, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

    }
}
