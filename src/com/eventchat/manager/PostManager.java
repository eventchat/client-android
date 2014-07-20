package com.eventchat.manager;

import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.eventchat.util.DebugLog;
import com.eventchat.util.WebApiUtil;
import com.eventchat.webapi.EventChatClient;
import com.eventchat.webapi.OnReceiveCallback;

public final class PostManager implements IDispose {

    private static final String TAG = PostManager.class.getSimpleName();

    private static PostManager sInstance = new PostManager();

    private static Context sContext = null;

    private EventChatClient mClient = null;

    private PostManager() {
        DebugLog.d(TAG, "FeedManager");
        mClient = EventChatClient.getInstance();
    }

    public static PostManager getInstance(Context context) {
        DebugLog.d(TAG, "getInstance");
        sContext = context;
        return sInstance;
    }

    public void getPost(String postId) {
        if (mClient != null) {
            mClient.getPost(postId, new GetPostCallback());
        }
    }

    public void createPost(String title, String type, String body,
            String eventId) {
        if (mClient != null) {
            mClient.createPost(title, type, body, eventId,
                    new CreatePostCallback());
        }
    }

    public void deletePost(String postId) {
        if (mClient != null) {
            mClient.deletePost(postId, new DeletePostCallback());
        }
    }

    public void searchPost(double latitude, double longitude, int maxDistance) {
        if (mClient != null) {
            mClient.searchPost(latitude, longitude, maxDistance,
                    new SearchPostCallback());
        }
    }

    public void createComment(String postId, String body) {
        if (mClient != null) {
            mClient.createComment(postId, body, new CreateCommentCallback());
        }
    }

    public void getEvent(String eventId) {
        if (mClient != null) {
            mClient.getEvent(eventId, new GetEventCallback());
        }
    }

    public void createEvent(String name, double longitude, double latitude,
            String startTime, String endTime, String desc) {
        if (mClient != null) {
            mClient.createEvent(name, longitude, latitude, startTime, endTime,
                    desc, new CreateEventCallback());
        }
    }

    public void updateEvent(String name, double longitude, double latitude,
            String startTime, String endTime, String desc) {
        if (mClient != null) {
            mClient.updateEvent(name, longitude, latitude, startTime, endTime,
                    desc, new UpdateEventCallback());
        }
    }

    public void deleteEvent(String eventId) {
        if (mClient != null) {
            mClient.deleteEvent(eventId, new DeleteEventCallback());
        }
    }

    public void updateView(int position) {

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
    }

    private class GetPostCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            String content = WebApiUtil.resToString(response);
            DebugLog.d(TAG, content);
            try {
                JSONObject object = new JSONObject(content);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class CreatePostCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }

    private class DeletePostCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }

    private class SearchPostCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }

    private class CreateCommentCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }

    private class GetEventCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }

    private class CreateEventCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }

    private class UpdateEventCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }

    private class DeleteEventCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }
}
