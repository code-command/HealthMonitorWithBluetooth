package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel;

import android.view.View;
import android.widget.Button;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.SystemInfo;
import com.android.zhangziyu.healthmonitorwithbluetooth.R;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.EffectiveClick;


/**
 * Created by zhangziyu on 2017/6/27.
 */

public class SwitchPagerButtonViewModel implements View.OnClickListener {


    @Override
    public void onClick(View v) {
        if (EffectiveClick.isEffectiveDoubleClick()) {
            SystemInfo systemInfo = SystemInfo.getSystemInfo();
            if (systemInfo.isReceive()) {
                Button button = (Button) v;
                if (systemInfo.isPulseShow()) {
                    systemInfo.setPulseShow(false);
                    button.setText(R.string.ecg);
                } else {
                    systemInfo.setPulseShow(true);
                    button.setText(R.string.pulse);
                }
            }
        }
    }
}
