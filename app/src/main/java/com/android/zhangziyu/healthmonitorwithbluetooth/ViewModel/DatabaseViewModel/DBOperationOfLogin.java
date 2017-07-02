package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel;


import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.User;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.ErrorCode;

import java.util.List;

/**
 * Created by zhangziyu on 2017/6/29.
 */

public class DBOperationOfLogin {

    public static boolean checkLogin(User user, List<ErrorCode> list) {
        return DBOperationOfBase.checkInfoIntegrity(user, list) && verifyUser(user, list);
    }

    private static boolean verifyUser (User user, List<ErrorCode> list) {
        /*此代码为验证功能所使用，后期会改成向数据库中查询比对结果*/
        if (user.getName().equals(user.getPassword())) {
            return true;
        } else {
            list.add(ErrorCode.USER_LOGINFAILURE);
            return false;
        }
    }
}
