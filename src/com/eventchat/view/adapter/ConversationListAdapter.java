package com.eventchat.view.adapter;

import java.util.List;

import com.eventchat.R;
import com.eventchat.entity.ChatMessage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ConversationListAdapter extends BaseAdapter {

    private Context mContext = null;

    private List<ChatMessage> mConversationList = null;

    public ConversationListAdapter(Context context, List<ChatMessage> list) {
        mContext = context;
        mConversationList = list;
    }

    @Override
    public int getCount() {
        return mConversationList.size();
    }

    @Override
    public Object getItem(int position) {
        return mConversationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.conversation_entry, null);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView time = (TextView) convertView.findViewById(R.id.time);
        TextView content = (TextView) convertView.findViewById(R.id.content);
        ChatMessage message = mConversationList.get(position);
        name.setText(message.getFrom().getName());
        time.setText(message.getCreatedTime());
        content.setText(message.getMessage());
        return convertView;
    }
}
