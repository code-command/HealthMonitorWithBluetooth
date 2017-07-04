package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean;

import android.bluetooth.BluetoothDevice;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * 蓝牙设备数据结构类
 */
public class BtDeviceItem extends BaseObservable {
    private String deviceName;
    private String deviceAddr;
    private boolean bond;
    private BluetoothDevice btDevice;

    public BtDeviceItem(String deviceName, String deviceAddr, boolean bond, BluetoothDevice btDevice) {
        this.deviceName = deviceName;
        this.deviceAddr = deviceAddr;
        this.bond = bond;
        this.btDevice = btDevice;
    }

    @Bindable
    public String getDeviceName() {
        return deviceName;
    }

    @Bindable
    public String getDeviceAddr() {
        return deviceAddr;
    }

    @Bindable
    public boolean isBond() {
        return bond;
    }

    @Bindable
    public BluetoothDevice getBtDevice() {
        return btDevice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BtDeviceItem that = (BtDeviceItem) o;

        return deviceAddr.equals(that.deviceAddr);

    }
}