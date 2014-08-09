package com.eventchat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
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

    private BroadcastReceiver mReceiver = null;

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

        getActionBar().setTitle(mTargetUser.getName());

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
                if (mMessageText != null && mMessageText.getText().length() > 0) {
                    String message = mMessageText.getText().toString();
                    ChatManager.getInstance().sendChatMessage(
                            mTargetUser.getId(), message,
                            new ChatHandler(getMainLooper()));

                    TimeZone tz = TimeZone.getTimeZone("UTC");
                    DateFormat df = new SimpleDateFormat(
                            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
                    df.setTimeZone(tz);
                    String nowAsISO = df.format(new Date());

                    ChatMessage chat = EntityFactory.createChatMessage(
                            ProfileManager.getInstance(ChatActivity.this)
                                    .getCurrentUser(), mTargetUser, message,
                            nowAsISO);
                    ChatManager.getInstance().putChatMessage(
                            mTargetUser.getId(), chat);
                    mChatList.clear();
                    mChatList.addAll(ChatManager.getInstance()
                            .getChatMessageListByUser(mTargetUser));
                    mChatListAdapter.notifyDataSetChanged();
                    mMessageText.setText("");
                    hideKeyboard();
                }
            }
        });

        mMessageText.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard();
                }
            }
        });
        ChatHandler handler = new ChatHandler(getMainLooper());
        handler.sendEmptyMessageDelayed(0, 1000);
        registerReceiver(mReceiver = new IntentBroadcastReceiver(),
                new IntentFilter(Intent.ACTION_VIEW));
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mMessageText.getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        unregisterReceiver(mReceiver);
        finish();
    }

    public static final String UPDATE_CHAT_MESSAGE_ACTION = "update_chat_message_list";

    private class IntentBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(UPDATE_CHAT_MESSAGE_ACTION)) {

            }
            updateChatMessageList();
        }
    }

    public void updateChatMessageList() {
        mChatList.clear();
        mChatList.addAll(ChatManager.getInstance().getChatMessageListByUser(
                mTargetUser));
        mChatListAdapter.notifyDataSetChanged();
    }

    private class ChatHandler extends Handler {

        public ChatHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            updateChatMessageList();
            sendEmptyMessageDelayed(0, 10000);
        }
    }
}
