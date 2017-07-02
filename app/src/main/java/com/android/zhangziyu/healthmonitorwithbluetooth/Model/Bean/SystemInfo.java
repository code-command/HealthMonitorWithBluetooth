package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.zhangziyu.healthmonitorwithbluetooth.BR;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.CmdCode;


public class SystemInfo extends BaseObservable{

    private boolean open;
    private boolean pluseShow;
    private CmdCode cmdCode;

    private static SystemInfo systemInfo;

    private SystemInfo() {
        open = false;
        pluseShow = true;
        cmdCode = CmdCode.CMD_END;
    }

    public static SystemInfo getSystemInfo() {
        if(systemInfo == null){
            synchronized (SystemInfo.class){
                if(systemInfo == null){
                    systemInfo = new SystemInfo();
                }
            }
        }
        return systemInfo;
    }

    @Bindable
    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
        notifyPropertyChanged(BR.open);
    }

    @Bindable
    public boolean isPluseShow() {
        return pluseShow;
    }

    public void setPluseShow(boolean pluseShow) {
        this.pluseShow = pluseShow;
        notifyPropertyChanged(BR.pluseShow);
    }

    @Bindable
    public CmdCode getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(CmdCode cmdCode) {
        this.cmdCode = cmdCode;
        notifyPropertyChanged(BR.cmdCode);
    }
}
