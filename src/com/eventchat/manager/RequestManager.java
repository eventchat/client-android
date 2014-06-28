package com.eventchat.manager;

import com.eventchat.util.DebugLog;

public final class RequestManager {

    private static final String TAG = RequestManager.class.getSimpleName();

    private static RequestManager sInstance = new RequestManager();

    private RequestManager() {
        DebugLog.d(TAG, "RequestManager");
    }

    public static RequestManager getInstance() {
        DebugLog.d(TAG, "getInstance");
        return sInstance;
    }
}
