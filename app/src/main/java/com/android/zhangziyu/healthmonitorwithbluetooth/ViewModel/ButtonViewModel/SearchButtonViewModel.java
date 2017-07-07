package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel;

import android.bluetooth.BluetoothAdapter;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.EffectiveClick;

/**
 * Created by zhangziyu on 2017/7/2.
 */

public class SearchButtonViewModel implements OnClickListener{

    private BluetoothAdapter btAdapter;

    public SearchButtonViewModel(BluetoothAdapter btAdapter) {
        this.btAdapter = btAdapter;
    }


    @Override
    public void onClick(View view) {
        if (EffectiveClick.isEffectiveDoubleClick()) {
            if (!btAdapter.isEnabled())
                return;
            if (btAdapter.isDiscovering()) {
                btAdapter.cancelDiscovery();
            } else {
                btAdapter.startDiscovery();
            }
        }
    }
}
