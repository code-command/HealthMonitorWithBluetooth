package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.MonitorData;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.SystemInfo;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 完成数据库所有的增、删、改、查、相关操作
 */
public class SQLiteOperation {
    private static CustomSQLiteOpenHelper customSQLiteOpenHelper = SystemInfo.getSystemInfo().getCustomSQLiteOpenHelper();

    /**
     * 用于向数据表user中插入数据
     *
     * @param user 需要插入的数据
     */
    public static void insertItem2User(User user) {
        if (customSQLiteOpenHelper == null) {
            return;
        }
        SQLiteDatabase dbWrite = customSQLiteOpenHelper.getWritableDatabase();
        dbWrite.beginTransaction(); //开启事务
        dbWrite.execSQL(DataBaseStringHelper.SQL_Insert_UserItem,
                new Object[]{null, user.getName(), user.getPassword(),
                        user.getGender(), user.getAge(), user.getTelephone()});
        dbWrite.setTransactionSuccessful(); //事务已经执行成功
        dbWrite.endTransaction();   //结束事务
    }

    /**
     * 更新表user中指定用户名的数据
     *
     * @param userName 需更新的用户名
     * @param user   更新的数据
     */
    public static void updateItemOfUserByUserName(String userName, User user) {
        if (customSQLiteOpenHelper == null) {
            return;
        }
        SQLiteDatabase dbWrite = customSQLiteOpenHelper.getWritableDatabase();
        dbWrite.beginTransaction(); //开启事务
        dbWrite.execSQL(DataBaseStringHelper.SQL_Update_UserItemByUserName,
                new Object[]{user.getName(), user.getPassword(), user.getGender(),
                        user.getAge(), user.getTelephone(), userName});
        dbWrite.setTransactionSuccessful(); //事务已经执行成功
        dbWrite.endTransaction();   //结束事务
    }

    /**
     * 更新表user中指定id的数据
     *
     * @param userId 需更新的数据id
     * @param user   更新的数据
     */
    public static void updateItemOfUserById(int userId, User user) {
        if (customSQLiteOpenHelper == null) {
            return;
        }
        SQLiteDatabase dbWrite = customSQLiteOpenHelper.getWritableDatabase();
        dbWrite.beginTransaction(); //开启事务
        dbWrite.execSQL(DataBaseStringHelper.SQL_Update_UserItemById,
                new Object[]{user.getName(), user.getPassword(), user.getGender(),
                        user.getAge(), user.getTelephone(), userId});
        dbWrite.setTransactionSuccessful(); //事务已经执行成功
        dbWrite.endTransaction();   //结束事务
    }

