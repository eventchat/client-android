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
import org.apache.http.impl.client.DefaultHttpClient;

import com.eventchat.util.DebugLog;

public final class EventChatClient {

    private static final String TAG = EventChatClient.class.getSimpleName();

    private static final int FIXED_SIZE = 16;

    private static EventChatClient sInstance = new EventChatClient();

    private ExecutorService mExecutorService = null;

    private BlockingQueue<EventChatRequest> mRequestQueue = null;

    private DefaultHttpClient mHttpClient = null;

    private HttpHost mHttpHost = null;

    private RequestThread mRequestThread = null;

    private EventChatClient() {
        DebugLog.d(TAG, "EventChatClient");
        mExecutorService = Executors.newFixedThreadPool(FIXED_SIZE);
        mRequestQueue = new LinkedBlockingQueue<EventChatRequest>();
        mHttpClient = new DefaultHttpClient();
        mHttpHost = new HttpHost("eventchat.herokuapp.com");
        mRequestThread = new RequestThread();
        mRequestThread.start();
    }

    public static EventChatClient getInstance() {
        return sInstance;
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

    public void loginStatus(OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildCheckLoginRequest()).with(callback));
    }

    public void joinEvent(String eventId, OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildJointEventRequest(eventId)).with(callback));
    }

    public void getAttendeeList(String eventId, OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildGetAttendeeList(eventId)).with(callback));
    }

    public void getUser(String userId, OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildGetUserRequest(userId)).with(callback));
    }

    public void createUser(String name, String email, String password,
            String info, OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildCreateUserRequest(name, email, password,
                        info)).with(callback));
    }

    public void getPost(String postId, OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildGetPostRequest(postId)).with(callback));
    }

    public void createPost(String title, String type, String body,
            String eventId, OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildCreatePostRequest(title, type, body,
                        eventId)).with(callback));
    }

    public void deletePost(String postId, OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildDeletePostRequest(postId)).with(callback));
    }

    public void searchPost(double latitude, double longitude, int maxDistance,
            OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildGetPostBySearchRequest(latitude, longitude,
                        maxDistance)).with(callback));
    }

    public void createComment(String postId, String body,
            OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildCreateCommentToPostRequest(postId, body))
                .with(callback));
    }

    public void getEvent(String eventId, OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildGetEventRequest(eventId)).with(callback));
    }

    public void createEvent(String name, double longitude, double latitude,
            String address, String startTime, String endTime, String desc,
            OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildCreateEventRequest(name, longitude,
                        latitude, address, startTime, endTime, desc)).with(
                callback));
    }

    public void updateEvent(String name, double longitude, double latitude,
            String address, String startTime, String endTime, String desc,
            OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildUpdateEventRequest(name, longitude,
                        latitude, address, startTime, endTime, desc)).with(
                callback));
    }

    public void deleteEvent(String eventId, OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildDeleteEventRequest(eventId)).with(callback));
    }

    public void getMessage(String eventId, OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildGetMessageByEventRequest(eventId)).with(
                callback));
    }

    public void getNotification(OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildGetNotificationRequest()).with(callback));
    }

    public void readNotification(String id, OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildReadNotificationByIdRequest(id)).with(
                callback));
    }

    public void readAllNotifications(OnReceiveCallback callback) {
        sendRequest(new EventChatRequest().with(
                RequestBuilder.buildReadNotificationRequest()).with(callback));
    }

    public void destory() {
        mRequestThread.stopThread();
    }

    private void sendRequest(final EventChatRequest request) {
        if (mRequestQueue != null) {
            mRequestQueue.add(request);
        }
    }

    private void send(final EventChatRequest request) {
        DebugLog.d(TAG, "send");
        if (request != null) {
            mExecutorService.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        HttpRequest req = request.getHttpRequest();
                        if (req != null) {
                            HttpResponse response = mHttpClient.execute(
                                    mHttpHost, req);
                            OnReceiveCallback callback = request.getCallback();
                            if (callback != null) {
                                callback.onReceive(response);
                            }
                        }
                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
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
