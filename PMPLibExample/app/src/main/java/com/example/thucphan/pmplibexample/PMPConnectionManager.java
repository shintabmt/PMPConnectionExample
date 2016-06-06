package com.example.thucphan.pmplibexample;

import com.phillip.pmp.api.SubscribeOrQueryBean;
import com.phillip.pmp.api.SubscribeQueryRequest;
import com.phillip.pmp.common.PMPException;
import com.phillip.pmp.core.PMPConnection;

/**
 * Created by thuc.phan on 6/6/2016.
 */
public class PMPConnectionManager {
    private static final String API_URL = "https://118.189.144.221:443/IPhoneAPI/PMPPrice";
    private static final int PULL_TIME = 0;
    private static final int TIME_OUT = 3000;
    private PMPConnection connection;
    private MyMessageListener myMessageListener;
    private static PMPConnectionManager sInstance;

    private PMPConnectionManager() {

    }

    public static PMPConnectionManager getInstance() {
        if (sInstance == null) {
            sInstance = new PMPConnectionManager();
        }
        return sInstance;
    }

    public void init() {
        myMessageListener = new MyMessageListener();
        try {
            connection = new PMPConnection(API_URL, myMessageListener, PULL_TIME);
            connection.setConnectionTimeout(TIME_OUT);
        } catch (PMPException e) {
            e.printStackTrace();
        }
    }

    public void login(final String id, String pwd) {
        try {
            connection.login(id, pwd);
        } catch (PMPException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String topicName) {
        // build subscribe request
        SubscribeQueryRequest subscribeQueryRequest = new SubscribeQueryRequest();
        // fill subscribe message,clinet need use SubscribeOrQueryBean array
        SubscribeOrQueryBean subscribeOrQueryBean[] = new SubscribeOrQueryBean[1];

        subscribeOrQueryBean[0] = new SubscribeOrQueryBean();
        // this parameter is subscribe topic name
        subscribeOrQueryBean[0].setTopicName(topicName);
        // set subscribe filed.P,v,t are filed name,many fileds use comma division
        String fileds = "1,2,3,999,17,11";

        subscribeOrQueryBean[0].setFileds(fileds);

//        subscribeOrQueryBean[1] = new SubscribeOrQueryBean();
//        // this parameter is subscribe topic name
//        subscribeOrQueryBean[1].setTopicName("\\\\SGXSE\\\\[SGXSE]TOP02");
//        // subscribe filed
//        String fileds1 = "1,2,3,999,17,11";
//
//        subscribeOrQueryBean[1].setFileds(fileds1);


        subscribeQueryRequest.setRequestType(1);
        subscribeQueryRequest.setSubscribeOrQueryBean(subscribeOrQueryBean);
        try {
            // send subscribe request
            connection.submitSubscribeQueryRequest(subscribeQueryRequest);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void query(String topicName) {
        // build query request
        SubscribeQueryRequest subscribeQueryRequest = new SubscribeQueryRequest();
        // fill query message,clinet need use SubscribeOrQueryBean array
        SubscribeOrQueryBean subscribeOrQueryBean[] = new SubscribeOrQueryBean[1];

        subscribeOrQueryBean[0] = new SubscribeOrQueryBean();
        // this parameter is query topic name
        subscribeOrQueryBean[0].setTopicName(topicName);
        // set filed.P,v,t are filed name,many fileds use comma division
        String fileds = "1,2,3,999,17,11";

        subscribeOrQueryBean[0].setFileds(fileds);
        subscribeOrQueryBean[0].setDayNumber(455);
        subscribeOrQueryBean[0].setPageNumber(0);
        // set type to query,note:integer 3 is query
        subscribeQueryRequest.setRequestType(3);
        subscribeQueryRequest.setSubscribeOrQueryBean(subscribeOrQueryBean);
        try {
            // send query request
            connection.submitSubscribeQueryRequest(subscribeQueryRequest);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public  void unSubscribe(String topicName) {
        // build cancel subscribe request
        SubscribeQueryRequest subscribeQueryRequest = new SubscribeQueryRequest();
        // fill cancel subscribe message,clinet need use SubscribeOrQueryBean
        // array
        SubscribeOrQueryBean subscribeOrQueryBean[] = new SubscribeOrQueryBean[1];

        subscribeOrQueryBean[0] = new SubscribeOrQueryBean();
        // this parameter is cancel subscribe topic name
        subscribeOrQueryBean[0].setTopicName(topicName);
        // set cancel subscribe filed.P,v,t are filed name,many fileds use comma
        // division
        String fileds = "1,2,3,999,17,11";

        subscribeOrQueryBean[0].setFileds(fileds);
        // set type to cancel subscribe,note:integer 2 is subscribe
        subscribeQueryRequest.setRequestType(2);

        subscribeQueryRequest.setSubscribeOrQueryBean(subscribeOrQueryBean);
        try {
            // send cancel subscribe request
            connection.submitSubscribeQueryRequest(subscribeQueryRequest);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
