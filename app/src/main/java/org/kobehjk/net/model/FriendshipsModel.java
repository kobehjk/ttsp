package org.kobehjk.net.model;

import org.kobehjk.net.api.ApiClient;
import org.kobehjk.net.api.ParamsMap;
import org.kobehjk.net.app.AppConstants;
import org.kobehjk.net.entity.SimpleUserEntity;
import org.kobehjk.net.support.okhttp.callback.ResultCallback;
import org.kobehjk.net.support.okhttp.request.OkHttpRequest;

import java.util.List;

/**
 * Created by sunger on 2015/11/30.
 */
public class FriendshipsModel{
    public OkHttpRequest getFriends(String uid, int page, ResultCallback<List<SimpleUserEntity>> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.UID_KEY, uid);
        paramsMap.put(AppConstants.ParamKey.PAGE_KEY, page);
        return ApiClient.create(AppConstants.RequestPath.FRIENDSHIPS, paramsMap).tag("").get(callback);
    }
}
