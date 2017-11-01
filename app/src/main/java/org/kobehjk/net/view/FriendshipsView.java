package org.kobehjk.net.view;

import org.kobehjk.net.entity.SimpleUserEntity;

import java.util.List;

/**
 * Created by sunger on 2015/11/30.
 */
public interface FriendshipsView {
    void showError();

    void showFriends(List<SimpleUserEntity> data);
}
