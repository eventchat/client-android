package com.eventchat.view;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.eventchat.EventActivity;
import com.eventchat.R;
import com.eventchat.entity.Event;
import com.eventchat.manager.EventManager;
import com.eventchat.manager.ProfileManager;
import com.eventchat.util.Constant;
import com.eventchat.util.DebugLog;
import com.eventchat.util.JsonParser;
import com.eventchat.view.adapter.EventListAdapter;

public class MyEventsFragment extends Fragment {

    private static final String TAG = MyEventsFragment.class.getSimpleName();

    private MyEventsHandler mHandler = null;

    private ListView mEventListView = null;

    private List<Event> mEventList = null;

    private EventListAdapter mAdapter = null;

    public MyEventsFragment() {
        mEventList = new ArrayList<Event>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_events_fragment,
                container, false);

        mEventListView = (ListView) rootView.findViewById(R.id.event_list);
        mAdapter = new EventListAdapter(getActivity(), mEventList);
        mEventListView.setAdapter(mAdapter);
        mHandler = new MyEventsHandler(Looper.myLooper());
        String userId = ProfileManager.getInstance(getActivity())
                .getCurrentUser().getId();
        EventManager.getInstance(getActivity()).getEventListByUserId(userId,
                mHandler);
        mEventListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Intent intent = new Intent(getActivity(), EventActivity.class);
                Bundle bundle = new Bundle();
                Event event = mEventList.get(position);
                bundle.putSerializable(Constant.Data.EVENT_DATA, event.getId());
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
        return rootView;
    }

    private void updateEventList(List<Event> eventList) {
        if (eventList != null) {
            mEventList.clear();
            mEventList.addAll(eventList);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class MyEventsHandler extends Handler {

        public MyEventsHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            DebugLog.d(TAG, "handleMessage");
            switch (msg.what) {
            case Constant.UI.UPDATE_EVENT_LIST:
                updateEventList(JsonParser.parseEventList((String) msg.obj));
                break;
            default:
                break;
            }
        }
    }
}
