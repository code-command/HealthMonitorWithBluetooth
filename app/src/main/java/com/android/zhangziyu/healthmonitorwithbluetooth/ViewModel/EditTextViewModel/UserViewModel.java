package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.EditTextViewModel;

import android.text.Editable;
import android.text.TextWatcher;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.User;


public class UserViewModel {
    private User user;

    public UserViewModel(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getUserAge() {
        return user.getAge();
    }

    public TextWatcher userNameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            user.setName(editable.toString());
        }
    };

    public TextWatcher userPwdWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            user.setPassword(editable.toString());
        }
    };
}
