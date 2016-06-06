package com.example.thucphan.pmplibexample;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AppCompatButton mChooseTopicBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChooseTopicBtn = (AppCompatButton) findViewById(R.id.chooseTopicBtn);
        mChooseTopicBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chooseTopicBtn:
                showTopicDialog();
                break;
        }
    }

    private void showTopicDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle(R.string.choice_topic)
                .setItems(getResources().getStringArray(R.array.topic_arrays), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        dialog.show();
    }
}
