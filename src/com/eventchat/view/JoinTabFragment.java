package com.eventchat.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.eventchat.R;
import com.eventchat.entity.EntityFactory;
import com.eventchat.entity.Event;

public class JoinTabFragment extends Fragment {

    private static final String TAG = JoinTabFragment.class.getSimpleName();

    public JoinTabFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.join_fragment, container,
                false);
        GridView gridView = (GridView) rootView.findViewById(R.id.pattern);
        List<Event> eventList = new ArrayList<Event>();
        eventList.add(EntityFactory.createEvent("1"));
        eventList.add(EntityFactory.createEvent("2"));
        eventList.add(EntityFactory.createEvent("3"));
        eventList.add(EntityFactory.createEvent("4"));
        gridView.setAdapter(new EventPatternAdapter(inflater, eventList));
        return rootView;
    }
}
