package org.kobehjk.net.presenter;

/**
 * Created by kobehjk on 2015/11/19.
 */
public interface ResetPasswordPresenter {


    void resetPassword(String verify_code, String phone, String pwd);

    void getVerifySMS(String phone, String pwd);

}
