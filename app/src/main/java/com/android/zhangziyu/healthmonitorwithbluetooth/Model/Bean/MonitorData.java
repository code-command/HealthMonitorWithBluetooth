package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.android.zhangziyu.healthmonitorwithbluetooth.BR;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MonitorData extends BaseObservable implements Parcelable {

    private int userId;
    private Date saveTime;
    private ArrayList<Float> pulseData;
    private ArrayList<Float> ecgData;

    public MonitorData() {
        userId = -1;
        saveTime = Calendar.getInstance().getTime();
        pulseData = new ArrayList<>();
        ecgData = new ArrayList<>();
        for (int i=0; i<50; i++) {
            pulseData.add(i+0.5f);
            ecgData.add(i+0.75f);
        }
    }

    public MonitorData(int userId, Date saveTime, ArrayList<Float> pulseData, ArrayList<Float> ecgData) {
        this.userId = userId;
        this.saveTime = saveTime;
        this.pulseData = pulseData;
        this.ecgData = ecgData;
    }

    protected MonitorData(Parcel in) {
        userId = in.readInt();
    }

    public static final Creator<MonitorData> CREATOR = new Creator<MonitorData>() {
        @Override
        public MonitorData createFromParcel(Parcel in) {
            return new MonitorData(in);
        }

        @Override
        public MonitorData[] newArray(int size) {
            return new MonitorData[size];
        }
    };

    @Bindable
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
        notifyPropertyChanged(BR.userId);
    }

    @Bindable
    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
        notifyPropertyChanged(BR.saveTime);
    }

    @Bindable
    public ArrayList<Float> getPulseData() {
        return pulseData;
    }

    @Bindable
    public String getPulseDataOfJson() {
        Gson gson = new Gson();
        return gson.toJson(pulseData);
    }

    public void setPulseData(ArrayList<Float> pulseData) {
        this.pulseData = pulseData;
        notifyPropertyChanged(BR.pulseData);
    }

    @Bindable
    public ArrayList<Float> getEcgData() {
        return ecgData;
    }

    @Bindable
    public String getEcgDataOfJson() {
        Gson gson = new Gson();
        return gson.toJson(ecgData);
    }

    public void setEcgData(ArrayList<Float> ecgData) {
        this.ecgData = ecgData;
        notifyPropertyChanged(BR.ecgData);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
    }
}
