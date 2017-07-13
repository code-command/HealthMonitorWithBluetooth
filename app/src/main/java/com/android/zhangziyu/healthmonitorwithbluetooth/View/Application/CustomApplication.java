package com.android.zhangziyu.healthmonitorwithbluetooth.View.Application;

import android.app.Application;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.SystemInfo;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel.CustomSQLiteOpenHelper;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel.DataBaseStringHelper;


public class CustomApplication extends Application {

    private CustomSQLiteOpenHelper customSQLiteOpenHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        customSQLiteOpenHelper = new CustomSQLiteOpenHelper(getApplicationContext(), DataBaseStringHelper.DB_NAME, null, 1);
        customSQLiteOpenHelper.getWritableDatabase();
        SystemInfo.getSystemInfo().setCustomSQLiteOpenHelper(customSQLiteOpenHelper);
    }
}
