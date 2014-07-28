package com.eventchat.view.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eventchat.R;
import com.eventchat.entity.User;

public class AttendeeListAdapter extends BaseAdapter {

    private Context mContext = null;

    private List<User> mUserList = null;

    public AttendeeListAdapter(Context context, List<User> userList) {
        mContext = context;
        mUserList = userList;
    }

    @Override
    public int getCount() {
        return mUserList.size();
    }

    @Override
    public Object getItem(int position) {
        return mUserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.attendee_entry, null);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(((User) getItem(position)).getName());
        return convertView;
    }
}
