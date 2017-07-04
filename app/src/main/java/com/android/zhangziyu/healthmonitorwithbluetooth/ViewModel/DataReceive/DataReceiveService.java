package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DataReceive;

import android.app.IntentService;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.ReceptionData;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.SystemInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

public class DataReceiveService extends IntentService {
    private BluetoothAdapter btAdapter;
    private SystemInfo systemInfo;
    private BluetoothDevice btDevice;
    private BluetoothSocket btSocket;
    private SwitchBinder switchBinder = new SwitchBinder();
    private byte[] receiveBuffer = new byte[32];
    private Queue<Byte> receiveQueue = new LinkedList<>();
    private ReceptionData receptionData;

    private OnGetDataListener onGetDataListener;            //自定义回调接口

    public class SwitchBinder extends Binder {
        public DataReceiveService getService() {
            return DataReceiveService.this;
        }
    }

    /**************************注册回调接口***************************/
    public void setOnGetDataListener(OnGetDataListener onGetDataListener) {
        this.onGetDataListener = onGetDataListener;
    }

    public DataReceiveService() {
        super("DataReceiveService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        systemInfo = SystemInfo.getSystemInfo();
        receptionData = (ReceptionData) intent.getSerializableExtra("receiveData");
        String deviceAddr = intent.getStringExtra("deviceAddr");
        btDevice = btAdapter.getRemoteDevice(deviceAddr);
        return switchBinder;
    }

    @Override
    public void onCreate() {
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        receptionData.updateCoreData("Test");
        final UUID deviceUUID = UUID.fromString(intent.getStringExtra("deviceUUID"));
        try {
            btSocket = btDevice.createInsecureRfcommSocketToServiceRecord(deviceUUID);
            btSocket.connect();
            InputStream receiveStream = btSocket.getInputStream();
            while (systemInfo.isReceive()) {
                int read = receiveStream.read(receiveBuffer);
                if (read > 0) {
                    for (byte b : receiveBuffer) {
                        receiveQueue.offer(b);
                    }
                    if (receptionData.checkCompleteData(receiveQueue)) {
                        receptionData.interceptCoreData(receiveQueue);
                        onGetDataListener.GetDataCollection(receptionData);
                        onGetDataListener.GetDataCollection(receptionData);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        try {
            if (btSocket != null) {
                btSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.onUnbind(intent);
    }
}
