package com.example.thucphan.pmplibexample;

import com.example.thucphan.pmplibexample.listener.LoginListener;
import com.phillip.pmp.common.PMPException;
import com.phillip.pmp.core.PMPConnection;

/**
 * Created by thuc.phan on 6/6/2016.
 */
public class PMPConnectionManager {
    PMPConnection connection;
    MyMessageListener myMessageListener;
    private static PMPConnectionManager sInstance;
    private PMPConnectionManager(){

    }
    public static PMPConnectionManager  getInstance(){
        if (sInstance == null){
            sInstance = new PMPConnectionManager();
        }
        return sInstance;
    }
    public void init()  {
        myMessageListener = new MyMessageListener();
        try {
            connection = new PMPConnection(Constant.API_URL, myMessageListener, 5 );
        } catch (PMPException e) {
            e.printStackTrace();
        }
    }
    public void login(String id, String pwd, LoginListener listener){
        try {
            myMessageListener.setLoginListener(listener);
            connection.login(id, pwd);
        } catch (PMPException e) {
            e.printStackTrace();
        }
    }
}
