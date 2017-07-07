package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel;

import android.view.View;
import android.view.View.OnClickListener;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.SystemInfo;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.CmdCode;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.EffectiveClick;

/**
 * Created by zhangziyu on 2017/6/26.
 */

public class ClearButtonViewModel implements OnClickListener {

    @Override
    public void onClick(View v) {
        if (EffectiveClick.isEffectiveDoubleClick()) {
            SystemInfo systemInfo = SystemInfo.getSystemInfo();
            if (systemInfo.isOpen()) {
                systemInfo.setCmdCode(CmdCode.CMD_CLEAR);
            }
        }
    }
}
