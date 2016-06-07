package com.example.thucphan.pmplibexample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thucphan.pmplibexample.base.ReceiverActivity;

/**
 * Created by thuc.phan on 6/6/2016.
 */
public class LoginActivity extends ReceiverActivity implements View.OnClickListener {

    TextView mIdTextView;
    TextView mPasswordTextView;
    AppCompatButton mLoginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        mIdTextView = (TextView) findViewById(R.id.loginId);
        mPasswordTextView = (TextView) findViewById(R.id.loginPassword);
        mLoginBtn = (AppCompatButton) findViewById(R.id.loginBtn);
        mLoginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                login();
                break;
            default:
                break;
        }
    }

    private void login() {
        AsyncTask<String, Void, Void> task = new AsyncTask<String, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showIndeterminateProgressDialog("Login", "Please Wait");
            }

            @Override
            protected Void doInBackground(String... strings) {
                PMPConnectionManager.getInstance().login(strings[0], strings[1]);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dismissIndeterminateProgressDialog();
            }
        };
        task.execute(mIdTextView.getText().toString(), mPasswordTextView.getText().toString());
    }

    @Override
    public void handleMessage(String event, String message) {
        if (event.equals(MyMessageListener.EVENT_LOGIN)) {
            Toast.makeText(this, "Login Response : " + message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}
