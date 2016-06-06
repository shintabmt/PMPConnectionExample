package com.example.thucphan.pmplibexample;

import com.example.thucphan.pmplibexample.listener.LoginListener;
import com.phillip.pmp.api.MessageListener;
import com.phillip.pmp.common.PMPException;

/**
 * Created by thuc.phan on 6/6/2016.
 */
public class MyMessageListener implements MessageListener {

    private LoginListener loginListener;

    public LoginListener getLoginListener() {
        return loginListener;
    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public void loginCallback(String message) throws PMPException {
        // call here when API login PMP Server success
        System.out.println("loginCallback:" + message);
        loginListener.onLoginSuccess(message);
    }

    public void subscribeCallback(String message) throws PMPException {
        // call here when API receive subscrib message
        System.out.println("subscribeCallback:" + message);

    }

    public void queryCallback(String message) throws PMPException {
        // call here when API receive query message
        System.out.println("queryCallback:" + message);

    }

    public void connectionStatusCallback(String message) throws PMPException {
        // call here when API inner status happen change or error
        System.out.println("connectionStatusCallback:" + message);

    }

    public void subscribeQueryConfirmCallback(String message)
            throws PMPException {
        // call here when API receive query or subscribe acknowledgement message
        System.out.println("subscribeQueryConfirmCallback:" + message);

    }

    @Override
    public void debugCallback(String arg0) throws PMPException {
        // TODO Auto-generated method stub
        System.out.println("debugCallback:" + arg0);

    }

    @Override
    public void exceptionCallback(String arg0) {
        // TODO Auto-generated method stub
        System.out.println("exceptionCallback:" + arg0);

    }

}
