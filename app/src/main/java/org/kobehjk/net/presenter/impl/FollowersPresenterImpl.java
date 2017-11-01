package org.kobehjk.net.presenter.impl;

import com.squareup.okhttp.Request;

import org.kobehjk.net.entity.FollowEntity;
import org.kobehjk.net.model.FollowersModel;
import org.kobehjk.net.presenter.FollowersPresenter;
import org.kobehjk.net.support.okhttp.callback.ResultCallback;
import org.kobehjk.net.view.FollowersView;

import java.util.List;

/**
 * Created by sunger on 2015/11/30.
 */
public class FollowersPresenterImpl implements FollowersPresenter {
    private FollowersModel model;
    private FollowersView view;

    public FollowersPresenterImpl(FollowersView view) {
        this.view = view;
        model = new FollowersModel();
    }

    @Override
    public void getFollowers(String uid, int page) {
        model.getFollowers(uid, page, new ResultCallback<List<FollowEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showError();
            }

            @Override
            public void onResponse(List<FollowEntity> response) {
                view.showFollows(response);
            }
        });


    }
}
