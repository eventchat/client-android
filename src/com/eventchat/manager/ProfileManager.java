package com.eventchat.manager;

import org.apache.http.HttpResponse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.eventchat.LoginActivity;
import com.eventchat.MainActivity;
import com.eventchat.util.DebugLog;
import com.eventchat.util.WebApiUtil;
import com.eventchat.view.ProgressDialogView;
import com.eventchat.webapi.EventChatClient;
import com.eventchat.webapi.OnReceiveCallback;

public final class ProfileManager implements IDispose {

    private static final String TAG = ProfileManager.class.getSimpleName();

    private static ProfileManager sInstance = new ProfileManager();

    private static Context sContext = null;

    private EventChatClient mClient = null;

    private ProgressDialogView mProgressDialog = null;

    private ProfileManager() {
        DebugLog.d(TAG, "ProfileManager");
        mClient = EventChatClient.getInstance();
        mProgressDialog = new ProgressDialogView();
    }

    public static ProfileManager getInstance(Context context) {
        DebugLog.d(TAG, "getInstance");
        sContext = context;
        return sInstance;
    }

    public void login(String name, String password) {
        DebugLog.d(TAG, "login name = " + name + " password = " + password);
        if (mClient != null) {
            mProgressDialog.show(sContext, "Longin", "Longin");
            mClient.login(name, password, new LoginCallback());
        }
    }

    public void logout() {
        DebugLog.d(TAG, "logout");
        if (mClient != null) {
            mClient.logout(new LogoutCallback());
        }
    }

    public void loginStatus() {
        DebugLog.d(TAG, "loginStatus");
        if (mClient != null) {
            mClient.loginStatus(new LoginStatusCallback());
        }
    }

    public void getUser(String userId) {
        DebugLog.d(TAG, "getUser userId = " + userId);
        if (mClient != null) {
            mClient.getUser(userId, new GetUserCallback());
        }
    }

    public void createUser(String name, String email, String password,
            String info) {
        DebugLog.d(TAG, "createUser name = " + name + " email = " + email
                + " password = " + password + " info = " + info);
        if (mClient != null) {
            mClient.createUser(name, email, password, info,
                    new CreateUserCallback());
        }
    }

    @Override
    public void dispose() {

    }

    private void startActivity(Class<?> cls) {
        Intent intent = new Intent(sContext, cls);
        ((Activity) (sContext)).startActivity(intent);
    }

    private class LoginCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
            mProgressDialog.dismiss();
            ((LoginActivity) sContext).finish();
            startActivity(MainActivity.class);
        }
    }

    private class LogoutCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }

    private class LoginStatusCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }

    private class GetUserCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }

    private class CreateUserCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }
}
