package com.eventchat.view;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eventchat.R;
import com.eventchat.entity.Event;
import com.eventchat.entity.User;
import com.eventchat.util.DebugLog;

public class EventFragment extends Fragment {

    private static final String TAG = EventFragment.class.getSimpleName();

    private TextView mEventName = null;

    private TextView mEventAddress = null;

    private TextView mEventTime = null;

    private TextView mEventOrganizer = null;

    public EventFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        DebugLog.d(TAG, "onCreateView");
        DebugLog.d(TAG, "container = " + container);
        View rootView = inflater.inflate(R.layout.event_fragment, container,
                false);
        mEventName = (TextView) rootView.findViewById(R.id.event_name);
        mEventAddress = (TextView) rootView.findViewById(R.id.location_content);
        mEventTime = (TextView) rootView.findViewById(R.id.time_content);
        mEventOrganizer = (TextView) rootView
                .findViewById(R.id.organizer_content);

        return rootView;
    }

    public void updateEventInfo(Event event) {
        mEventName.setText(event.getName());
        mEventAddress.setText(event.getAddress());
        mEventTime.setText(event.getStartTime());
        mEventOrganizer.setText(event.getOrganizer());
    }

    public void updateAttendeeList(List<User> userList) {

    }
}
