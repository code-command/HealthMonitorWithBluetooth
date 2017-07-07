package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Widget;

import android.content.Context;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * 自定义Toast，使得多次单击不会连续弹出显示窗口
 */
public class OptimizationToast {
    private static StringBuilder oldMsg = new StringBuilder();
    private static Toast toast = null;
    private static long oldTime = 0;
    private static long newTime = 0;

    private OptimizationToast() {}

    private static synchronized void SynInit(WeakReference<Context> context, String msg) {
        toast = Toast.makeText(context.get(), msg, Toast.LENGTH_SHORT);
        toast.show();
        oldTime = System.currentTimeMillis();
    }

    public static void showToast(WeakReference<Context> context, String msg) {
        if (toast == null) {
            SynInit(context, msg);
        } else {
            newTime = System.currentTimeMillis();
            if (msg.equals(oldMsg.toString())) {
                if (newTime - oldTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg.setLength(0);
                oldMsg.append(msg);
                toast.setText(msg);
                toast.show();
            }
        }
        oldTime = newTime;
    }
}
