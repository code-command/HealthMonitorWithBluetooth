package com.android.zhangziyu.healthmonitorwithbluetooth.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.android.zhangziyu.healthmonitorwithbluetooth.BR;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Adapters.BindingAdapters.ChooseDateRangeBindingAdapter;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Adapters.MethodsAdapters.ListAdapter;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.BaseActionbarconf;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.SystemInfo;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Widget.OptimizationToast;
import com.android.zhangziyu.healthmonitorwithbluetooth.R;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel.ChooseDateRangeButtonViewModel;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel.SearchHistoryButtonViewModel;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ListViewModel.OnGetNewBundle;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ListViewModel.SearchHistoryListItemViewModel;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.ActionBarOperation;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.MethodsViewModel.ImmersionLine;
import com.android.zhangziyu.healthmonitorwithbluetooth.databinding.ActionbarBaseBinding;
import com.android.zhangziyu.healthmonitorwithbluetooth.databinding.ActivityHistoryBinding;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private ActivityHistoryBinding binding;

    private ChooseDateRangeButtonViewModel chooseDateRangeButtonViewModel;
    private SearchHistoryButtonViewModel searchHistoryButtonViewModel;
    private SearchHistoryListItemViewModel searchHistoryListItemViewModel;

    private ArrayList<Date> chooseRange;
    private ArrayList<String> searchResult;
    private SystemInfo systemInfo;
    private ListAdapter searchHistoryListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        new ImmersionLine(this, R.color.actionBarBackground);
        initActionBar();
        initData();
        initBinding();
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
        confs.setMidTitleText(getString(R.string.search_history_title));
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
        systemInfo = SystemInfo.getSystemInfo();
        chooseRange = new ArrayList<>();
        searchResult = new ArrayList<>();
        searchHistoryListAdapter = new ListAdapter<>(this, R.layout.module_searchresult, BR.searchResult, searchResult);
        chooseDateRangeButtonViewModel = new ChooseDateRangeButtonViewModel(chooseRange);
        searchHistoryButtonViewModel = new SearchHistoryButtonViewModel(chooseRange, searchHistoryListAdapter);

        searchHistoryListItemViewModel = new SearchHistoryListItemViewModel(searchResult);
        searchHistoryListItemViewModel.setOnGetNewBundle(new OnGetNewBundle() {
            @Override
            public void setNewBundle(Bundle bundle) {
                Intent intent = new Intent(HistoryActivity.this, HistoryDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                OptimizationToast.showToast(new WeakReference<Context>(getApplicationContext()), bundle.getString("searchHistory"));
            }
        });
    }

    private void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history);
        binding.setSystemInfo(systemInfo);
        binding.setDateRange(chooseRange);
        binding.setChooseDateRangeButtonViewModel(chooseDateRangeButtonViewModel);
        binding.setSearchHistoryButtonViewModel(searchHistoryButtonViewModel);
        binding.setHistoryListAdapter(searchHistoryListAdapter);

        binding.lvSearchHistory.setOnItemClickListener(searchHistoryListItemViewModel);
    }

    @Override
    public void onBackPressed() {
        systemInfoReset();
        super.onBackPressed();
    }

    private void systemInfoReset() {
        systemInfo.setChooseDateRange(false);
        systemInfo.setSearchHistory(false);
    }
}
