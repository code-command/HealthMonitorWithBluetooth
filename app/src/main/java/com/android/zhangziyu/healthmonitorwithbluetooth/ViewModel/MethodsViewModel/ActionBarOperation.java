package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;

public class ActionBarOperation {
    public static void setSystemActionBar(Context context, ActionBar actionBar, int backgroundId){
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, backgroundId)));
            actionBar.setSplitBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, backgroundId)));
        }
    }

    public static int getStatusBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen","android");
        return resources.getDimensionPixelSize(resourceId);
    }
}
