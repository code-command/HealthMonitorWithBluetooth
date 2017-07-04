package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.BluetoothViewModel;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Adapters.MethodsAdapters.ListAdapter;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.SystemInfo;


/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class BtReceiver extends BroadcastReceiver {
    private BluetoothAdapter btAdapter;
    private BluetoothDevice btDevice;
    private SystemInfo systemInfo;

    private BtAdapterViewModel btAdapterViewModel;
    private BtDeviceViewModel btDeviceViewModel;

    public BtReceiver(BluetoothAdapter btAdapter, ListAdapter deviceListAdapter) {
        this.btAdapter = btAdapter;
        this.systemInfo = SystemInfo.getSystemInfo();

        btAdapterViewModel = new BtAdapterViewModel(btAdapter, deviceListAdapter);
        btDeviceViewModel = new BtDeviceViewModel(deviceListAdapter);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        btAdapterViewModel.processIntentChanged(intent);

        btDeviceViewModel.processIntentChanged(intent);
    }
}
