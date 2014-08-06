package com.eventchat;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.eventchat.entity.Chat;
import com.eventchat.entity.EntityFactory;
import com.eventchat.manager.ChatManager;
import com.eventchat.view.adapter.ChatListAdapter;

public class ChatActivity extends Activity {

    private ListView mChatListView = null;

    private Button mSendButton = null;

    private EditText mMessageText = null;

    private List<Chat> mChatList = null;

    private ChatListAdapter mChatListAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);

        mChatList = new ArrayList<Chat>();
        mChatListAdapter = new ChatListAdapter(this, mChatList);
        mChatListView = (ListView) findViewById(R.id.chat_list);
        mMessageText = (EditText) findViewById(R.id.message_box);
        mSendButton = (Button) findViewById(R.id.send_button);
        mChatListView.setAdapter(mChatListAdapter);
        mSendButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mMessageText != null) {
                    String message = mMessageText.getText().toString();
                    ChatManager.getInstance().sendChatMessage(
                            "53d6d158da0e0f0200e69de1", message,
                            new ChatHandler(getMainLooper()));
                    Chat chat = EntityFactory.createChat(
                            "53d6d158da0e0f0200e69de1", message);
                    mChatList.add(chat);
                    mChatListAdapter.notifyDataSetChanged();
                }
            }
        });

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
