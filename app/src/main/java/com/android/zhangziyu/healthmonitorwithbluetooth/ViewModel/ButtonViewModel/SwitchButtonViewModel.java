package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel;

import android.bluetooth.BluetoothAdapter;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.EffectiveClick;

/**
 * Created by zhangziyu on 2017/7/2.
 */

public class SwitchButtonViewModel implements OnClickListener {

    private BluetoothAdapter btAdapter;

    public SwitchButtonViewModel(BluetoothAdapter btAdapter) {
        this.btAdapter = btAdapter;
    }


    @Override
    public void onClick(View view) {
        if (EffectiveClick.isEffectiveDoubleClick()) {
            if (btAdapter.isEnabled()) {
                btAdapter.disable();
            } else {
                btAdapter.enable();
            }
        }
    }
}
