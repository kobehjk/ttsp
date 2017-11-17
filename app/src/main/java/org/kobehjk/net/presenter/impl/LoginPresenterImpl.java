package org.kobehjk.net.presenter.impl;

import com.squareup.okhttp.Request;

import org.kobehjk.net.entity.OauthUserEntity;
import org.kobehjk.net.view.LoginView;
import org.kobehjk.net.app.App;
import org.kobehjk.net.model.LoginModel;
import org.kobehjk.net.presenter.LoginPresenter;
import org.kobehjk.net.support.okhttp.callback.ResultCallback;

/**
 * Created by kobehjk on 2015/11/17.
 */
public class LoginPresenterImpl implements LoginPresenter {

    private LoginView view;

    private LoginModel model;

    public LoginPresenterImpl(LoginView view) {
        this.view = view;
        model = new LoginModel();
    }

    @Override
    public void login(String phone, String pwd) {
        view.showLoading();
        model.login(phone, pwd, new ResultCallback<OauthUserEntity>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showLoginFaile(e.getMessage());
            }

            @Override
            public void onResponse(OauthUserEntity response) {
                App.getInstance().setOauth(response);
                view.loginSuccess();
            }
        });
    }
}
