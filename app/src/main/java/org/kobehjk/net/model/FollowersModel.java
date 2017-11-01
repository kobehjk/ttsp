package org.kobehjk.net.model;

import org.kobehjk.net.api.ApiClient;
import org.kobehjk.net.api.HeaderMap;
import org.kobehjk.net.api.ParamsMap;
import org.kobehjk.net.app.AppConstants;
import org.kobehjk.net.entity.FollowEntity;
import org.kobehjk.net.support.okhttp.callback.ResultCallback;
import org.kobehjk.net.support.okhttp.request.OkHttpRequest;

import java.util.List;

/**
 * Created by sunger on 2015/11/30.
 */
public class FollowersModel {
    public OkHttpRequest getFollowers(String uid, int page, ResultCallback<List<FollowEntity>> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.UID_KEY, uid);
        paramsMap.put(AppConstants.ParamKey.PAGE_KEY, page);
        paramsMap.put(AppConstants.ParamKey.WITH_CAPTION_KEY, AppConstants.ParamDefaultValue.WITH_CAPTION);
        HeaderMap headerMap = new HeaderMap();
        return ApiClient.create(AppConstants.RequestPath.FOLLOWERS).headers(headerMap).params(paramsMap).get(callback);
    }
}
