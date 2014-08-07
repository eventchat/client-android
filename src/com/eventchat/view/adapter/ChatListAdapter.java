package com.eventchat.view.adapter;

import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eventchat.R;
import com.eventchat.entity.ChatMessage;
import com.eventchat.manager.ProfileManager;
import com.eventchat.util.DebugLog;

public class ChatListAdapter extends BaseAdapter {

    private static final String TAG = ChatListAdapter.class.getSimpleName();

    private Context mContext = null;

    private List<ChatMessage> mChatList = null;

    public ChatListAdapter(Context context, List<ChatMessage> chatList) {
        DebugLog.d(TAG, "ChatEntryAdapter");
        mContext = context;
        mChatList = chatList;
    }

    @Override
    public int getCount() {
        return mChatList.size();
    }

    @Override
    public Object getItem(int position) {
        return mChatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.chat_entry, null);
        }
        LinearLayout layout = (LinearLayout) convertView;
        TextView message = (TextView) convertView.findViewById(R.id.message);
        ChatMessage chat = mChatList.get(position);
        if (!isMyself(chat.getFrom().getId())) {
            layout.setGravity(Gravity.LEFT);
            message.setBackgroundResource(R.drawable.bubble_yellow);
        } else {
            layout.setGravity(Gravity.RIGHT);
            message.setBackgroundResource(R.drawable.bubble_green);
        }
        message.setText(chat.getMessage());
        return convertView;
    }

    private boolean isMyself(String to) {
        return to == ProfileManager.getInstance(mContext).getCurrentUser()
                .getId();
    }
}
