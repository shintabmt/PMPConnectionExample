package com.example.thucphan.pmplibexample.activity;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.thucphan.pmplibexample.views.MyAdapter;
import com.example.thucphan.pmplibexample.managers.MyMessageListener;
import com.example.thucphan.pmplibexample.managers.PMPConnectionManager;
import com.example.thucphan.pmplibexample.R;
import com.example.thucphan.pmplibexample.base.ReceiverActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ReceiverActivity implements View.OnClickListener {

    AppCompatButton mChooseTopicBtn;
    RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private List<String> mInformationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChooseTopicBtn = (AppCompatButton) findViewById(R.id.chooseTopicBtn);
        mChooseTopicBtn.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mInformationList = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chooseTopicBtn:
                showTopicDialog();
                break;
        }
    }

    @Override
    public void handleMessage(String event, String message) {
        if (event.equals(MyMessageListener.EVENT_SUBSCRIBE_CONFIRM) || event.equals(MyMessageListener.EVENT_SUBSCRIBE)) {
            mInformationList.add(message);
            mAdapter.setItems(mInformationList);
        }
    }

    private void showTopicDialog() {
        final String[] strings = getResources().getStringArray(R.array.topic_arrays);
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle(R.string.choice_topic)
                .setItems(strings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        subscribeTopic(strings[which]);
                    }
                }).create();
        dialog.show();
    }

    private void subscribeTopic(String topicName) {
        AsyncTask<String, Void, Void> task = new AsyncTask<String, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showIndeterminateProgressDialog(R.string.subscribe, R.string.please_wait);
            }

            @Override
            protected Void doInBackground(String... strings) {
                PMPConnectionManager.getInstance().subscribe(strings[0]);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dismissIndeterminateProgressDialog();
            }
        };
        task.execute(topicName);
    }

}
