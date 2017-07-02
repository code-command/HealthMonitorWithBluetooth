package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.android.zhangziyu.healthmonitorwithbluetooth.BR;

/**
 * Created by zhangziyu on 2017/6/29.
 */

public class BaseActionbarconf extends BaseObservable{
    private boolean leftImgBtnShow;
    private boolean midTitleShow;
    private boolean rightBtnShow;

    private String midTitleText;
    private String rightBtnText;

    private View.OnClickListener leftImgBtnClickListener = null;
    private View.OnClickListener rightBtnClickListener = null;

    public BaseActionbarconf() {
    }

    @Bindable
    public boolean isLeftImgBtnShow() {
        return leftImgBtnShow;
    }

    public void setLeftImgBtnShow(boolean leftImgBtnShow) {
        this.leftImgBtnShow = leftImgBtnShow;
        notifyPropertyChanged(BR.leftImgBtnShow);
    }

    @Bindable
    public boolean isMidTitleShow() {
        return midTitleShow;
    }

    public void setMidTitleShow(boolean midTitleShow) {
        this.midTitleShow = midTitleShow;
        notifyPropertyChanged(BR.midTitleShow);
    }

    @Bindable
    public boolean isRightBtnShow() {
        return rightBtnShow;
    }

    public void setRightBtnShow(boolean rightBtnShow) {
        this.rightBtnShow = rightBtnShow;
        notifyPropertyChanged(BR.rightBtnShow);
    }

    @Bindable
    public String getMidTitleText() {
        return midTitleText;
    }

    public void setMidTitleText(String midTitleText) {
        this.midTitleText = midTitleText;
        notifyPropertyChanged(BR.midTitleText);
    }

    @Bindable
    public String getRightBtnText() {
        return rightBtnText;
    }

    public void setRightBtnText(String rightBtnText) {
        this.rightBtnText = rightBtnText;
        notifyPropertyChanged(BR.rightBtnText);
    }

    @Bindable
    public View.OnClickListener getLeftImgBtnClickListener() {
        return leftImgBtnClickListener;
    }

    public void setLeftImgBtnClickListener(View.OnClickListener leftImgBtnClickListener) {
        this.leftImgBtnClickListener = leftImgBtnClickListener;
        notifyPropertyChanged(BR.leftImgBtnClickListener);
    }

    @Bindable
    public View.OnClickListener getRightBtnClickListener() {
        return rightBtnClickListener;
    }

    public void setRightBtnClickListener(View.OnClickListener rightBtnClickListener) {
        this.rightBtnClickListener = rightBtnClickListener;
        notifyPropertyChanged(BR.rightBtnClickListener);
    }
}
