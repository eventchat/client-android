package com.eventchat;

import com.eventchat.util.DebugLog;

import android.app.Activity;
import android.os.Bundle;

public class LoginActivity extends Activity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DebugLog.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
    }
}
