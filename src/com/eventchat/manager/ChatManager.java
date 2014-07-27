package com.eventchat.manager;

import org.apache.http.HttpResponse;

import com.eventchat.util.DebugLog;
import com.eventchat.webapi.OnReceiveCallback;

public final class ChatManager implements IDispose, OnReceiveCallback {

    private static final String TAG = ChatManager.class.getSimpleName();

    private static ChatManager sInstance = new ChatManager();

    private ChatManager() {
        DebugLog.d(TAG, "ChatManager");
    }

    public static ChatManager getInstance() {
        DebugLog.d(TAG, "getInstance");
        return sInstance;
    }

    public void sendMessage(String to, String message) {

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
