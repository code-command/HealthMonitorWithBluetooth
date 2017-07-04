package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel;

/**
 * 接收按钮的接口，用于在开启与关闭Service时，在Activity中实现与界面相关的操作
 */

public interface OnReceiveButtonListener {
    public void startReceive();

    public void stopReceive();
}
