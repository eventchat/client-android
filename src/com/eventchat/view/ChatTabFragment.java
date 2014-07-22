package com.eventchat.view;

import com.eventchat.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChatTabFragment extends Fragment {

    private static final String TAG = ChatTabFragment.class.getSimpleName();

    public ChatTabFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chat_fragment, container,
                false);
        return rootView;
    }
}
