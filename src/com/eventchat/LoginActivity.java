package com.eventchat;

import com.eventchat.manager.RequestManager;
import com.eventchat.util.DebugLog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity implements OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private Button mLoginButton = null;

    private EditText mUserNameText = null;

    private EditText mPasswordText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DebugLog.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mLoginButton = (Button) findViewById(R.id.login);
        mLoginButton.setOnClickListener(this);
        mUserNameText = (EditText) findViewById(R.id.user_name);
        mPasswordText = (EditText) findViewById(R.id.password);
    }

    @Override
    public void onClick(View v) {
        String userName = mUserNameText.getText().toString();
        String password = mPasswordText.getText().toString();
        RequestManager.getInstance().login(userName, password);
    }
}
