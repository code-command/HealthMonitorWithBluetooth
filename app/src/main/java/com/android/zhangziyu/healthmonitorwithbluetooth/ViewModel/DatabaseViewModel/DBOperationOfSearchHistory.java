package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.SystemInfo;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangziyu on 2017/7/6.
 */

public class DBOperationOfSearchHistory {

    /**
     * 用于查询制定范围内当前用户的保存记录名称，名称由用户名与保存时间组成
     *
     * @param dateRange    时间范围
     * @param searchResult 查询结果
     * @return
     */
    public static boolean searchHistoryRecordNameWithDateRange(List dateRange, List searchResult) {

        User user = SystemInfo.getSystemInfo().getUser();
        List<Date> selectResult = SQLiteOperation.getAllSaveTimeOfMDByUserIdAndTimeRange(user.getId(),
                (Date) dateRange.get(0), (Date) dateRange.get(dateRange.size() - 1));
        if (selectResult.isEmpty()) {
            searchResult.clear();
            return false;
        }

        SimpleDateFormat format = new SimpleDateFormat(DataBaseStringHelper.DATE_FORMAT);
        searchResult.clear();
        for (Date date : selectResult) {
            String item = user.getName() + " -- " + format.format(date);
            searchResult.add(item);
        }
        return true;
    }

    /**
     * 根据选定的时间来查找数据
     *
     * @param recordName      项名
     * @param pulseDataValues 存放脉搏数据
     * @param ecgDataValues   存放心电数据
     * @return
     */
    public static boolean searchHistoryMonitorWithRecordName(String recordName,
                                                             ArrayList<Float> pulseDataValues,
                                                             ArrayList<Float> ecgDataValues) {
        String[] itemName = recordName.split(" -- ");
        if (itemName.length != 2) {
            return false;
        }
        int userId = SystemInfo.getSystemInfo().getUser().getId();
        SimpleDateFormat format = new SimpleDateFormat(DataBaseStringHelper.DATE_FORMAT);

        try {
            Map<String, ArrayList<Float>> selectResult =
                    SQLiteOperation.getAllDataOfMDByUserIdAndSaveTime(userId, format.parse(itemName[1]));
            pulseDataValues.clear();
            ecgDataValues.clear();
            pulseDataValues.addAll(selectResult.get(DataBaseStringHelper.MD_ITEM_PULSEDATA));
            ecgDataValues.addAll(selectResult.get(DataBaseStringHelper.MD_ITEM_ECGDATA));
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            pulseDataValues.clear();
            ecgDataValues.clear();
            return false;
        }
    }
}
