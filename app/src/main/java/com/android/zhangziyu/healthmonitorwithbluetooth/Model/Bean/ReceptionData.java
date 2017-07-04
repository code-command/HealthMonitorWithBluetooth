package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;


import com.android.zhangziyu.healthmonitorwithbluetooth.BR;

import java.io.Serializable;
import java.util.Queue;

/**
 * 接收数据格式类
 */
public class ReceptionData extends BaseObservable implements Serializable {
    private final String checkCode;
    private final String endCode;
    private final int checkLength;
    private final int endLength;

    {
        endCode = "0xdd";
        checkCode = "0x22";
        checkLength = checkCode.length();
        endLength = endCode.length();
    }

    @Bindable
    private StringBuilder codeData = new StringBuilder();

    public boolean checkCompleteData(Queue<Byte> resource) {
        if (resource.contains(checkCode) && resource.contains(endCode))
            return true;
        return true;
    }

    @Bindable
    public StringBuilder getCodeData() {
        return codeData;
    }

    public void updateCoreData(String resource) {
        codeData.setLength(0);
        codeData.append(resource);
        notifyPropertyChanged(BR.codeData);
    }

    public void interceptCoreData(Queue<Byte> resource) {
        if (removeCheckCode(resource)) {
            updateCoreData(getCoreData(resource));
        }
    }

    private boolean removeCheckCode(Queue<Byte> resource) {
        int checkIndex = 0;
        byte tmpCheckByte = (byte) checkCode.charAt(checkIndex);
        while (checkIndex<checkLength && !resource.isEmpty()) {
            byte tmpByte = resource.poll();
            if (tmpByte == tmpCheckByte) {
                checkIndex++;
                tmpCheckByte = (byte) checkCode.charAt(checkIndex);
            }
        }
        if (checkIndex == checkLength) return true;
        return false;
    }

    private String getCoreData(Queue<Byte> resource) {
        StringBuilder resultBuilder = new StringBuilder();
        while (!resource.isEmpty() && resultBuilder.length()<endLength) {
            resultBuilder.append(resource.poll());
        }
        while (!resource.isEmpty()) {
            int resultLength = resultBuilder.length();
            if (resultBuilder.substring(resultLength-endLength, resultLength).equals(endCode)) {
                return resultBuilder.substring(0, resultLength-endLength+1);
            }
            resultBuilder.append(resource.poll());
        }
        return null;
    }

    public String toShow() {
        return  getCodeData().toString();
    }
}
