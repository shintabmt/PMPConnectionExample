package com.example.thucphan.pmplibexample.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thucphan.pmplibexample.managers.MyMessageListener;
import com.example.thucphan.pmplibexample.managers.PMPConnectionManager;
import com.example.thucphan.pmplibexample.R;
import com.example.thucphan.pmplibexample.base.ReceiverActivity;

/**
 * Created by thuc.phan on 6/6/2016.
 */
public class LoginActivity extends ReceiverActivity implements View.OnClickListener {

    TextView mIdTextView;
    TextView mPasswordTextView;
    AppCompatButton mLoginBtn;
    TextInputLayout mInputIdLayout;
    TextInputLayout mInputPasswordLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        setupViews();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                if (!validateId()) {
                    return;
                }
                if (!validatePassword()) {
                    return;
                }
                login();
                break;
            default:
                break;
        }
    }

    private void setupViews() {
        mIdTextView = (TextView) findViewById(R.id.loginId);
        mIdTextView.addTextChangedListener(new LoginTextWatcher(mIdTextView));
        mPasswordTextView = (TextView) findViewById(R.id.loginPassword);
        mPasswordTextView.addTextChangedListener(new LoginTextWatcher(mPasswordTextView));

        mLoginBtn = (AppCompatButton) findViewById(R.id.loginBtn);
        mLoginBtn.setOnClickListener(this);
        mInputIdLayout = (TextInputLayout) findViewById(R.id.idInputLayout);
        mInputPasswordLayout = (TextInputLayout) findViewById(R.id.passwordInputLayout);
    }


    private void login() {
        AsyncTask<String, Void, Void> task = new AsyncTask<String, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showIndeterminateProgressDialog(R.string.login, R.string.please_wait);
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


    private boolean validateId() {
        String id = mIdTextView.getText().toString().trim();
        if (TextUtils.isEmpty(id)) {
            mInputIdLayout.setError(getString(R.string.err_msg_id));
            return false;
        }
        mInputIdLayout.setError("");
        return true;
    }

    private boolean validatePassword() {
        String pwd = mPasswordTextView.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            mInputPasswordLayout.setError(getString(R.string.err_msg_pwd));
            return false;
        }
        mInputPasswordLayout.setError("");
        return true;
    }


    private class LoginTextWatcher implements TextWatcher {

        private View view;

        private LoginTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.loginId:
                    validateId();
                    break;
                case R.id.loginPassword:
                    validatePassword();
                    break;
                default:
                    break;
            }
        }
    }

}
