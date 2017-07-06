package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Enums;


import com.android.zhangziyu.healthmonitorwithbluetooth.R;

public enum ErrorCode {
    EMPTY_USERNAME(R.string.login_emptyNameHint),
    EMPTY_USERPWD(R.string.login_emptyPwdHint),
    USER_LOGINFAILURE(R.string.login_failuerLogin),

    USERNAME_NOTUNIQUE(R.string.register_username_notUnique),
    USERPWD_ERRORFORMAT(R.string.register_userpwd_errorFormat),
    USERPWD_DISAFFINITY(R.string.register_userpwd_disaffinity),
    USERAGE_OUTRANGE(R.string.register_userage_outRange),
    REGISTER_SAVEFAILURE(R.string.register_savefailure),

    REVISEUSER_SAVEFAILURE(R.string.revise_user_hint_failure),


    ENDMARK(Integer.MAX_VALUE);
    private int index;
    ErrorCode(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
