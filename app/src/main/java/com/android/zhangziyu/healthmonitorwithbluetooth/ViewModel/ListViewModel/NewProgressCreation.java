package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ListViewModel;

import java.util.Map;

/**
 * 用于在Activity中实现List单击时向数据接收界面的跳转操作
 */
public interface NewProgressCreation {
    public void startNewActivity(Map<String, String> extraMap);
}
