package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.User;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.ErrorCode;

import java.util.List;

/**
 * Created by zhangziyu on 2017/6/29.
 */

public class DBOperationOfBase {

    /**
     * 验证用户信息的用户名与密码的格式
     * @param user  用户信息
     * @param list  判断错误码存放
     * @return
     */
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
