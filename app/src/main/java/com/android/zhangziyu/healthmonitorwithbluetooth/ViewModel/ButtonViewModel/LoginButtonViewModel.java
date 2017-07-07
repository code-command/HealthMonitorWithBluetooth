package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel;

import android.view.View;
import android.view.View.OnClickListener;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.User;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.ErrorCode;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel.DBOperationOfLogin;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.EffectiveClick;

import java.util.ArrayList;
import java.util.List;


public class LoginButtonViewModel implements OnClickListener {

    private User user;
    private OnGetClickListener onClickListener;

    public LoginButtonViewModel(User user) {
        this.user = user;
    }

    @Override
    public void onClick(View v) {
        if (EffectiveClick.isEffectiveDoubleClick()) {
            List<ErrorCode> list = new ArrayList<>(1);
            if (DBOperationOfLogin.checkLogin(user, list)) {
                onClickListener.success();
            } else {
                onClickListener.failure(list.get(list.size()-1));
            }
        }
    }

    public void setOnClickListener(OnGetClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
