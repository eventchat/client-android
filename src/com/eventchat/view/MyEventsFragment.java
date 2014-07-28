package com.eventchat.view;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eventchat.R;
import com.eventchat.manager.EventManager;

public class MyEventsFragment extends Fragment {

    private static final String TAG = MyEventsFragment.class.getSimpleName();

    private MyEventsHandler mHandler = null;

    public MyEventsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_events_fragment,
                container, false);
        
        return rootView;
    }

    private class MyEventsHandler extends Handler {

        public MyEventsHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {

        }
    }
}
