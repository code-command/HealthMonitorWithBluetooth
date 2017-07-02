package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel;


import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.ErrorCode;

public interface OnGetClickListener {
    public void success();
    public void failure(ErrorCode errorCode);
}
