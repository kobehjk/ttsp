package org.kobehjk.net.api;

import org.kobehjk.net.entity.OauthUserEntity;
import org.kobehjk.net.app.App;
import org.kobehjk.net.app.AppConstants;

import java.util.HashMap;

/**
 * Created by sunger on 15/11/21.
 */
public class HeaderMap extends HashMap<String, String> {
    public HeaderMap() {
        OauthUserEntity entity= App.getInstance().getOauthUserEntity();
        if (entity != null) {
            put(AppConstants.ParamKey.ACCESS_TOKEN_KEY, entity.getAccess_token());
        }
    }
}
