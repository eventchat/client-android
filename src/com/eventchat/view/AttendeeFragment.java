package com.eventchat.view;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.eventchat.R;
import com.eventchat.entity.User;
import com.eventchat.util.DebugLog;
import com.eventchat.view.adapter.AttendeeListAdapter;

public class AttendeeFragment extends Fragment {

    public static final String ATTENDEE_LIST = "attendee_list";

    private static final String TAG = AttendeeFragment.class.getSimpleName();

    private List<User> mAttendeeList = null;

    public AttendeeFragment() {

    }

    @SuppressWarnings("unchecked")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        DebugLog.d(TAG, "onCreateView");
        mAttendeeList = (List<User>) getArguments().get(ATTENDEE_LIST);
        View rootView = inflater.inflate(R.layout.attendee_fragment, container,
                false);
        ListView attendeeListView = (ListView) rootView
                .findViewById(R.id.attendee_list);
        attendeeListView.setAdapter(new AttendeeListAdapter(getActivity(),
                mAttendeeList));
        return rootView;
    }
}
