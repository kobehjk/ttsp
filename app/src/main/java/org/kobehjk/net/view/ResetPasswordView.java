package org.kobehjk.net.view;

/**
 * Created by kobehjk on 2015/11/19.
 */
public interface ResetPasswordView {

    void showSuccess();

    void showResetError(String msg);


    void showSmsSuccess();


    void showMsg(String msg);

}
