package com.eventchat.manager;

import org.apache.http.HttpResponse;

import com.eventchat.util.DebugLog;
import com.eventchat.webapi.EventChatClient;
import com.eventchat.webapi.OnReceiveCallback;

public final class ProfileManager implements IDispose {

    private static final String TAG = ProfileManager.class.getSimpleName();

    private static ProfileManager sInstance = new ProfileManager();

    private EventChatClient mClient = null;

    private ProfileManager() {
        DebugLog.d(TAG, "ProfileManager");
        mClient = new EventChatClient();
    }

    public static ProfileManager getInstance() {
        DebugLog.d(TAG, "getInstance");
        return sInstance;
    }

    public void login(String userName, String password) {
        DebugLog.d(TAG, "login userName = " + userName + " password = "
                + password);
        if (mClient != null) {
        }
    }

    public void logout() {
        DebugLog.d(TAG, "logout");
        if (mClient != null) {
        }
    }

    public void loginStatus() {
        DebugLog.d(TAG, "loginStatus");

    }

    @Override
    public void dispose() {
    }

    private class LoginCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {

        }
    }

    private class LogoutCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {

        }
    }

    private class LoginStatusCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {

        }
    }

    private class GetUserCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {

        }
    }

    private class CreateUserCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {

        }
    }
}
