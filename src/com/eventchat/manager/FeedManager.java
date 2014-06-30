package com.eventchat.manager;

import org.apache.http.HttpResponse;

import com.eventchat.util.DebugLog;
import com.eventchat.webapi.OnReceiveCallback;

public final class FeedManager implements IDispose, OnReceiveCallback {

    private static final String TAG = FeedManager.class.getSimpleName();

    private static FeedManager sInstance = new FeedManager();

    private FeedManager() {
        DebugLog.d(TAG, "FeedManager");
    }

    public static FeedManager getInstance() {
        DebugLog.d(TAG, "getInstance");
        return sInstance;
    }

    @Override
    public void onReceive(HttpResponse response) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }
}
