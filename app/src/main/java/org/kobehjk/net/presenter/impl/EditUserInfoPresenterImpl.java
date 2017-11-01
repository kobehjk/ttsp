package org.kobehjk.net.presenter.impl;

import com.squareup.okhttp.Request;

import org.kobehjk.net.entity.UserEntity;
import org.kobehjk.net.view.EditUserInfoView;
import org.kobehjk.net.app.App;
import org.kobehjk.net.model.EditUserInfoModel;
import org.kobehjk.net.presenter.EditUserInfoPresenter;
import org.kobehjk.net.support.okhttp.callback.ResultCallback;

/**
 * Created by sunger on 2015/11/28.
 */
public class EditUserInfoPresenterImpl implements EditUserInfoPresenter {
    private EditUserInfoModel model;
    private EditUserInfoView view;

    public EditUserInfoPresenterImpl(EditUserInfoView view) {
        this.view = view;
        model = new EditUserInfoModel();
    }

    @Override
    public void update(String screen_name, String gender, String description, String filePath) {
        model.update(screen_name, gender, description, filePath, new ResultCallback<UserEntity>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showError(e.getMessage());
            }

            @Override
            public void onResponse(UserEntity response) {
                App.getInstance().getOauthUserEntity().setUser(response);
                view.updateSuccess();
            }
        });
    }
}
