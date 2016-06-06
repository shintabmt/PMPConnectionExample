package com.example.thucphan.pmplibexample.base;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by shintabmt@gmai.com on 6/6/2016.
 */
public abstract class ReceiverActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("event"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String event = intent.getStringExtra("event");
            String message = intent.getStringExtra("message");
            Log.d("THUC-PHAN", "event: " + event + "       message : " + message);
            handleMessage(event, message);
        }
    };
    public abstract void handleMessage(String event, String message);


    /**
     * Displays a progress dialog
     * @param title title of the dialog
     * @param message message of the dialog
     */
    public void showIndeterminateProgressDialog(String title, String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog = ProgressDialog.show(this, title, message, true);
        progressDialog.setCancelable(false);
    }

    /**
     * Dismisses the progress dialog
     */
    public void dismissIndeterminateProgressDialog() {
        progressDialog.dismiss();
    }
}
