package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel;


import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.User;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.ErrorCode;

import java.util.List;

/**
 * Created by zhangziyu on 2017/6/29.
 */

public class DBOperationOfLogin {

    /**
     * 验证登录是否正确
     * @param user  登录用户信息
     * @param list  保存错误码返回
     * @return
     */
    public static boolean checkLogin(User user, List<ErrorCode> list) {
        return DBOperationOfBase.checkInfoIntegrity(user, list) && verifyUser(user, list);
    }

    /**
     * 验证输入的用户名与密码是否正确
     * @param user  登录用户信息，若信息正确，则完善用户信息
     * @param list  保存错误码返回
     * @return
     */
    private static boolean verifyUser (User user, List<ErrorCode> list) {
        if (SQLiteOperation.getItemCountOfUserByUserNameAndUserPwd(user.getName(), user.getPassword())>0) {
            user.setUser(SQLiteOperation.getItemOfUserByUserName(user.getName()));
            return true;
        } else {
            list.add(ErrorCode.USER_LOGINFAILURE);
            return false;
        }
    }
}
