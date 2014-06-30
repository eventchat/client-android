package com.eventchat.webapi;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.eventchat.entity.Comment;
import com.eventchat.entity.Event;
import com.eventchat.entity.Post;
import com.eventchat.entity.Session;
import com.eventchat.entity.User;
import com.eventchat.util.DebugLog;

public final class EventChatClient {

    private static final String TAG = EventChatClient.class.getSimpleName();

    private static final int FIXED_SIZE = 4;

    private ExecutorService mExecutorService = null;

    private HttpClient mHttpClient = null;

    private HttpHost mHttpHost = null;

    private BlockingQueue<EventChatRequest> mRequestQueue = null;

    public EventChatClient() {
        DebugLog.d(TAG, "EventChatClient");
        mExecutorService = Executors.newFixedThreadPool(FIXED_SIZE);
        mHttpClient = new DefaultHttpClient();
        mHttpHost = new HttpHost("eventchat.herokuapp.com");
        mRequestQueue = new LinkedBlockingQueue<EventChatRequest>();
    }

    public void login(String name, String password, OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildLoginRequest(name, password))
                .with(callback));
    }

    public void logout(OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildLogoutRequest()).with(callback));
    }

    public void getUser(String userId, OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildGetUserRequest(userId)).with(callback));
    }

    public void createUser(User user, OnReceiveCallback callback) {
    }

    public void getPost(String postId, OnReceiveCallback callback) {

    }

    public void createPost(Post post, OnReceiveCallback callback) {

    }

    public void deletePost(String postId, OnReceiveCallback callback) {

    }

    public void searchPost(double latitude, double longitude, int maxDistance,
            OnReceiveCallback callback) {

    }

    public void createComment(Comment comment, OnReceiveCallback callback) {

    }

    public void getEvent(String eventId, OnReceiveCallback callback) {

    }

    public void createEvent(Event event, OnReceiveCallback callback) {

    }

    public void updateEvent(Event event, OnReceiveCallback callback) {

    }

    public void deleteEvent(String eventId, OnReceiveCallback callback) {

    }

    public void getMessage(String eventId, OnReceiveCallback callback) {

    }

    public void getNotification(OnReceiveCallback callback) {

    }

    public void readNotification(String id, OnReceiveCallback callback) {

    }

    public void readAllNotifications(OnReceiveCallback callback) {

    }

    private void sendRequest(final EventChatRequest request) {
        if (mRequestQueue != null) {
            mRequestQueue.add(request);
        }
    }

    private void send(final EventChatRequest request) {
        DebugLog.d(TAG, "send");
        mExecutorService.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    HttpResponse response = mHttpClient.execute(mHttpHost,
                            request.getHttpRequest());
                    request.getCallback().onReceive(response);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class RequestThread extends Thread {

        private boolean mIsRunning = false;

        @Override
        public void run() {
            mIsRunning = true;
            while (mIsRunning) {
                if (mRequestQueue != null) {
                    try {
                        EventChatRequest request = mRequestQueue.take();
                        send(request);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void stopThread() {
            mIsRunning = false;
        }
    }
}
