package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel;

import android.view.View;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.SystemInfo;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.CmdCode;

/**
 * Created by zhangziyu on 2017/7/3.
 */

public class ReceiveButtonViewModel implements View.OnClickListener {
    private OnReceiveButtonListener onReceiveButtonListener;

    public void setOnReceiveButtonListener(OnReceiveButtonListener onReceiveButtonListener) {
        this.onReceiveButtonListener = onReceiveButtonListener;
    }

    @Override
    public void onClick(View v) {
        SystemInfo systemInfo = SystemInfo.getSystemInfo();
        if (systemInfo.isReceive()) {
            systemInfo.setReceive(false);
            systemInfo.setCmdCode(CmdCode.CMD_EMPTY);
            onReceiveButtonListener.stopReceive();
        } else {
            systemInfo.setReceive(true);
            onReceiveButtonListener.startReceive();
        }
    }
}
