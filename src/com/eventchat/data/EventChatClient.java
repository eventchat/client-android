package com.eventchat.data;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.http.HttpRequest;

import android.os.Message;

import com.eventchat.observer.ISubject;
import com.eventchat.util.DebugLog;

public final class EventChatClient implements ISubject {

    private static final String TAG = EventChatClient.class.getSimpleName();

    private static final int FIXED_SIZE = 4;

    private static EventChatClient sChatClient = new EventChatClient();

    private Queue<HttpRequest> mRequestQueue = null;

    private ExecutorService mExecutorService = null;

    private RequestThread mRequestThread = null;

    private EventChatClient() {
        DebugLog.d(TAG, "EventChatClient");
        mRequestQueue = new LinkedBlockingQueue<HttpRequest>();
        mExecutorService = Executors.newFixedThreadPool(FIXED_SIZE);
        mRequestThread = new RequestThread();
    }

    public void send(HttpRequest request) {
        DebugLog.d(TAG, "send");
        if (mRequestQueue != null) {
            mRequestQueue.add(request);
        }
    }

    private class RequestThread implements Runnable {

        @Override
        public void run() {
            if (mExecutorService != null) {

            }
        }

    }

    @Override
    public void notfiy(ISubject subject, Message msg) {

    }
}
