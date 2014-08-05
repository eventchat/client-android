package com.eventchat.manager;

import org.apache.http.HttpResponse;

import android.os.Handler;
import android.os.Message;

import com.eventchat.util.Constant;
import com.eventchat.util.DebugLog;
import com.eventchat.util.WebApiUtil;
import com.eventchat.webapi.EventChatClient;
import com.eventchat.webapi.OnReceiveCallback;

public final class ChatManager implements IDispose {

    private static final String TAG = ChatManager.class.getSimpleName();

    private static ChatManager sInstance = new ChatManager();

    private EventChatClient mClient = null;

    private boolean mIsPolling = false;

    private ChatManager() {
        DebugLog.d(TAG, "ChatManager");
        mClient = EventChatClient.getInstance();
        mIsPolling = false;
    }

    public static ChatManager getInstance() {
        DebugLog.d(TAG, "getInstance");
        return sInstance;
    }

    public void sendChatMessage(String to, String message, Handler handler) {
        if (mClient != null) {
            mClient.sendChatMessage(to, message, new SendChatMessageCallback(
                    handler));
        }
    }

    public void getChatMessage(Handler handler) {
        if (mClient != null) {
            mClient.getChatMessage(new GetChatMessageCallback(handler));
        }
    }

    @Override
    public void dispose() {
        mIsPolling = false;
    }

    private class SendChatMessageCallback implements OnReceiveCallback {
        private Handler mHandler = null;

        public SendChatMessageCallback(Handler handler) {
            mHandler = handler;
        }

        @Override
        public void onReceive(HttpResponse response) {
            if (WebApiUtil.isSuccess(response)) {
                String res = WebApiUtil.resToString(response);
                DebugLog.d(TAG, res);
                Message msg = mHandler.obtainMessage(
                        Constant.UI.UPDATE_CHAT_MESSAGE, res);
                mHandler.sendMessage(msg);
            }
        }
    }

    private class GetChatMessageCallback implements OnReceiveCallback {

        private Handler mHandler = null;

        public GetChatMessageCallback(Handler handler) {
            mHandler = handler;
        }

        @Override
        public void onReceive(HttpResponse response) {
            String res = WebApiUtil.resToString(response);
            DebugLog.d(TAG, res);
            if (WebApiUtil.isSuccess(response)) {
                Message msg = mHandler.obtainMessage(
                        Constant.UI.UPDATE_CHAT_MESSAGE, res);
                mHandler.sendMessage(msg);
            }
            if (mIsPolling) {
                getChatMessage(mHandler);
            }
        }
    }
}
