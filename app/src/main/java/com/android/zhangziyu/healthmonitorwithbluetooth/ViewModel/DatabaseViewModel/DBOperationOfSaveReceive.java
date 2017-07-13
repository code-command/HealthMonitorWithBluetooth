package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.MonitorData;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangziyu on 2017/7/4.
 */

public class DBOperationOfSaveReceive {

    /**
     * 保存采集来的脉搏与心电数据
     *
     * @param userId       用户id
     * @param monitorDataMap 采集的数据
     * @return
     */
    public static boolean saveReceiveData(int userId, Map<String, ArrayList<Float>> monitorDataMap) {
        Calendar calendar = Calendar.getInstance();
        MonitorData monitorData = new MonitorData(userId, calendar.getTime(),
                monitorDataMap.get(DataBaseStringHelper.MD_ITEM_PULSEDATA),
                monitorDataMap.get(DataBaseStringHelper.MD_ITEM_ECGDATA));
        SQLiteOperation.insertItem2MonitorData(monitorData);
        if (SQLiteOperation.getItemCountOfMDByUserIdAndSaveTime(userId, calendar.getTime()) > 0) {
            return true;
        }
        return false;
    }
}
