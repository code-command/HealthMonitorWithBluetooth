package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;


import com.android.zhangziyu.healthmonitorwithbluetooth.BR;

import java.io.Serializable;

public class User extends BaseObservable implements Parcelable {

    private int id;
    private String name;
    private String password;
    private boolean gender;
    private int age;
    private String telephone;

    public User() {
        this.gender = true;
        this.age = 0;
    }

    public User (User user) {
        this.name = user.getName();
        this.password = user.getPassword();
        this.gender = user.getGender();
        this.age = user.getAge();
        this.telephone = user.getTelephone();
    }

    public User(int id, String name, String password, boolean gender, int age, String telephone) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.telephone = telephone;
    }

    public void setUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.gender = user.getGender();
        this.age = user.getAge();
        this.telephone = user.getTelephone();
    }

    protected User(Parcel in) {
        name = in.readString();
        password = in.readString();
        gender = in.readByte() != 0;
        age = in.readInt();
        telephone = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(password);
        dest.writeByte((byte) (gender ? 1 : 0));
        dest.writeInt(age);
        dest.writeString(telephone);
    }


}
