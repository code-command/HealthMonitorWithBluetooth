package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;
import android.view.View.OnClickListener;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.SystemInfo;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.User;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.ErrorCode;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Widget.OptimizationToast;
import com.android.zhangziyu.healthmonitorwithbluetooth.R;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel.DBOperationOfRegister;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.EffectiveClick;
import com.squareup.timessquare.CalendarPickerView;

import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.List;

public class ReviseUserButtonViewModel implements OnClickListener {

    private User oldUser;
    private User newUser;
    private OnGetClickListener onGetClickListener;

    public ReviseUserButtonViewModel(User oldUser, User newUser) {
        this.oldUser = oldUser;
        this.newUser = newUser;
    }

    public void setOnGetClickListener(OnGetClickListener onGetClickListener) {
        this.onGetClickListener = onGetClickListener;
    }

    @Override
    public void onClick(View v) {
        if (EffectiveClick.isEffectiveDoubleClick()) {
            initDialog(v);
        }

    }

    private void initDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setMessage(R.string.revise_user_hint_confirm);
        setPositiveButton(builder, v);
        setNegativeButton(builder)
                .create()
                .show();
    }

    private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder, final View view) {
        return builder.setPositiveButton(R.string.units_options_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (DBOperationOfRegister.reviseUserInfo(oldUser, newUser)) {
                    SystemInfo.getSystemInfo().getUser().setUser(newUser);
                    onGetClickListener.success();
                } else {
                    onGetClickListener.failure(ErrorCode.REVISEUSER_SAVEFAILURE);
                }
            }
        });
    }

    private AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder) {
        return builder.setNegativeButton(R.string.units_options_cancel, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

    }
}
