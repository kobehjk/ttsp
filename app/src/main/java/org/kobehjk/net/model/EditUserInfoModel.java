package org.kobehjk.net.model;

import android.util.Pair;

import org.kobehjk.net.api.ApiClient;
import org.kobehjk.net.api.HeaderMap;
import org.kobehjk.net.api.ParamsMap;
import org.kobehjk.net.app.AppConstants;
import org.kobehjk.net.entity.UserEntity;
import org.kobehjk.net.support.okhttp.callback.ResultCallback;
import org.kobehjk.net.support.okhttp.request.OkHttpRequest;

import java.io.File;

/**
 * Created by sunger on 2015/11/28.
 */
public class EditUserInfoModel {

    public OkHttpRequest update(String screen_name, String gender, String description, String filePath, ResultCallback<UserEntity> callback) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(AppConstants.ParamKey.SCREEN_NAEM_KEY,screen_name);
        paramsMap.put(AppConstants.ParamKey.GENDER_KEY,gender);
        paramsMap.put(AppConstants.ParamKey.DESCRIPTION_KEY,description);
        HeaderMap headerMap = new HeaderMap();
        OkHttpRequest.Builder builder = ApiClient.create(AppConstants.RequestPath.USERS_UPDATE, paramsMap, headerMap).tag("");
        if (filePath != null) {
            return builder.files(new Pair<>("avatar", new File(filePath))).upload(callback);
        } else {
            return builder.post(callback);
        }
    }
}
