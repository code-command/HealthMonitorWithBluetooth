package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ButtonViewModel;

import android.view.View;
import android.view.View.OnClickListener;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Adapters.MethodsAdapters.ListAdapter;
import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.SystemInfo;
import com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.DatabaseViewModel.DBOperationOfSearchHistory;

import java.util.ArrayList;
import java.util.Date;

public class SearchHistoryButtonViewModel implements OnClickListener{

    private ArrayList<String> searchResult = new ArrayList<>();
    private ArrayList<Date> chooseRange;
    private SystemInfo systemInfo;
    private ListAdapter searchHistoryListAdapter;

    public SearchHistoryButtonViewModel(ArrayList<Date> chooseRange, ListAdapter searchHistoryListAdapter) {
        this.chooseRange = chooseRange;
        this.searchHistoryListAdapter = searchHistoryListAdapter;
        systemInfo = SystemInfo.getSystemInfo();
    }

    @Override
    public void onClick(View v) {
        systemInfo.setSearchHistory(DBOperationOfSearchHistory.searchHistoryRecordNameWithDateRange(chooseRange,
                searchResult));
        searchHistoryListAdapter.renewalAllList(searchResult);
    }
}
