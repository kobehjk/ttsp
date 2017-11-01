package org.kobehjk.net.model;

import org.kobehjk.net.api.ApiClient;
import org.kobehjk.net.app.AppConstants;
import org.kobehjk.net.entity.CategoryEntity;
import org.kobehjk.net.support.okhttp.callback.ResultCallback;
import org.kobehjk.net.support.okhttp.request.OkHttpRequest;

import java.util.List;

public class CategoryModel {
    /**
     * 获取首页分类列表
     *
     * @param callback
     * @return
     */
    public OkHttpRequest getCategory(ResultCallback<List<CategoryEntity>> callback) {
        return ApiClient.create(AppConstants.RequestPath.CATEGOTY).tag("").get(callback);
    }
}
