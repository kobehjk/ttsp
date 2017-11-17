package org.kobehjk.net.presenter.impl;

import com.google.gson.JsonElement;
import com.squareup.okhttp.Request;

import org.kobehjk.net.entity.OauthUserEntity;
import org.kobehjk.net.model.ResetPasswordModel;
import org.kobehjk.net.view.ResetPasswordView;
import org.kobehjk.net.app.App;
import org.kobehjk.net.presenter.ResetPasswordPresenter;
import org.kobehjk.net.support.okhttp.callback.ResultCallback;

/**
 * Created by kobehjk on 2015/11/19.
 */
public class ResetPasswordPresenterImpl implements ResetPasswordPresenter {
    private ResetPasswordView view;
    private ResetPasswordModel model;

    public ResetPasswordPresenterImpl(ResetPasswordView view) {
        this.view = view;
        this.model = new ResetPasswordModel();
    }

    @Override
    public void resetPassword(String verify_code, String phone, String pwd) {
        model.resetPassword(phone, pwd, verify_code, new ResultCallback<OauthUserEntity>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showResetError(e.getMessage());

            }

            @Override
            public void onResponse(OauthUserEntity response) {
                App.getInstance().setOauth(response);
                view.showSuccess();
            }
        });
    }

    @Override
    public void getVerifySMS(String phone, String pwd) {
        model.getVerifySMS(phone, pwd, new ResultCallback<JsonElement>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showMsg(e.getMessage());
            }

            @Override
            public void onResponse(JsonElement response) {
                view.showSmsSuccess();
            }
        });
    }
}
