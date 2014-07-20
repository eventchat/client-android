package com.eventchat.view;

import com.eventchat.util.DebugLog;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EventFragment extends Fragment {

    private static final String TAG = EventFragment.class.getSimpleName();

    public EventFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        DebugLog.d(TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
