package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel;

import android.database.sqlite.SQLiteDatabase;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.User;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.ErrorCode;

import java.util.List;

/**
 * Created by zhangziyu on 2017/6/29.
 */

public class DBOperationOfRegister {

    /**
     * 验证注册信息是否正确
     *
     * @param user 用户信息
     * @param list 保存错误码返回
     * @return
     */
    public static boolean registerUser(User user, List<ErrorCode> list) {
        return DBOperationOfBase.checkInfoIntegrity(user, list)
                && checkNameUnique(user, list)
                && checkPwdFormat(user, list)
                && saveNewUser(user, list);
    }

    /**
     * 验证用户名是否已被使用
     *
     * @param user 用户信息
     * @param list 保存错误码返回
     * @return
     */
    private static boolean checkNameUnique(User user, List<ErrorCode> list) {
        if (SQLiteOperation.getItemCountOfUserByUserName(user.getName()) > 0) {
            list.add(ErrorCode.USERNAME_NOTUNIQUE);
            return false;
        }
        return true;
    }

    /**
     * 验证用户密码是否符合规范
     *
     * @param user 用户信息
     * @param list 保存错误码返回
     * @return
     */
    private static boolean checkPwdFormat(User user, List<ErrorCode> list) {
        /*此代码为验证功能所使用，后期进行争取的非法字符校验*/
        if (user.getPassword().equals("////////")) {
            list.add(ErrorCode.USERPWD_ERRORFORMAT);
            return false;
        }
        return true;
    }

    /**
     * 完成新型用户注册
     *
     * @param user 用户信息
     * @param list 保存错误码返回
     * @return
     */
    private static boolean saveNewUser(User user, List<ErrorCode> list) {
        SQLiteOperation.insertItem2User(user);
        if (SQLiteOperation.getItemCountOfUserByUserName(user.getName()) > 0) {
            return true;
        }
        list.add(ErrorCode.REGISTER_SAVEFAILURE);
        return false;
    }

    /**
     * 完成个人信息设置
     *
     * @param oldUser
     * @param newUser
     * @return
     */
    public static boolean reviseUserInfo(User oldUser, User newUser) {
        SQLiteOperation.updateItemOfUserByUserName(oldUser.getName(), newUser);
        if (SQLiteOperation.getItemCountOfUserByUserName(newUser.getName()) > 0) {
            return true;
        }
        return false;
    }
}
