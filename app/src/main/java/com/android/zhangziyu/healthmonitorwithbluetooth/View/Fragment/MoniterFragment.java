package com.android.zhangziyu.healthmonitorwithbluetooth.View.Fragment;


import android.support.v4.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.SystemInfo;
import com.android.zhangziyu.healthmonitorwithbluetooth.R;
import com.android.zhangziyu.healthmonitorwithbluetooth.databinding.FragmentMoniterBinding;

import java.util.ArrayList;

public class MoniterFragment extends Fragment {

    public static final String DATAVALUES = "datavaluse";

    private FragmentMoniterBinding binding;
    private SystemInfo systemInfo;
    private ArrayList dataValuse;

    public static MoniterFragment newInstance(ArrayList dataValuse) {
        MoniterFragment pluseFragment = new MoniterFragment();

        Bundle bundle = new Bundle();
        bundle.putCharSequenceArrayList(DATAVALUES, dataValuse);

        pluseFragment.setArguments(bundle);

        return pluseFragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        systemInfo = SystemInfo.getSystemInfo();
        Bundle bundle = getArguments();
        if (bundle != null) {
            dataValuse = bundle.getCharSequenceArrayList(DATAVALUES);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_moniter, null);

        bindingData(view);

        return view;
    }

    private void bindingData(View view) {
        binding = DataBindingUtil.bind(view);
        binding.setSystemInfo(systemInfo);
        binding.setDataValues(dataValuse);
    }
}
