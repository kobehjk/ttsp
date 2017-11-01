package org.kobehjk.net.presenter.impl;

import com.squareup.okhttp.Request;

import org.kobehjk.net.entity.SimpleUserEntity;
import org.kobehjk.net.model.FriendshipsModel;
import org.kobehjk.net.presenter.FriendshipsPresenter;
import org.kobehjk.net.support.okhttp.callback.ResultCallback;
import org.kobehjk.net.view.FriendshipsView;

import java.util.List;

/**
 * Created by sunger on 2015/11/30.
 */
public class FriendshipsPresenterImpl implements FriendshipsPresenter {
    private FriendshipsModel model;
    private FriendshipsView view;

    public FriendshipsPresenterImpl(FriendshipsView view) {
        this.view = view;
        model = new FriendshipsModel();
    }

    @Override
    public void getFriends(String uid, int page) {
        model.getFriends(uid, page, new ResultCallback<List<SimpleUserEntity>>() {
            @Override
            public void onError(Request request, Exception e) {
                view.showError();
            }

            @Override
            public void onResponse(List<SimpleUserEntity> response) {
                view.showFriends(response);
            }
        });
    }
}
