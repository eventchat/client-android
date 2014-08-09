package com.eventchat.view.adapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eventchat.R;
import com.eventchat.entity.ChatMessage;
import com.eventchat.manager.ProfileManager;

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

        if (!ProfileManager.getInstance(mContext).isMyself(message.getFrom())) {
            name.setText(message.getFrom().getName());
        } else {
            name.setText(message.getTo().getName());
        }
        DateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        Date date = null;
        try {
            date = format.parse(message.getCreatedTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        format = new SimpleDateFormat("hh:mm aa", Locale.US);
        time.setText(format.format(date));
        content.setText(message.getMessage());
        return convertView;
    }
}
