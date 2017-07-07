package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.android.zhangziyu.healthmonitorwithbluetooth.BR;

/**
 * 用于控制自定义ActionBar
 */
public class BaseActionbarconf extends BaseObservable{
    private boolean leftImgBtnShow; //是否显示左侧按钮
    private boolean midTitleShow;   //是否显示标题
    private boolean rightBtnShow;   //是否显示右侧按钮

    private String midTitleText;    //标题内容
    private String rightBtnText;    //右侧按钮内容

    private View.OnClickListener leftImgBtnClickListener = null;    //左侧按钮单击响应
    private View.OnClickListener rightBtnClickListener = null;      //右侧按钮单击响应

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
