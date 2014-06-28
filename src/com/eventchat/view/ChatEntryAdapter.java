package com.eventchat.view;

import java.util.List;

import com.eventchat.R;
import com.eventchat.entity.Chat;
import com.eventchat.util.DebugLog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ChatEntryAdapter extends BaseAdapter {

    private static final String TAG = ChatEntryAdapter.class.getSimpleName();

    private Context mContext = null;

    private List<Chat> mChatList = null;

    public ChatEntryAdapter(Context context, List<Chat> chatList) {
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
        if (convertView != null) {
            // TODO
        } else {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.chat_entry, parent);
        }
        return convertView;
    }
}
