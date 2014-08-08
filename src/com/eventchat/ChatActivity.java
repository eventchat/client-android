package com.eventchat;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.eventchat.entity.ChatMessage;
import com.eventchat.entity.EntityFactory;
import com.eventchat.entity.User;
import com.eventchat.manager.ChatManager;
import com.eventchat.manager.ProfileManager;
import com.eventchat.util.Constant;
import com.eventchat.view.adapter.ChatListAdapter;

public class ChatActivity extends Activity {

    private ListView mChatListView = null;

    private Button mSendButton = null;

    private EditText mMessageText = null;

    private List<ChatMessage> mChatList = null;

    private ChatListAdapter mChatListAdapter = null;

    private User mTargetUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);

        getActionBar().setBackgroundDrawable(
                getResources().getDrawable(R.color.theme));
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mTargetUser = (User) bundle.getSerializable(Constant.Data.CHAT_DATA);

        mChatList = new ArrayList<ChatMessage>();
        List<ChatMessage> list = ChatManager.getInstance()
                .getChatMessageListByUser(mTargetUser);
        if (list != null) {
            mChatList.addAll(list);
        }
        mChatListAdapter = new ChatListAdapter(this, mChatList);
        mChatListView = (ListView) findViewById(R.id.chat_list);
        mMessageText = (EditText) findViewById(R.id.message_box);
        mSendButton = (Button) findViewById(R.id.send_button);
        mChatListView.setAdapter(mChatListAdapter);
        mChatListView.setClickable(false);
        mChatListView.setDivider(null);
        mSendButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mMessageText != null) {
                    String message = mMessageText.getText().toString();
                    ChatManager.getInstance().sendChatMessage(
                            mTargetUser.getId(), message,
                            new ChatHandler(getMainLooper()));
                    ChatMessage chat = EntityFactory.createChatMessage(
                            ProfileManager.getInstance(ChatActivity.this)
                                    .getCurrentUser(), mTargetUser, message,
                            null);
                    ChatManager.getInstance().putChatMessage(
                            mTargetUser.getId(), chat);
                    mChatList.add(chat);
                    mChatListAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void updateChatMessageList() {

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
