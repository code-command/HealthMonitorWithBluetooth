package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.ref.WeakReference;

import static com.android.zhangziyu.healthmonitorwithbluetooth.Model.Widget.OptimizationToast.showToast;


public class CustomSQLiteOpenHelper extends SQLiteOpenHelper {

    private Context context;

    public CustomSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseStringHelper.DB_CreateTable_User);
        db.execSQL(DataBaseStringHelper.DB_CreateTable_MonitorData);
        showToast(new WeakReference<Context>(context), "Create DataBase");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void deleteDataBase() {
        context.deleteDatabase(DataBaseStringHelper.DB_NAME);
        showToast(new WeakReference<Context>(context), "Delete DataBase");
    }
}
