package com.eventchat.view.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eventchat.R;
import com.eventchat.entity.Event;
import com.eventchat.util.DebugLog;

public class EventListAdapter extends BaseAdapter {

    private Context mContext = null;

    private List<Event> mEventList = null;

    public EventListAdapter(Context context, List<Event> events) {
        mContext = context;
        mEventList = events;
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
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.event_entry, null);
        }
        Event event = mEventList.get(position);
        TextView eventNameText = (TextView) convertView
                .findViewById(R.id.event_name);
        TextView locationText = (TextView) convertView
                .findViewById(R.id.location);
        TextView timeText = (TextView) convertView.findViewById(R.id.time);
        eventNameText.setText(event.getName());
        locationText.setText(event.getAddress());
        timeText.setText(event.getStartTime());
        DebugLog.d("EventListAdapter", "==========================");
        DebugLog.d("EventListAdapter", event.getStartTime());
        DebugLog.d("EventListAdapter", "==========================");
        return convertView;
    }
}
