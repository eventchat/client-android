package com.eventchat;

import com.eventchat.util.DebugLog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends Activity {

    private static final int DELAYED_TIME = 1 * 1000;

    private static final String TAG = SplashActivity.class.getSimpleName();

    private Handler sHandler = new Handler(new MessageCallback());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DebugLog.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.splash_activity);
        sHandler.sendEmptyMessageDelayed(0, DELAYED_TIME);
    }

    private class MessageCallback implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {
            SplashActivity.this.finish();
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            SplashActivity.this.startActivity(intent);
            return true;
        }

    }
}
