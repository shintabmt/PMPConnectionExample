package com.example.thucphan.pmplibexample;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thucphan.pmplibexample.listener.LoginListener;
import com.phillip.pmp.common.PMPException;

/**
 * Created by thuc.phan on 6/6/2016.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginListener{

    TextView mIdTextView;
    TextView mPasswordTextView;
    AppCompatButton mLoginBtn;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
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

    private void login(){
        PMPConnectionManager.getInstance().login(mIdTextView.getText().toString(), mPasswordTextView.getText().toString(), this);
    }

    @Override
    public void onLoginSuccess(String message) {
        Toast.makeText(this, "Login Response : " + message, Toast.LENGTH_SHORT).show();
        Intent  intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
