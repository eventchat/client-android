package com.eventchat.view.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eventchat.R;
import com.eventchat.entity.Event;

public class EventPatternAdapter extends BaseAdapter {

    private List<Event> mEventList = null;

    private LayoutInflater mInflater = null;

    public EventPatternAdapter(LayoutInflater inflater, List<Event> eventList) {
        mInflater = inflater;
        mEventList = eventList;
    }

    @Override
    public int getCount() {
        return mEventList.size();
    }

    @Override
    public Object getItem(int position) {
        return mEventList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater
                    .inflate(R.layout.join_pattern_entry, parent, false);
        }
        TextView pattern = (TextView) convertView.findViewById(R.id.event_name);
        pattern.setText(mEventList.get(position).getName());
        return convertView;
    }
}
