package com.eventchat.manager;

import com.eventchat.util.DebugLog;

public final class FeedManager {

    private static final String TAG = FeedManager.class.getSimpleName();

    private static FeedManager sInstance = new FeedManager();

    private FeedManager() {
        DebugLog.d(TAG, "FeedManager");
    }

    public static FeedManager getInstance() {
        DebugLog.d(TAG, "getInstance");
        return sInstance;
    }
}
