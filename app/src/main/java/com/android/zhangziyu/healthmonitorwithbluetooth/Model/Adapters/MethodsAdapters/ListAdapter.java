package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Adapters.MethodsAdapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class ListAdapter<T> extends BaseAdapter {
    private LayoutInflater inflater;
    private int layoutId;
    private int variableId;
    private List<T> list;

    public ListAdapter(Context context, int layoutId, int variableId, List<T> list) {
        this.inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.variableId = variableId;
        this.list = list;
    }

    public void clearList() {
        list.clear();
        notifyDataSetChanged();
    }

    public void updateList(T item) {
        if (list.contains(item)) {
            int postion = list.indexOf(item);
            list.remove(postion);
            list.add(postion, item);
        } else {
            list.add(item);
        }
        notifyDataSetChanged();
    }

    public void renewalAllList(List<T> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }
        binding.setVariable(variableId, list.get(position));
        return binding.getRoot();
    }
}