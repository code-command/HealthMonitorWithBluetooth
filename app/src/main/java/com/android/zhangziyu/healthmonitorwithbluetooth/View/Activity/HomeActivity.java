package com.android.zhangziyu.healthmonitorwithbluetooth.View.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.BaseActionbarconf;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.User;
import com.android.zhangziyu.healthmonitorwithbluetooth.R;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel.HomeButtonViewModel;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel.OnGetNewActivity;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.ActionBarOperation;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.ImmersionLine;
import com.android.zhangziyu.healthmonitorwithbluetooth.databinding.ActionbarBaseBinding;
import com.android.zhangziyu.healthmonitorwithbluetooth.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private User user;
    private HomeButtonViewModel homeButtonViewModel;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        new ImmersionLine(this, R.color.actionBarBackground);
        binding = DataBindingUtil.setContentView(HomeActivity.this, R.layout.activity_home);
        initActionBar();
        initData();
        bindingData();
    }

    private void initActionBar() {
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
        confs.setMidTitleText(getString(R.string.app_name));
        confs.setLeftImgBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        custonActionBar.setConfigs(confs);
        this.getSupportActionBar().setCustomView(custonActionBar.abloginRoot);
    }

    private void logout() {
        AlertDialog msetStringDialog = new AlertDialog.Builder(HomeActivity.this)
                .setCancelable(false)
                .setMessage(R.string.navigation_optname_logout)
                .setPositiveButton(R.string.units_options_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        OnSuperBackPressed();
                    }
                })
                .setNegativeButton(R.string.units_options_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .create();
        msetStringDialog.setCanceledOnTouchOutside(true);
        msetStringDialog.show();
    }

    private void initData() {
        user = (User) getIntent().getSerializableExtra("User");
        homeButtonViewModel = new HomeButtonViewModel();
        homeButtonViewModel.setonGetNewActivity(new OnGetNewActivity() {
            @Override
            public void setNewActivity(Class<?> cls) {
                Intent intent = new Intent(HomeActivity.this, cls);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });
    }

    private void bindingData() {
        binding.setHomeButtonViewModel(homeButtonViewModel);
    }

    @Override
    public void onBackPressed() {
        logout();
    }

    private void OnSuperBackPressed() {
        super.onBackPressed();
        finish();
    }
}
