package com.android.zhangziyu.healthmonitorwithbluetooth.Model.Adapters.BindingAdapters;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.android.zhangziyu.healthmonitorwithbluetooth.Model.Bean.User;

public class RegisterBindingAdapter {

    @BindingAdapter({"saveUser", "errorNameHint"})
    public static void checkName(final TextInputLayout textInputLayout, final User user, final String errorHint) {
        EditText editText = textInputLayout.getEditText();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length()<10) {
                    user.setName(s.toString());
                    textInputLayout.setError(null);
                } else {
                    textInputLayout.setError(errorHint);
                }
            }
        });
    }

    @BindingAdapter({"saveUser", "errorPwdHint"})
    public static void checkPwd(final TextInputLayout textInputLayout, final User user, final String errorHint) {
        EditText editText = textInputLayout.getEditText();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()<=0) {
                    textInputLayout.setError(null);
                    return;
                }
                if (isFormatOfpassword(s.toString())) {
                    textInputLayout.setError(null);
                } else {
                    textInputLayout.setError(errorHint);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setPassword(s.toString());
            }
        });
    }

    private static boolean isFormatOfpassword(String password) {
        String regex = "^[a-zA-Z0-9]+$";
        return password.matches(regex);
    }

    @BindingAdapter({"saveUser", "errorRePwdHint"})
    public static void checkRePwd(final TextInputLayout textInputLayout, final User user, final String errorHint) {
        final EditText editText = textInputLayout.getEditText();
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String str = editText.getText().toString();
                    if (user.getPassword() == null || user.getPassword().isEmpty() || str.isEmpty()) {
                        textInputLayout.setError(null);
                        return;
                    }
                    if (str.equals(user.getPassword())) {
                        textInputLayout.setError(null);
                    } else {
                        textInputLayout.setError(errorHint);
                    }
                } else {
                    textInputLayout.setError(null);
                }
            }
        });
    }

    @BindingAdapter({"saveUser", "errorAgeHint"})
    public static void checkAge(final TextInputLayout textInputLayout, final User user, final String errorHint) {
        EditText editText = textInputLayout.getEditText();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.length() <= 0) {
                    textInputLayout.setError(null);
                } else {
                    int age = Integer.parseInt(s.toString());
                    if (age < 0 || age > 200) {
                        textInputLayout.setError(errorHint);
                    } else {
                        textInputLayout.setError(null);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String strAge = s.toString();
                user.setAge(strAge.isEmpty() ? 0 : Integer.parseInt(strAge));
            }
        });
    }

    @BindingAdapter({"saveUser", "errorTelHint"})
    public static void checkTelephone(final TextInputLayout textInputLayout, final User user, String errorHint) {
        EditText editText = textInputLayout.getEditText();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setTelephone(s.toString());
            }
        });
    }
}
