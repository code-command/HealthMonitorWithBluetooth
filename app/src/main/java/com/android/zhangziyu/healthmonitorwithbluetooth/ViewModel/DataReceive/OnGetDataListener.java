package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DataReceive;


import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.ReceptionData;

/**
 * Created by zhangziyu on 2017/6/11.
 */

public interface OnGetDataListener {
    void GetDataCollection(ReceptionData data);
}
