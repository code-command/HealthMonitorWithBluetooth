package com.android.zhangziyu.healthmonitorwithbluetooth.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.BaseActionbarconf;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.SystemInfo;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.User;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.ErrorCode;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Widget.OptimizationToast;
import com.android.zhangziyu.healthmonitorwithbluetooth.R;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel.OnGetClickListener;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel.ReceiveButtonViewModel;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel.ReviseUserButtonViewModel;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.ActionBarOperation;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.HideSoftKeyBoard;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.ImmersionLine;
import com.android.zhangziyu.healthmonitorwithbluetooth.databinding.ActionbarBaseBinding;
import com.android.zhangziyu.healthmonitorwithbluetooth.databinding.ActivitySetBinding;

import java.lang.ref.WeakReference;

public class SetActivity extends AppCompatActivity {

    private User user;
    private User tmpUser;
    private ReviseUserButtonViewModel reviseUserButtonViewModel;
    ActivitySetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        new ImmersionLine(this, R.color.actionBarBackground);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_set);

        initActionBar();
        initData();
        bindingData();
    }

    private void initActionBar() {
        HideSoftKeyBoard.setupUI(binding.loginRlRegisterRoot, this);
        initCustomActionBar();
    }

    private void initCustomActionBar() {
        ActionBarOperation.setSystemActionBar(getApplicationContext(), this.getSupportActionBar(), R.color.actionBarBackground);
        ViewGroup root = (ViewGroup) findViewById(R.id.ablogin_root);
        ActionbarBaseBinding custonActionBar = DataBindingUtil.inflate(getLayoutInflater(), R.layout.actionbar_base, root, false);

        BaseActionbarconf confs = new BaseActionbarconf();
        confs.setLeftImgBtnShow(true);
        confs.setMidTitleShow(true);
        confs.setRightBtnShow(false);
        confs.setMidTitleText(getString(R.string.revise_user_title));
        confs.setLeftImgBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        custonActionBar.setConfigs(confs);
        this.getSupportActionBar().setCustomView(custonActionBar.abloginRoot);
    }

    private void initData() {
        user = SystemInfo.getSystemInfo().getUser();
        tmpUser = new User(user);
        reviseUserButtonViewModel = new ReviseUserButtonViewModel(user, tmpUser);
        reviseUserButtonViewModel.setOnGetClickListener(new OnGetClickListener() {
            @Override
            public void success() {
                OptimizationToast.showToast(new WeakReference<Context>(getApplication()), getString(R.string.revise_user_hint_success));
            }

            @Override
            public void failure(ErrorCode errorCode) {
                OptimizationToast.showToast(new WeakReference<Context>(getApplication()), getString(errorCode.getIndex()));
            }
        });
    }

    private void bindingData() {
        binding.setUser(tmpUser);
        binding.setReviseUserViewModel(reviseUserButtonViewModel);
    }
}
