package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel;

public class DataBaseStringHelper {

    /**
     * 数据库公共字段
     **/
    public static final String DB_NAME = "HealthMonitorDB.db";
    public static final String TABLE_ITEM_ID = "id";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * User表 相关字符串定义
     **/
    public static final String TABLE_USER_NAME = "user";
    public static final String USER_ITEM_NAME = "name";
    public static final String USER_ITEM_PWD = "password";
    public static final String USER_ITEM_GENDER = "gender";
    public static final String USER_ITEM_AGE = "age";
    public static final String USER_ITEM_TEL = "telephone";

    /**
     * MonitorData表 相关字符串定义
     **/
    public static final String TABLE_MONITORDATA_NAME = "monitor_data";
    public static final String MD_ITEM_USERID = "user_id";
    public static final String MD_ITEM_SAVETIME = "save_time";
    public static final String MD_ITEM_PULSEDATA = "pulse_data";
    public static final String MD_ITEM_ECGDATA = "ecg_data";

    /**
     * 创建User表字段
     **/
    public static final String DB_CreateTable_User =
            "create table " + TABLE_USER_NAME + " ( "
                    + TABLE_ITEM_ID + " integer primary key autoincrement, "
                    + USER_ITEM_NAME + " varchar(32) NOT NULL, "
                    + USER_ITEM_PWD + " varchar(16) NOT NULL, "
                    + USER_ITEM_GENDER + " integer NOT NULL, "
                    + USER_ITEM_AGE + " integer, "
                    + USER_ITEM_TEL + " varchar(11) "
                    + ")";

    /**
     * 向表User中插入数据
     **/
    public static final String SQL_Insert_UserItem =
            "insert into " + TABLE_USER_NAME + " ( "
                    + TABLE_ITEM_ID + ", "
                    + USER_ITEM_NAME + ", "
                    + USER_ITEM_PWD + ", "
                    + USER_ITEM_GENDER + ", "
                    + USER_ITEM_AGE + ", "
                    + USER_ITEM_TEL
                    + ") values (?,?,?,?,?,?)";

    /**
     * 更新表user中的特定id的数据
     **/
    public static final String SQL_Update_UserItemById =
            "UPDATE " + TABLE_USER_NAME + " SET "
                    + USER_ITEM_NAME + " = ?, "
                    + USER_ITEM_PWD + " = ?, "
                    + USER_ITEM_GENDER + " = ?, "
                    + USER_ITEM_AGE + " = ?, "
                    + USER_ITEM_TEL + " = ? "
                    + " WHERE " + TABLE_ITEM_ID + " = ?";

    /**
     * 更新表user中的特定用户名的数据
     **/
    public static final String SQL_Update_UserItemByUserName =
            "UPDATE " + TABLE_USER_NAME + " SET "
                    + USER_ITEM_NAME + " = ?, "
                    + USER_ITEM_PWD + " = ?, "
                    + USER_ITEM_GENDER + " = ?, "
                    + USER_ITEM_AGE + " = ?, "
                    + USER_ITEM_TEL + " = ? "
                    + " WHERE " + USER_ITEM_NAME + " = ?";

    /**
     * 根据用户名查询用户id
     **/
    public static final String SQL_Select_UserIdByUserName =
            "SELECT " + TABLE_ITEM_ID + " FROM " + TABLE_USER_NAME
                    + " WHERE " + USER_ITEM_NAME + " = ?";

    /**
     * 根据用户名查询用户记录
     **/
    public static final String SQL_Select_UserByUserName =
            "SELECT * FROM " + TABLE_USER_NAME
                    + " WHERE " + USER_ITEM_NAME + "= ?";

    /**
     * 根据用户名查询记录是否存在
     **/
    public static final String SQL_Select_UserCountByUserName =
            "SELECT COUNT(*) FROM " + TABLE_USER_NAME
                    + " WHERE " + USER_ITEM_NAME + " = ? ";

    /**
     * 根据用户名与密码判断该记录是否存在
     **/
    public static final String SQL_Select_UserByUserNameAndUserPwd =
            "SELECT COUNT(*) FROM " + TABLE_USER_NAME
                    + " WHERE " + USER_ITEM_NAME + "= ? "
                    + " AND " + USER_ITEM_PWD + " = ? ";

    /**
     * 创建MonitorData表字段
     **/
    public static final String DB_CreateTable_MonitorData =
            "create table " + TABLE_MONITORDATA_NAME + " ( "
                    + TABLE_ITEM_ID + " integer primary key autoincrement, "
                    + MD_ITEM_USERID + " integer NOT NULL, "
                    + MD_ITEM_SAVETIME + " time NOT NULL, "
                    + MD_ITEM_PULSEDATA + " text NOT NULL, "
                    + MD_ITEM_ECGDATA + " text NOT NULL, "
                    + "foreign key (" + MD_ITEM_USERID + ") references "
                    + TABLE_USER_NAME + " (" + TABLE_ITEM_ID + ")"
                    + ")";

    /**
     * 向表monitor_data中
     **/
    public static final String SQL_Insert_MonitorDataItem =
            "insert into " + TABLE_MONITORDATA_NAME + " ( "
                    + TABLE_ITEM_ID + ", "
                    + MD_ITEM_USERID + ", "
                    + MD_ITEM_SAVETIME + ", "
                    + MD_ITEM_PULSEDATA + ", "
                    + MD_ITEM_ECGDATA
                    + ") values (?, ?, ?, ?, ?)";


    /**
     * 根据用户Id查询此用户所有记录的保存时间
     **/
    public static final String SQL_Select_AllSaveTimeByUserIdAndTimeRange =
            "SELECT " + MD_ITEM_SAVETIME + " FROM " + TABLE_MONITORDATA_NAME +
                    " WHERE " + MD_ITEM_USERID + " = ? " +
                    "AND (datetime(" + MD_ITEM_SAVETIME + ") between datetime(?) and datetime(?))";

    /**
     * 根据用户id与保存时间查询记录条数
     */
    public static final String SQL_Select_ItemCountByByUserIdAndSaveTime =
            "SELECT COUNT (" + MD_ITEM_USERID + ") FROM " + TABLE_MONITORDATA_NAME +
                    " WHERE " + MD_ITEM_USERID + " = ? " +
                    "AND datetime(" + MD_ITEM_SAVETIME + ") = datetime(?)";

    /**
     * 根据用户id与保存时间查询脉搏与心跳数据
     **/
    public static final String SQL_Select_AllDataByUserIdAndSaveTime =
            "SELECT " + MD_ITEM_PULSEDATA + ", " + MD_ITEM_ECGDATA + " FROM " + TABLE_MONITORDATA_NAME +
                    " WHERE " + MD_ITEM_USERID + " = ? " +
                    "AND datetime(" + MD_ITEM_SAVETIME + ") = datetime(?)";

}
