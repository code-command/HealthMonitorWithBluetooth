package com.android.zhangziyu.healthmonitorwithbluetooth.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.BaseActionbarconf;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.User;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.ErrorCode;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Widget.OptimizationToast;
import com.android.zhangziyu.healthmonitorwithbluetooth.R;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel.LoginButtonViewModel;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel.OnGetClickListener;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.EditTextViewModel.UserViewModel;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.ActionBarOperation;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.ExitOperation;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.HideSoftKeyBoard;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.ImmersionLine;
import com.android.zhangziyu.healthmonitorwithbluetooth.databinding.ActionbarBaseBinding;
import com.android.zhangziyu.healthmonitorwithbluetooth.databinding.ActivityLoginBinding;

import java.lang.ref.WeakReference;

public class LoginActivity extends AppCompatActivity {

    private User user;
    private UserViewModel userViewModel;
    private LoginButtonViewModel loginButtonViewModel;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        new ImmersionLine(this, R.color.actionBarBackground);
        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);

        initActionBar();
        initData();
        bindingData();
    }

    private void initActionBar() {
        HideSoftKeyBoard.setupUI(binding.loginRlRoot, this);
        initCustomActionBar();
    }

    private void initCustomActionBar() {
        ActionBarOperation.setSystemActionBar(getApplicationContext(), this.getSupportActionBar(), R.color.actionBarBackground);
        ViewGroup root = (ViewGroup) findViewById(R.id.ablogin_root);
        ActionbarBaseBinding custonActionBar = DataBindingUtil.inflate(getLayoutInflater(), R.layout.actionbar_base, root, false);

        BaseActionbarconf confs = new BaseActionbarconf();
        confs.setLeftImgBtnShow(false);
        confs.setMidTitleShow(true);
        confs.setRightBtnShow(true);
        confs.setMidTitleText(getString(R.string.login_login));
        confs.setRightBtnText(getString(R.string.login_register));
        confs.setRightBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        custonActionBar.setConfigs(confs);
        this.getSupportActionBar().setCustomView(custonActionBar.abloginRoot);
    }

    private void initData() {
        user = new User();
        userViewModel = new UserViewModel(user);
        loginButtonViewModel = new LoginButtonViewModel(user);
        loginButtonViewModel.setOnClickListener(new OnGetClickListener() {
            @Override
            public void success() {
                user.setAge(24);
                user.setTelephone("15255150806");

                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);finish();
            }

            @Override
            public void failure(ErrorCode errorCode) {
                OptimizationToast.showToast(new WeakReference<Context>(getApplication()), getString(errorCode.getIndex()));
            }
        });
    }

    private void bindingData() {
        binding.setUserViewModel(userViewModel);
        binding.setLoginViewModel(loginButtonViewModel);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            ExitOperation.exitBy2Click(getApplicationContext());
        }
        return false;
    }
}
