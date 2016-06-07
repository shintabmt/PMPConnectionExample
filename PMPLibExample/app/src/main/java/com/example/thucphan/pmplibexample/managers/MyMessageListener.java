package com.example.thucphan.pmplibexample.managers;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.thucphan.pmplibexample.MyApplication;
import com.phillip.pmp.api.MessageListener;
import com.phillip.pmp.common.PMPException;

/**
 * Created by thuc.phan on 6/6/2016.
 */
public class MyMessageListener implements MessageListener {

    public static final String ACTION_EVENT = "com.example.ACTION_EVENT";
    public static final String EVENT_LOGIN = "com.example.EVENT_LOGIN";
    public static final String EVENT_QUERY = "com.example.EVENT_QUERY";
    public static final String EVENT_SUBSCRIBE = "com.example.EVENT_SUBSCRIBE";
    public static final String EVENT_CONNECTION_STATUS = "com.example.EVENT_CONNECTION_STATUS";
    public static final String EVENT_SUBSCRIBE_CONFIRM = "com.example.EVENT_SUBSCRIBE_CONFIRM";
    public static final String EVENT_DEBUG = "com.example.EVENT_DEBUG";
    public static final String EVENT_EXCEPTION = "com.example.EVENT_EXCEPTION";

    public void loginCallback(String message) throws PMPException {
        // call here when API login PMP Server success
        sendBroadCast(EVENT_LOGIN, message);
    }

    public void subscribeCallback(String message) throws PMPException {
        // call here when API receive subscrib message
        sendBroadCast(EVENT_SUBSCRIBE, message);
    }

    public void queryCallback(String message) throws PMPException {
        // call here when API receive query message
        sendBroadCast(EVENT_QUERY, message);
    }

    public void connectionStatusCallback(String message) throws PMPException {
        // call here when API inner status happen change or error
        sendBroadCast(EVENT_CONNECTION_STATUS, message);
    }

    public void subscribeQueryConfirmCallback(String message)
            throws PMPException {
        sendBroadCast(EVENT_SUBSCRIBE_CONFIRM, message);
    }

    @Override
    public void debugCallback(String message) throws PMPException {
        sendBroadCast(EVENT_DEBUG, message);
    }

    @Override
    public void exceptionCallback(String message) {
        sendBroadCast(EVENT_EXCEPTION, message);

    }

    private void sendBroadCast(String event, String message) {
        Intent intent = new Intent();
        intent.setAction(ACTION_EVENT);
        intent.putExtra("event", event);
        intent.putExtra("message", message);
        LocalBroadcastManager.getInstance(MyApplication.getInstance()).sendBroadcast(intent);
    }
}
