package com.eventchat.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;

import android.os.Handler;
import android.os.Message;

import com.eventchat.entity.ChatMessage;
import com.eventchat.entity.User;
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

    private Map<String, List<ChatMessage>> mChatMap = null;

    private ChatManager() {
        DebugLog.d(TAG, "ChatManager");
        mClient = EventChatClient.getInstance();
        mIsPolling = true;
        mChatMap = new HashMap<String, List<ChatMessage>>();
    }

    public static ChatManager getInstance() {
        return sInstance;
    }

    public void sendChatMessage(String to, String message, Handler handler) {
        if (mClient != null) {
            mClient.sendChatMessage(to, message, new SendChatMessageCallback(
                    handler));
        }
    }

    public void getChatMessage(Handler handler) {
        DebugLog.d(TAG, "getChatMessage");
        if (mClient != null) {
            mClient.getChatMessage(new GetChatMessageCallback(handler));
        }
    }

    public void putChatMessage(String userId, ChatMessage message) {
        if (userId != null && message != null) {
            if (mChatMap.containsKey(userId)) {
                mChatMap.get(userId).add(message);
            } else {
                List<ChatMessage> list = new ArrayList<ChatMessage>();
                list.add(message);
                mChatMap.put(userId, list);
            }
        }
    }

    public void putChatMessage(List<ChatMessage> messageList) {
        for (ChatMessage message : messageList) {
            putChatMessage(message.getFrom().getId(), message);
        }
    }

    public List<ChatMessage> getChatMessageListByUser(User user) {
        if (user != null) {
            return mChatMap.get(user.getId());
        }
        return null;
    }

    public List<ChatMessage> getConversationList() {
        List<ChatMessage> conversationList = new ArrayList<ChatMessage>();
        for (String key : mChatMap.keySet()) {
            List<ChatMessage> list = mChatMap.get(key);
            DebugLog.d(TAG, "target user id = " + key);
            if (list.size() > 0) {
                conversationList.add(list.get(list.size() - 1));
            }
        }
        return conversationList;
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
