package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.zhangziyu.healthmonitorwithbluetooth.BR;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.CmdCode;

import java.io.Serializable;


public class SystemInfo extends BaseObservable{

    private boolean open;
    private boolean search;
    private boolean found;
    private boolean receive;

    private boolean pulseShow;
    private CmdCode cmdCode;

    private boolean chooseDateRange;
    private boolean searchHistory;

    private static SystemInfo systemInfo;

    private SystemInfo() {
        open = false;
        search = false;
        found = false;
        receive = false;
        pulseShow = true;
        cmdCode = CmdCode.CMD_END;

        chooseDateRange = false;
        searchHistory = false;
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

    public void reset() {
        open = false;
        search = false;
        found = false;
        receive = false;
        pulseShow = true;
        cmdCode = CmdCode.CMD_END;

        chooseDateRange = false;
        searchHistory = false;
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
    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
        notifyPropertyChanged(BR.search);
    }

    @Bindable
    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
        notifyPropertyChanged(BR.found);
    }

    @Bindable
    public boolean isReceive() {
        return receive;
    }

    public void setReceive(boolean receive) {
        this.receive = receive;
        notifyPropertyChanged(BR.receive);
    }

    @Bindable
    public boolean isPulseShow() {
        return pulseShow;
    }

    public void setPulseShow(boolean pulseShow) {
        this.pulseShow = pulseShow;
        notifyPropertyChanged(BR.pulseShow);
    }

    @Bindable
    public CmdCode getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(CmdCode cmdCode) {
        this.cmdCode = cmdCode;
        notifyPropertyChanged(BR.cmdCode);
    }

    @Bindable
    public boolean isChooseDateRange() {
        return chooseDateRange;
    }

    public void setChooseDateRange(boolean chooseDateRange) {
        this.chooseDateRange = chooseDateRange;
        notifyPropertyChanged(BR.chooseDateRange);
    }

    @Bindable
    public boolean isSearchHistory() {
        return searchHistory;
    }

    public void setSearchHistory(boolean searchHistory) {
        this.searchHistory = searchHistory;
        notifyPropertyChanged(BR.searchHistory);
    }
}
