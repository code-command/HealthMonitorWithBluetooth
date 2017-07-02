package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;


import com.android.zhangziyu.healthmonitorwithbluetooth.BR;

import java.io.Serializable;

public class User extends BaseObservable implements Serializable {

    private String name;
    private String password;
    private boolean gender;
    private int age;
    private String telephone;

    public User() {
        this.gender = true;
        this.age = 0;
    }

    public User(String name, String password, boolean gender, int age, String telephone) {
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.telephone = telephone;
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }


    @Bindable
    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
    }

    @Bindable
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
        notifyPropertyChanged(BR.telephone);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}
