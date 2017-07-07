package com.android.zhangziyu.healthmonitorwithbluetooth.View.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Adapters.MethodsAdapters.CommentPagerAdapter;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.BaseActionbarconf;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.SystemInfo;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.CmdCode;
import com.android.zhangziyu.healthmonitorwithbluetooth.R;
import com.android.zhangziyu.healthmonitorwithbluetooth.View.Fragment.MoniterFragment;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel.SwitchPagerButtonViewModel;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel.DBOperationOfSearchHistory;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.ActionBarOperation;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.ImmersionLine;
import com.android.zhangziyu.healthmonitorwithbluetooth.databinding.ActionbarBaseBinding;
import com.android.zhangziyu.healthmonitorwithbluetooth.databinding.ActivityHistoryDetailBinding;

import java.util.ArrayList;
import java.util.List;

public class HistoryDetailActivity extends AppCompatActivity {

    ActivityHistoryDetailBinding binding;
    private List<Fragment> fragments;
    ArrayList<Float> pulseDataValues = new ArrayList<>();
    ArrayList<Float> ecgDataValues = new ArrayList<>();
    private String searchHistoryResult;
    CommentPagerAdapter commentPagerAdapter;
    SystemInfo systemInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        new ImmersionLine(this, R.color.actionBarBackground);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history_detail);

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
        confs.setMidTitleText(getString(R.string.search_history_detail_title));
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
        searchHistoryResult = getintent.getStringExtra("searchHistory");
        DBOperationOfSearchHistory.searchHistoryMonitorWithRecordName(searchHistoryResult,
                pulseDataValues, ecgDataValues);
        systemInfo = SystemInfo.getSystemInfo();
        systemInfo.setReceive(true);
        systemInfo.setCmdCode(CmdCode.CMD_HISTORY);
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
        binding.setResultTitle(searchHistoryResult);
        binding.setViewPagerAdapter(commentPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        systemInfoReset();
        super.onBackPressed();
    }

    private void systemInfoReset() {
        systemInfo.setReceive(false);
        systemInfo.setCmdCode(CmdCode.CMD_EMPTY);
    }
}
