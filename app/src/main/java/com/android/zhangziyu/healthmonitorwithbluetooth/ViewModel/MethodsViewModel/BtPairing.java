package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel;

import android.bluetooth.BluetoothDevice;

import java.lang.reflect.Method;

public class BtPairing {
    /**************************与蓝牙设备配对**************************/
    public static boolean creatBond(Class<? extends BluetoothDevice> btClass, BluetoothDevice btDevice)throws Exception
    {
        Method createbondMethod = btClass.getMethod("createBond");
        return (Boolean) createbondMethod.invoke(btDevice);
    }

    /**************************与设备解除配对**************************/
    public static boolean removeBond(Class<? extends BluetoothDevice> btClass, BluetoothDevice btDevice)throws Exception
    {
        Method removeBondMethod = btClass.getMethod("removeBond");
        return (Boolean) removeBondMethod.invoke(btDevice);
    }

}
