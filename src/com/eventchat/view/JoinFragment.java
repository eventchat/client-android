package com.eventchat.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.eventchat.EventActivity;
import com.eventchat.R;
import com.eventchat.entity.EntityFactory;
import com.eventchat.entity.Event;
import com.eventchat.util.Constant;
import com.eventchat.util.DebugLog;
import com.eventchat.view.adapter.EventPatternAdapter;

public class JoinFragment extends Fragment {

    private static final String TAG = JoinFragment.class.getSimpleName();

    private static final String EVENT_ID = "53d6d749da0e0f0200e69de7";

    private static final String EVENT_PATTERN = "2143";

    private static final int DELAYED_TIME = 3000;

    private StringBuffer mEventPattern = null;

    private JoinHandler mHandler = null;

    public JoinFragment() {
        mEventPattern = new StringBuffer();
        mHandler = new JoinHandler();
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
        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                DebugLog.d(TAG, "onItemClick");
                if (mEventPattern != null) {
                    mEventPattern.append(position + 1);
                    DebugLog.d(TAG, mEventPattern.toString());
                    if (mEventPattern.toString().equals(EVENT_PATTERN)) {
                        Intent intent = new Intent(getActivity(),
                                EventActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Constant.Data.EVENT_DATA,
                                EVENT_ID);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
                mHandler.sendEmptyMessageDelayed(0, DELAYED_TIME);
            }
        });

        return rootView;
    }

    private class JoinHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            if (mEventPattern != null) {
                mEventPattern = new StringBuffer();
            }
        }
    }
}
