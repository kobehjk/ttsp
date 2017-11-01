package org.kobehjk.net.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.view.View;

import com.dd.processbutton.iml.ActionProcessButton;

import org.kobehjk.net.presenter.LoginPresenter;
import org.kobehjk.net.presenter.impl.LoginPresenterImpl;
import org.kobehjk.net.ui.widget.TextWatcher;
import org.kobehjk.net.view.LoginView;
import org.kobehjk.net.utils.FormValidation;
import org.kobehjk.net.utils.KeyboardUtils;
import org.kobehjk.net.utils.WidgetUtils;

import kobehjk.org.demo.R;

/**
 * Created by sunger on 2015/11/17.
 */
public class LoginActivity extends BaseCompatActivity implements LoginView, View.OnClickListener {
    private LoginPresenter mPresenter;
    private ActionProcessButton mButtonSign;
    private TextInputLayout mTextInputLayoutPhone;
    private TextInputLayout mTextInputLayoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpCommonBackTooblBar(R.id.tool_bar, R.string.title_login);
        mButtonSign = (ActionProcessButton) findViewById(R.id.button_sign);
        mButtonSign.setOnClickListener(this);
        mTextInputLayoutPhone = findView(R.id.textInputLayout_phone);
        mTextInputLayoutPhone.getEditText().addTextChangedListener(new TextWatcher(mTextInputLayoutPhone) {
            @Override
            public void afterTextChanged(Editable s) {
                if (getEditString().length() > 10)
                    if (FormValidation.isMobile(getEditString())) {
                        mTextInputLayoutPhone.setErrorEnabled(false);
                    } else {
                        setEditTextError(mTextInputLayoutPhone, R.string.msg_error_phone);
                    }
            }
        });
        mTextInputLayoutPassword = findView(R.id.textInputLayout_password);
        mTextInputLayoutPassword.getEditText().addTextChangedListener(new TextWatcher(mTextInputLayoutPassword) {
            @Override
            public void afterTextChanged(Editable s) {
                if (getEditString().length() > 5)
                    if (FormValidation.isSimplePassword(getEditString())) {
                        mTextInputLayoutPassword.setErrorEnabled(false);
                    } else {
                        setEditTextError(mTextInputLayoutPassword, R.string.msg_error_password);
                    }
            }
        });
        findViewById(R.id.textView_create_account).setOnClickListener(this);
        findView(R.id.textView_reset_password).setOnClickListener(this);
        mPresenter = new LoginPresenterImpl(this);
    }


    private void setEditTextError(TextInputLayout layout, int msgId) {
        layout.setErrorEnabled(true);
        layout.setError(getString(msgId));
    }

    public boolean valid(String phone, String password) {
        if (!FormValidation.isMobile(phone)) {
            WidgetUtils.requestFocus(mTextInputLayoutPhone.getEditText());
            setEditTextError(mTextInputLayoutPhone, R.string.msg_error_phone);
            return true;
        }
        if (!FormValidation.isSimplePassword(password)) {
            WidgetUtils.requestFocus(mTextInputLayoutPassword.getEditText());
            setEditTextError(mTextInputLayoutPassword, R.string.msg_error_password);
            return true;
        }
        return false;
    }


    public void login() {
        KeyboardUtils.hide(this);
        String phone = mTextInputLayoutPhone.getEditText().getText().toString();
        String password = mTextInputLayoutPassword.getEditText().getText().toString();
        if (valid(phone, password))
            return;
        mPresenter.login(phone, password);
        mButtonSign.setMode(ActionProcessButton.Mode.ENDLESS);
    }


    @Override
    public void showLoading() {
        mButtonSign.setProgress(50);

    }

    @Override
    public void showLoginFaile(String msg) {
        mButtonSign.setProgress(-1);
        showMsgInBottom(msg);
    }

    @Override
    public void loginSuccess() {
        mButtonSign.setProgress(100);
        this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView_create_account:
                startActivity(new Intent(this, SignUpActivity.class));
                finish();
                break;
            case R.id.button_sign:
                login();
                break;
            case R.id.textView_reset_password:
                startActivity(new Intent(this, ResetPasswordActivity.class));
                finish();
                break;
        }
    }
}
