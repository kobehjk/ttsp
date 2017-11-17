package org.kobehjk.net.model;

import org.kobehjk.net.api.ApiClient;
import org.kobehjk.net.api.ParamsMap;
import org.kobehjk.net.app.AppConstants;
import org.kobehjk.net.entity.MediaEntity;
import org.kobehjk.net.support.okhttp.callback.ResultCallback;
import org.kobehjk.net.support.okhttp.request.OkHttpRequest;

import java.util.List;

/**
 * Created by kobehjk on 2015/12/2.
 */
public class RepostsModel {
    public OkHttpRequest getReposts(int uid, int page, ResultCallback<List<MediaEntity>> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.UID_KEY, uid);
        paramsMap.put(AppConstants.ParamKey.PAGE_KEY, page);
        return ApiClient.create(AppConstants.RequestPath.USER_REPOSTS, paramsMap).get(callback);
    }
}
