package com.android.zhangziyu.healthmonitorwithbluetooth.View.Activity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Adapters.MethodsAdapters.CommentPagerAdapter;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.BaseActionbarconf;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.ReceptionData;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.SystemInfo;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.CmdCode;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Widget.OptimizationToast;
import com.android.zhangziyu.healthmonitorwithbluetooth.R;
import com.android.zhangziyu.healthmonitorwithbluetooth.View.Fragment.MoniterFragment;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel.ClearButtonViewModel;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel.OnReceiveButtonListener;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel.ReceiveButtonViewModel;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel.SwitchPagerButtonViewModel;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DataReceive.DataReceiveService;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DataReceive.OnGetDataListener;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel.DBOperationOfSaveReceive;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.ActionBarOperation;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.ImmersionLine;
import com.android.zhangziyu.healthmonitorwithbluetooth.databinding.ActionbarBaseBinding;
import com.android.zhangziyu.healthmonitorwithbluetooth.databinding.ActivityDataAcceptanceBinding;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 监测数据监测界面
 */
public class DataAcceptanceActivity extends AppCompatActivity {

    ActivityDataAcceptanceBinding binding;
    private List<Fragment> fragments;
    ArrayList<Float> pulseDataValues = new ArrayList<>();
    ArrayList<Float> ecgDataValues = new ArrayList<>();
    CommentPagerAdapter commentPagerAdapter;
    SystemInfo systemInfo;
    String deviceAddr;
    ReceptionData receptionData;
    DataReceiveService.SwitchBinder switchBinder;
    DataReceiveService dataReceiveService;
    ReceiveButtonViewModel receiveButtonViewModel;
    ClearButtonViewModel clearButtonViewModel;
    SwitchPagerButtonViewModel switchPagerButtonViewModel;

    private Intent bindIntent;
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            switchBinder = (DataReceiveService.SwitchBinder) service;
            dataReceiveService = switchBinder.getService();
            dataReceiveService.setOnGetDataListener(new OnGetDataListener() {
                @Override
                public void GetDataCollection(ReceptionData data) {
                    pulseDataValues.clear();

                    for (int i=0; i<10; i++) {
                        pulseDataValues.add((float) (Math.random() * 10) + 50f);
                    }

                    ecgDataValues.clear();

                    for (int i=0; i<10; i++) {
                        ecgDataValues.add((float) (Math.random() * 10) + 50f);
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        new ImmersionLine(this, R.color.actionBarBackground);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_acceptance);

        initActionBar();
        initData();
        initViewPager();
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
        confs.setRightBtnShow(true);
        confs.setMidTitleText(getString(R.string.data_receive));
        confs.setRightBtnText(getString(R.string.pulse));
        confs.setLeftImgBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        confs.setRightBtnClickListener(new SwitchPagerButtonViewModel());
        custonActionBar.setConfigs(confs);
        this.getSupportActionBar().setCustomView(custonActionBar.abloginRoot);
    }

    private void initData() {
        Intent getintent = getIntent();
        systemInfo = SystemInfo.getSystemInfo();
        deviceAddr = getintent.getStringExtra("deviceAddr");
        bindIntent = getIntent();
        createBindIntent();

        receiveButtonViewModel = new ReceiveButtonViewModel();
        receiveButtonViewModel.setOnReceiveButtonListener(new OnReceiveButtonListener() {
            @Override
            public void startReceive() {
                DataAcceptanceActivity.this.getApplicationContext().bindService(bindIntent, conn, Context.BIND_AUTO_CREATE);
            }

            @Override
            public void stopReceive() {
                DataAcceptanceActivity.this.getApplicationContext().unbindService(conn);
            }
        });
        clearButtonViewModel = new ClearButtonViewModel();
        switchPagerButtonViewModel = new SwitchPagerButtonViewModel();
    }

    private void initViewPager() {
        MoniterFragment pulseFragment = MoniterFragment.newInstance(pulseDataValues);
        MoniterFragment ecgFragment = MoniterFragment.newInstance(ecgDataValues);
        fragments = new ArrayList<>(2);
        fragments.add(pulseFragment);
        fragments.add(ecgFragment);

        commentPagerAdapter = new CommentPagerAdapter(getSupportFragmentManager(), fragments);
    }

    private void bindingData() {
        binding.setSystemInfo(systemInfo);
        binding.setClearButton(clearButtonViewModel);
        binding.setReceiveButton(receiveButtonViewModel);
        binding.setViewPagerAdapter(commentPagerAdapter);
    }

    private void createBindIntent() {
        //绑定Service
        bindIntent = new Intent(DataAcceptanceActivity.this, DataReceiveService.class);
        bindIntent.putExtra("deviceAddr", deviceAddr);
        String deviceUUID = "00001101-0000-1000-8000-00805F9B34FB";
        bindIntent.putExtra("deviceUUID", deviceUUID);
        bindIntent.putExtra("receiveData", receptionData);
    }

    @Override
    public void onBackPressed() {
        systemInfoReset();
        saveData();
    }

    private void systemInfoReset() {
        systemInfo.setReceive(false);
        systemInfo.setPulseShow(true);
        systemInfo.setCmdCode(CmdCode.CMD_EMPTY);
    }

    private void saveData() {
        AlertDialog msetStringDialog = new AlertDialog.Builder(DataAcceptanceActivity.this)
                .setCancelable(false)
                .setMessage(R.string.save_receive_data)
                .setPositiveButton(R.string.units_options_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (DBOperationOfSaveReceive.saveReceiveData(pulseDataValues)) {
                            OptimizationToast.showToast(new WeakReference<Context>(getApplicationContext()), "Sava Data");
                        } else {
                            OptimizationToast.showToast(new WeakReference<Context>(getApplicationContext()), "Sava Error");
                        }
                        finish();
                    }
                })
                .setNegativeButton(R.string.units_options_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create();
        msetStringDialog.setCanceledOnTouchOutside(true);
        msetStringDialog.show();
    }
}
