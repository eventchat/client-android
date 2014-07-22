package com.eventchat.view;

import com.eventchat.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyEventsTabFragment extends Fragment {

    private static final String TAG = MyEventsTabFragment.class.getSimpleName();

    public MyEventsTabFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_events_fragment,
                container, false);
        return rootView;
    }
}
