package com.android.zhangziyu.healthmonitorwithbluetooth.ViewModel.ListViewModel;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

public class SearchHistoryListItemViewModel implements AdapterView.OnItemClickListener {

    private ArrayList<String> searchResult;
    private OnGetNewBundle onGetNewBundle;

    public SearchHistoryListItemViewModel(ArrayList<String> searchResult) {
        this.searchResult = searchResult;
    }

    public void setOnGetNewBundle(OnGetNewBundle onGetNewBundle) {
        this.onGetNewBundle = onGetNewBundle;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString("searchHistory", searchResult.get(position-1));
        onGetNewBundle.setNewBundle(bundle);
    }
}
