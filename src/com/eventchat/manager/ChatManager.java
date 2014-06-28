package com.eventchat.manager;

import com.eventchat.util.DebugLog;

public final class ChatManager {

    private static final String TAG = ChatManager.class.getSimpleName();

    private static ChatManager sInstance = new ChatManager();

    private ChatManager() {
        DebugLog.d(TAG, "ChatManager");
    }

    public static ChatManager getInstance() {
        DebugLog.d(TAG, "getInstance");
        return sInstance;
    }
}
