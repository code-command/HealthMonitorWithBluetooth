package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.BluetoothViewModel;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Adapters.MethodsAdapters.ListAdapter;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.BtDeviceItem;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.SystemInfo;


/**
 * 蓝牙设备各种状态的处理
 */

public class BtDeviceViewModel {
    private ListAdapter deviceListAdapter;
    private SystemInfo systemInfo;

    public BtDeviceViewModel(ListAdapter deviceListAdapter) {
        this.deviceListAdapter = deviceListAdapter;
        this.systemInfo = SystemInfo.getSystemInfo();
    }

    public boolean processIntentChanged(Intent intent) {
        boolean result = false;
        if (processActionChanged(intent))
            result = true;
        return result;
    }

    private boolean processActionChanged(Intent intent) {
        switch (intent.getAction()) {
            case BluetoothDevice.ACTION_FOUND:
                btDeviceFound(intent);
                return true;

            case BluetoothDevice.ACTION_BOND_STATE_CHANGED:
                btDeviceBondChanged(intent);
                return true;

            default:
                break;
        }
        return false;
    }

    private void btDeviceFound(Intent intent) {
        systemInfo.setFound(true);
        btDeviceBondChanged(intent);
    }

    private void btDeviceBondChanged(Intent intent) {
        BluetoothDevice btDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        BtDeviceItem btDeviceItem = new BtDeviceItem(btDevice.getName(), btDevice.getAddress(),
                btDevice.getBondState()==BluetoothDevice.BOND_BONDED, btDevice);
        deviceListAdapter.updateList(btDeviceItem);
    }

}
