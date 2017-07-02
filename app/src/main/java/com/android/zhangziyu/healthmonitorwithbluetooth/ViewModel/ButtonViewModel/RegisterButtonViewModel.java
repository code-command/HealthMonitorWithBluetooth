package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel;

import android.view.View;
import android.view.View.OnClickListener;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.User;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.ErrorCode;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel.DBOperationOfRegister;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangziyu on 2017/6/29.
 */

public class RegisterButtonViewModel implements OnClickListener {

    private User user;
    private OnGetClickListener onClickListener;

    public RegisterButtonViewModel(User user) {
        this.user = user;
    }

    public void setOnClickListener(OnGetClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void onClick(View v) {
        List<ErrorCode> list = new ArrayList<>(1);
        if (DBOperationOfRegister.registerUser(user, list)) {
            onClickListener.success();
        } else {
            onClickListener.failure(list.get(list.size()-1));
        }
    }
}
