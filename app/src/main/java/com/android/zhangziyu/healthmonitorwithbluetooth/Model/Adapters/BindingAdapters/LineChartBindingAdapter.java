package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Adapters.BindingAdapters;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.widget.Toast;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums.CmdCode;
import com.android.zhangziyu.healthmonitorwithbluetooth.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.List;

/**
 * Created by zhangziyu on 2017/7/2.
 */

public class LineChartBindingAdapter {

    @BindingAdapter({"isReceive", "cmdCode","dataValues"})
    public static void setLineChart(LineChart lineChart, boolean receive, CmdCode cmdCode,
                                    List dataValues) {
//        showToast(lineChart, open, cmdCode);
        if (!receive && cmdCode==CmdCode.CMD_END) {
            initLineChart(lineChart);
            return;
        }
        if (!receive && cmdCode==CmdCode.CMD_EMPTY) {
            closeChart(lineChart);
            return;
        }
        if (receive && cmdCode==CmdCode.CMD_ADD) {
            addEntry(lineChart, dataValues);
            return;
        }
        if (receive && (cmdCode==CmdCode.CMD_CLEAR || cmdCode==CmdCode.CMD_EMPTY || cmdCode==CmdCode.CMD_END)) {
            clearEntry(lineChart);
            return;
        }
    }

    private static void initLineChart(LineChart lineChart) {
        lineChart.getDescription().setEnabled(false);

        lineChart.setNoDataText("未开始接收");// 折线图没有数据时的描述

        lineChart.setDrawGridBackground(true);
        lineChart.setGridBackgroundColor(0xe0000000);//设置背景禁止位于颜色

        lineChart.setPinchZoom(false);// 如果设置为true,x和y轴可以同时用两手指伸缩,如果false,y轴可以分别按比例缩小的

        lineChart.setTouchEnabled(true); // 设置是否可以触摸
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(true);// 是否可以缩放

//        lineChart.setEnabled(false);
        lineChart.getLegend().setEnabled(false);

        initAxis(lineChart);

        lineChart.invalidate();
    }

    private static void initAxis(LineChart lineChart) {
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);

        YAxis rightYAxis = lineChart.getAxisRight();
        rightYAxis.setEnabled(false);
    }

    private static void addEntry(LineChart lineChart, List dataValues) {
        LineData data = lineChart.getData();
        if (data == null) {
            lineChart.setData(new LineData());
            data = lineChart.getData();
        }

        ILineDataSet set = data.getDataSetByIndex(0);
        if (set == null) {
            set = createSet();
            data.addDataSet(set);
        }

        int listLength = dataValues.size();
        for (int i=0; i<listLength; i++) {
            data.addEntry(new Entry(data.getDataSetByIndex(0).getEntryCount(), (float)dataValues.get(i)), 0);
            data.notifyDataChanged();
        }


        lineChart.notifyDataSetChanged();
        lineChart.setVisibleXRangeMinimum(10);
        lineChart.setVisibleXRangeMaximum(60);

//        if (data.getEntryCount()<60 || data.getEntryCount()%50 == 0) {
        lineChart.moveViewTo(data.getEntryCount() - 7, 50f, YAxis.AxisDependency.LEFT);
//        }
    }

    private static void clearEntry(LineChart lineChart) {
        lineChart.setData(new LineData());
        lineChart.invalidate();
    }

    private static void closeChart(LineChart lineChart) {
        lineChart.clear();
    }

    private static LineDataSet createSet() {
        LineDataSet set = new LineDataSet(null, null);
        set.setLineWidth(2f);

        set.setDrawCircles(false);
        set.setColor(Color.WHITE);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        return set;
    }
}
