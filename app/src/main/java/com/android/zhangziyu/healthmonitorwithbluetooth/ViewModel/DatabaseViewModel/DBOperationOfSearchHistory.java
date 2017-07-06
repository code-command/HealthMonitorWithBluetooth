package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangziyu on 2017/7/6.
 */

public class DBOperationOfSearchHistory {

    public static boolean searchHistoryRecordNameWithDateRange(List dateRange, List searchResult) {
        int dataRangeLength = dateRange.size();
        if (dataRangeLength>5) {
            SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss a zzz");
            searchResult.clear();
            for (int i=0; i<dataRangeLength; i++) {
                searchResult.add(ft.format((Date)dateRange.get(i)));
            }
            return true;
        }
        searchResult.clear();
        return false;
    }

    public static boolean searchHistoryMonitorWithRecordName(String recordName,
                                                             ArrayList<Float> pulseDataValues,
                                                             ArrayList<Float> ecgDataValues) {
        pulseDataValues.clear();
        ecgDataValues.clear();
        if (recordName.isEmpty()) {
            return true;
        }

        for (int i=0; i<100; i++) {
            pulseDataValues.add((float) (Math.random() * 10) + 50f);
        }

        for (int i=0; i<100; i++) {
            ecgDataValues.add((float) (Math.random() * 10) + 50f);
        }
        return true;
    }
}
