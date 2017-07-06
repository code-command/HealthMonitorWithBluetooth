package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.User;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.ErrorCode;

import java.util.List;

/**
 * Created by zhangziyu on 2017/6/29.
 */

public class DBOperationOfRegister {

    public static boolean registerUser(User user, List<ErrorCode> list) {
        return DBOperationOfBase.checkInfoIntegrity(user, list) && checkNameUnique(user ,list) && checkPwdFormat(user, list) && saveNewUser(user, list);
    }

    private static boolean checkNameUnique(User user, List<ErrorCode> list) {
        /*此代码为验证功能所使用，后期会改成向数据库中查询并返回查询结果*/
        if (user.getName().equals("errorName")) {
            list.add(ErrorCode.USERNAME_NOTUNIQUE);
            return false;
        }
        return true;
    }

    private static boolean checkPwdFormat(User user, List<ErrorCode> list) {
        /*此代码为验证功能所使用，后期进行争取的非法字符校验*/
        if (user.getPassword().equals("////////")) {
            list.add(ErrorCode.USERPWD_ERRORFORMAT);
            return false;
        }
        return true;
    }

    private static boolean saveNewUser(User user, List<ErrorCode> list) {
        /*此代码为验证功能所使用，后期会改成向数据库中插入数据并返回插入结果*/
        if (user.getName().equals("saveerror")) {
            list.add(ErrorCode.REGISTER_SAVEFAILURE);
            return false;
        }
        return true;
    }

    public static boolean reviseUserInfo(User oldUser, User newUser) {
        if (newUser.getName().equals("Error"))
            return false;
        return true;
    }
}
