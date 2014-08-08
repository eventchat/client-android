package com.eventchat.manager;

import org.apache.http.HttpResponse;

import android.os.Handler;
import android.os.Message;

import com.eventchat.util.Constant;
import com.eventchat.util.DebugLog;
import com.eventchat.util.WebApiUtil;
import com.eventchat.webapi.EventChatClient;
import com.eventchat.webapi.OnReceiveCallback;

public final class PostManager implements IDispose {

    private static final String TAG = PostManager.class.getSimpleName();

    private static PostManager sInstance = new PostManager();

    private EventChatClient mClient = null;

    private PostManager() {
        DebugLog.d(TAG, "PostManager");
        mClient = EventChatClient.getInstance();
    }

    public static PostManager getInstance() {
        DebugLog.d(TAG, "getInstance");
        return sInstance;
    }

    public void getPost(String postId) {
        if (mClient != null) {
            mClient.getPost(postId, new GetPostCallback());
        }
    }

    public void getPostListByEventId(String eventId, Handler handler) {
        if (mClient != null) {
            mClient.getPostListByEventId(eventId, new GetPostListCallback(
                    handler));
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

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
    }

    private class GetPostCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            String content = WebApiUtil.resToString(response);
            DebugLog.d(TAG, content);
            // try {
            // JSONObject object = new JSONObject(content);
            // } catch (JSONException e) {
            // e.printStackTrace();
            // }
        }
    }

    private class CreatePostCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }

    private class GetPostListCallback implements OnReceiveCallback {

        private Handler mHandler = null;

        public GetPostListCallback(Handler handler) {
            mHandler = handler;
        }

        @Override
        public void onReceive(HttpResponse response) {
            if (WebApiUtil.isSuccess(response)) {
                String res = WebApiUtil.resToString(response);
                DebugLog.d(TAG, res);
                Message msg = mHandler.obtainMessage(
                        Constant.UI.UPDATE_POST_LIST, res);
                mHandler.sendMessage(msg);
            }
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
}
