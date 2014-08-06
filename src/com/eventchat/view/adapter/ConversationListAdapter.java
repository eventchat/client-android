package com.eventchat.view.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ConversationListAdapter extends BaseAdapter {

    private Context mContext = null;

    private List<String> mConversationList = null;

    public ConversationListAdapter(Context context, List<String> list) {
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
            // TODO
        }
        return convertView;
    }
}
