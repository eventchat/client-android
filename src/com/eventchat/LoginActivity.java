package com.eventchat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eventchat.manager.ProfileManager;
import com.eventchat.util.DebugLog;
import com.eventchat.util.WebApiUtil;

public class LoginActivity extends Activity implements OnClickListener {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private Button mLoginButton = null;

    private EditText mUserNameText = null;

    private EditText mPasswordText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DebugLog.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        getActionBar().setBackgroundDrawable(
                getResources().getDrawable(R.color.theme));

        if (WebApiUtil.isInternetConnected(this)) {
            setContentView(R.layout.login_activity);
            mLoginButton = (Button) findViewById(R.id.login);
            mLoginButton.setOnClickListener(this);
            mUserNameText = (EditText) findViewById(R.id.user_name);
            mPasswordText = (EditText) findViewById(R.id.password);
        } else {
            Toast.makeText(this, "cannot connect to the Internet",
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        String userName = mUserNameText.getText().toString();
        String password = mPasswordText.getText().toString();
        ProfileManager.getInstance(this).login(userName, password);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