    /**
     * 查询表user中指定用户名的数据，用于展示当前用户信息
     *
     * @param userName
     * @return
     */
    public static User getItemOfUserByUserName(String userName) {
        User user = null;
        if (customSQLiteOpenHelper == null) {
            return user;
        }
        SQLiteDatabase dbReader = customSQLiteOpenHelper.getReadableDatabase();
        dbReader.beginTransaction(); //开启事务
        Cursor cursor = dbReader.rawQuery(DataBaseStringHelper.SQL_Select_UserByUserName,
                new String[]{userName});
        if (cursor.moveToFirst()) {
            user = new User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3) == 1,
                    cursor.getInt(4),
                    cursor.getString(5)
            );
        }
        dbReader.setTransactionSuccessful(); //事务已经执行成功
        dbReader.endTransaction();   //结束事务
        return user;
    }

    /**
     * 查询表user中当前用户名的数据条数，用于判定此用户名是否已被注册
     *
     * @param userName 用户名
     * @return
     */
    public static int getItemCountOfUserByUserName(String userName) {
        if (customSQLiteOpenHelper == null) {
            return -1;
        }
        int itemCount = 0;
        SQLiteDatabase dbReader = customSQLiteOpenHelper.getReadableDatabase();
        dbReader.beginTransaction(); //开启事务
        Cursor cursor = dbReader.rawQuery(DataBaseStringHelper.SQL_Select_UserCountByUserName,
                new String[]{userName});
        if (cursor.moveToFirst()) {
            itemCount = cursor.getInt(0);
        }
        dbReader.setTransactionSuccessful(); //事务已经执行成功
        dbReader.endTransaction();   //结束事务
        return itemCount;
    }

    /**
     * 查询表user中当前用户名与用户密码的数据条数，用于判定用户登录是否成功
     *
     * @param userName 用户名
     * @param userPwd  用户密码
     * @return
     */
    public static int getItemCountOfUserByUserNameAndUserPwd(String userName, String userPwd) {
        if (customSQLiteOpenHelper == null) {
            return -1;
        }
        int itemCount = 0;
        SQLiteDatabase dbReader = customSQLiteOpenHelper.getReadableDatabase();
        dbReader.beginTransaction(); //开启事务
        Cursor cursor = dbReader.rawQuery(DataBaseStringHelper.SQL_Select_UserByUserNameAndUserPwd,
                new String[]{userName, userPwd});
        if (cursor.moveToFirst()) {
            itemCount = cursor.getInt(0);
        }
        dbReader.setTransactionSuccessful(); //事务已经执行成功
        dbReader.endTransaction();   //结束事务
        return itemCount;
    }

    /**
     * 查询表user中指定用户名的数据的id，用于后续查询相应的保存的采集数据
     *
     * @param userName 用户名
     * @return
     */
    public static int getItemIdOfUserByUserName(String userName) {
        if (customSQLiteOpenHelper == null) {
            return -1;
        }
        int itemCount = 0;
        SQLiteDatabase dbReader = customSQLiteOpenHelper.getReadableDatabase();
        dbReader.beginTransaction(); //开启事务
        Cursor cursor = dbReader.rawQuery(DataBaseStringHelper.SQL_Select_UserIdByUserName,
                new String[]{userName});
        if (cursor.moveToFirst()) {
            itemCount = cursor.getInt(0);
        }
        dbReader.setTransactionSuccessful(); //事务已经执行成功
        dbReader.endTransaction();   //结束事务
        return itemCount;
    }

    /**
     * 向表monitor_data中插入新数据
     *
     * @param monitorData   新数据结构
     */
    public static void insertItem2MonitorData(MonitorData monitorData) {
        if (customSQLiteOpenHelper == null) {
            return;
        }
        SQLiteDatabase dbWrite = customSQLiteOpenHelper.getWritableDatabase();
        dbWrite.beginTransaction(); //开启事务;
        dbWrite.execSQL(DataBaseStringHelper.SQL_Insert_MonitorDataItem,
                new Object[]{null, monitorData.getUserId(), new Timestamp(monitorData.getSaveTime().getTime()),
                        monitorData.getPulseDataOfJson(), monitorData.getEcgDataOfJson()});
        dbWrite.setTransactionSuccessful(); //事务已经执行成功
        dbWrite.endTransaction();   //结束事务
    }

    /**
     * 查询表monitor_data中指定用户id与查询时间范围查询范围内的所有记录的时间
     *
     * @param userId    用户id
     * @param beginTime 起始时间
     * @param endTime   终止时间
     * @return
     */
    public static List<Date> getAllSaveTimeOfMDByUserIdAndTimeRange(int userId, Date beginTime, Date endTime) {
        ArrayList<Date> selectDateResult = new ArrayList<>();
        if (customSQLiteOpenHelper == null) {
            return selectDateResult;
        }
        SQLiteDatabase dbReader = customSQLiteOpenHelper.getReadableDatabase();
        dbReader.beginTransaction(); //开启事务
        SimpleDateFormat format = new SimpleDateFormat(DataBaseStringHelper.DATE_FORMAT);
        Cursor cursor = dbReader.rawQuery(DataBaseStringHelper.SQL_Select_AllSaveTimeByUserIdAndTimeRange,
                new String[]{String.valueOf(userId), format.format(beginTime), format.format(endTime)});
        while (cursor.moveToNext()) {
            try {
                selectDateResult.add((Date) format.parse(cursor.getString(0)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        dbReader.setTransactionSuccessful(); //事务已经执行成功
        dbReader.endTransaction();   //结束事务
        return selectDateResult;
    }

    /**
     * 查询表monitor_data中指定用户id与保存的数据条数，用于判定插入成功
     * @param userId    用户id
     * @param saveTime  保存时间
     * @return
     */
    public static int getItemCountOfMDByUserIdAndSaveTime(int userId, Date saveTime) {
        if (customSQLiteOpenHelper == null) {
            return -1;
        }
        int itemCount = 0;
        SQLiteDatabase dbReader = customSQLiteOpenHelper.getReadableDatabase();
        dbReader.beginTransaction(); //开启事务
        SimpleDateFormat format = new SimpleDateFormat(DataBaseStringHelper.DATE_FORMAT);
        Cursor cursor = dbReader.rawQuery(DataBaseStringHelper.SQL_Select_ItemCountByByUserIdAndSaveTime,
                new String[]{String.valueOf(userId), format.format(saveTime)});
        if (cursor.moveToFirst()) {
            itemCount = cursor.getInt(0);
        }
        dbReader.setTransactionSuccessful(); //事务已经执行成功
        dbReader.endTransaction();   //结束事务
        return itemCount;
    }

    /**
     * 查询表monitor_data中指定用户id与保存时间的数据，用于历史记录展示
     *
     * @param userId    用户id
     * @param saveTime  保存时间
     * @return
     */
    public static Map getAllDataOfMDByUserIdAndSaveTime(int userId, Date saveTime) {
        Map<String, ArrayList<Float>> selectDataResult = null;
        if (customSQLiteOpenHelper == null) {
            return selectDataResult;
        }
        selectDataResult = new HashMap<>();
        SQLiteDatabase dbReader = customSQLiteOpenHelper.getReadableDatabase();
        dbReader.beginTransaction(); //开启事务
        SimpleDateFormat format = new SimpleDateFormat(DataBaseStringHelper.DATE_FORMAT);
        Cursor cursor = dbReader.rawQuery(DataBaseStringHelper.SQL_Select_AllDataByUserIdAndSaveTime,
                new String[]{String.valueOf(userId), format.format(saveTime)});
        if (cursor.moveToFirst()) {
            Gson gson = new Gson();
            selectDataResult.put(DataBaseStringHelper.MD_ITEM_PULSEDATA,
                    (ArrayList<Float>) gson.fromJson(cursor.getString(0), new TypeToken<ArrayList<Float>>() {
                    }.getType()));
            selectDataResult.put(DataBaseStringHelper.MD_ITEM_ECGDATA,
                    (ArrayList<Float>) gson.fromJson(cursor.getString(1), new TypeToken<ArrayList<Float>>() {
                    }.getType()));
        }
        dbReader.setTransactionSuccessful(); //事务已经执行成功
        dbReader.endTransaction();   //结束事务
        return selectDataResult;
    }
}
