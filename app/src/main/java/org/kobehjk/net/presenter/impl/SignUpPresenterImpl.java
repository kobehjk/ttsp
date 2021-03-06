package org.kobehjk.net.presenter.impl;

import com.google.gson.JsonElement;
import com.squareup.okhttp.Request;

import org.kobehjk.net.app.App;
import org.kobehjk.net.entity.OauthUserEntity;
import org.kobehjk.net.model.LoginModel;
import org.kobehjk.net.model.SignUpModel;
import org.kobehjk.net.presenter.SignUpPresenter;
import org.kobehjk.net.view.SignUpView;
import org.kobehjk.net.support.okhttp.callback.ResultCallback;

/**
 * Created by sunger on 2015/11/18.
 */
public class SignUpPresenterImpl implements SignUpPresenter {

    private SignUpView view;
    private SignUpModel model;

    public SignUpPresenterImpl(SignUpView view) {
        this.view = view;
        model = new SignUpModel();
    }

    @Override
    public void signUp(String verify_code, final String phone, final String pwd) {
        model.signUp(phone, pwd, verify_code, new ResultCallback<JsonElement>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showSignUpError(e.getMessage());
            }

            @Override
            public void onResponse(JsonElement response) {
                view.signUpSuccess();
                autoLogin(phone, pwd);
            }
        });
    }

    @Override
    public void getVerifySMS(String phone, String pwd) {

        model.getVerifySMS(phone, pwd, new ResultCallback<JsonElement>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showVerifyError(e.getMessage());
            }

            @Override
            public void onResponse(JsonElement response) {
                if (response.getAsBoolean()) {
                    view.showVerifySuccerss();


                }
            }
        });
    }

    public void autoLogin(String phone, String pwd) {
        new LoginModel().login(phone, pwd, new ResultCallback<OauthUserEntity>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showMsg("自动登录失败");
            }

            @Override
            public void onResponse(OauthUserEntity response) {
                App.getInstance().setOauth(response);
                view.showMsg("自动登录成功");
            }
        });

    }
}
