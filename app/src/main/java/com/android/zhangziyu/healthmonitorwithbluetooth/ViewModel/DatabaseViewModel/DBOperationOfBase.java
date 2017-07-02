package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.User;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.ErrorCode;

import java.util.List;

/**
 * Created by zhangziyu on 2017/6/29.
 */

public class DBOperationOfBase {

    public static boolean checkInfoIntegrity(User user, List<ErrorCode> list) {
        if (user.getName()==null || user.getName().isEmpty()) {
            list.add(ErrorCode.EMPTY_USERNAME);
            return false;
        }
        if (user.getPassword()==null || user.getPassword().isEmpty()) {
            list.add(ErrorCode.EMPTY_USERPWD);
            return false;
        }
        return true;
    }
}
