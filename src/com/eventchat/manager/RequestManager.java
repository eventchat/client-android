package com.eventchat.manager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.http.HttpRequest;

import com.eventchat.entity.Session;
import com.eventchat.util.DebugLog;
import com.eventchat.webapi.EventChatClient;

public final class RequestManager implements IDispose {

    private static final String TAG = RequestManager.class.getSimpleName();

    private static RequestManager sInstance = new RequestManager();

    private BlockingQueue<HttpRequest> mRequestQueue = null;

    private EventChatClient mClient = null;

    private RequestManager() {
        DebugLog.d(TAG, "RequestManager");
        mRequestQueue = new LinkedBlockingQueue<HttpRequest>();
        mClient = new EventChatClient();
    }

    public static RequestManager getInstance() {
        DebugLog.d(TAG, "getInstance");
        return sInstance;
    }

    public void login(String userName, String password) {
        Session session = new Session();
        session.setUserName(userName);
        session.setPassword(password);
        // sendRequest(RequestBuilder.buildLoginRequest(session));
    }

    private void sendRequest(HttpRequest request) {
        if (mRequestQueue != null && request != null) {
            mRequestQueue.add(request);
        }
    }

    @Override
    public void dispose() {

    }
}
