package com.eventchat.view.adapter;

import java.util.List;
import java.util.zip.Inflater;

import com.eventchat.R;
import com.eventchat.entity.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AttendeeGridAdapter extends BaseAdapter {

    private static final int GRID_COLUMN = 7;

    private Context mContext = null;

    private List<User> mAttendeeList = null;

    public AttendeeGridAdapter(Context context, List<User> attendeeList) {
        mContext = context;
        mAttendeeList = attendeeList;
    }

    @Override
    public int getCount() {
        return Math.min(mAttendeeList.size() + 1, GRID_COLUMN);
    }

    @Override
    public Object getItem(int position) {
        return mAttendeeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position == getCount() - 1) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.attendee_more, null);
        } else {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.attendee, null);
            }
        }
        return convertView;
    }
}
