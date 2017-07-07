package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel;

import android.view.View;
import android.view.View.OnClickListener;

import com.android.zhangziyu.healthmonitorwithbluetooth.R;
import com.android.zhangziyu.healthmonitorwithbluetooth.View.Activity.HistoryActivity;
import com.android.zhangziyu.healthmonitorwithbluetooth.View.Activity.MoniterActivity;
import com.android.zhangziyu.healthmonitorwithbluetooth.View.Activity.SetActivity;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.EffectiveClick;

public class HomeButtonViewModel implements OnClickListener {

    private OnGetNewActivity onGetNewActivity;

    public void setonGetNewActivity(OnGetNewActivity onGetNewActivity) {
        this.onGetNewActivity = onGetNewActivity;
    }

    @Override
    public void onClick(View view) {
        if (EffectiveClick.isEffectiveDoubleClick()) {
            onGetNewActivity.setNewActivity(getNewActivity(view.getId()));
        }
    }

    private Class<?> getNewActivity(int viewId) {
        switch (viewId) {
            case R.id.home_btn_monitor:
                return MoniterActivity.class;

            case R.id.home_btn_history:
                return HistoryActivity.class;

            case R.id.home_btn_set:
                return SetActivity.class;

            default:
                return null;
        }
    }
}
