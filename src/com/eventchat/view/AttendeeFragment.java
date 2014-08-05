package com.eventchat.view;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.eventchat.MainActivity;
import com.eventchat.R;
import com.eventchat.entity.User;
import com.eventchat.util.Constant;
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
        attendeeListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                MainActivity activity = (MainActivity) getActivity();
                activity.setCurrentTab(Constant.Tag.TAB_CHAT);
                
            }
        });
        return rootView;
    }
}
