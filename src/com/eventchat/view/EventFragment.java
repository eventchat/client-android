package com.eventchat.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eventchat.R;
import com.eventchat.util.DebugLog;

public class EventFragment extends Fragment {

    private static final String TAG = EventFragment.class.getSimpleName();

    public EventFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        DebugLog.d(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.event_fragment, container,
                false);
        return rootView;
    }
}
