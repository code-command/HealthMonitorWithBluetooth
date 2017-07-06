package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel;

/**
 * Created by zhangziyu on 2017/7/6.
 */

public class CommonUtils {
    private static long lastClickTime;

    public static boolean isEffectiveDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800) {
            return false;
        }
        lastClickTime = time;
        return true;
    }
}
