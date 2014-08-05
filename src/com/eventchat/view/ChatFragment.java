package com.eventchat.view;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eventchat.R;
import com.eventchat.manager.ChatManager;

public class ChatFragment extends Fragment {

    private static final String TAG = ChatFragment.class.getSimpleName();

    private ChatHandler mHandler = null;

    public ChatFragment() {
        mHandler = new ChatHandler(Looper.getMainLooper());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chat_fragment, container,
                false);
        ChatManager.getInstance().getChatMessage(mHandler);
        return rootView;
    }

    private class ChatHandler extends Handler {

        public ChatHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}
