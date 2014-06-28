package com.eventchat.manager;

import com.eventchat.util.DebugLog;

public final class ProfileManager {

    private static final String TAG = ProfileManager.class.getSimpleName();

    private static ProfileManager sInstance = new ProfileManager();

    private ProfileManager() {
        DebugLog.d(TAG, "ProfileManager");
    }

    public static ProfileManager getInstance() {
        DebugLog.d(TAG, "getInstance");
        return sInstance;
    }

    public void login(String userName, String password) {
        DebugLog.d(TAG, "login userName = " + userName + " password = "
                + password);
    }

    public void logout() {
        DebugLog.d(TAG, "logout");
    }
}
