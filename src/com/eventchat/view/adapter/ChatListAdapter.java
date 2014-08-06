package com.eventchat.view.adapter;

import java.util.List;

import com.eventchat.R;
import com.eventchat.entity.Chat;
import com.eventchat.manager.ProfileManager;
import com.eventchat.util.DebugLog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChatListAdapter extends BaseAdapter {

    private static final String TAG = ChatListAdapter.class.getSimpleName();

    private Context mContext = null;

    private List<Chat> mChatList = null;

    public ChatListAdapter(Context context, List<Chat> chatList) {
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
        Chat chat = mChatList.get(position);
        if (isMyself(chat.getTo())) {
            layout.setGravity(Gravity.LEFT);
        } else {
            layout.setGravity(Gravity.RIGHT);
        }
        message.setText(chat.getMessage());
        return convertView;
    }

    private boolean isMyself(String to) {
        return to == ProfileManager.getInstance(mContext).getCurrentUser()
                .getId();
    }
}
