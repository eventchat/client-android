package com.eventchat.view;

import java.io.Serializable;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.eventchat.ProfileActivity;
import com.eventchat.R;
import com.eventchat.entity.Event;
import com.eventchat.entity.User;
import com.eventchat.manager.EventManager;
import com.eventchat.util.Constant;
import com.eventchat.util.DebugLog;
import com.eventchat.util.JsonParser;

public class EventFragment extends Fragment {

    private static final String TAG = EventFragment.class.getSimpleName();

    private TextView mEventName = null;

    private TextView mEventAddress = null;

    private TextView mEventTime = null;

    private TextView mEventOrganizer = null;

    private GridLayout mEventAttendees = null;

    private ProgressDialog mProgressDialog = null;

    private EventHandler mHandler = null;

    private static final String EVENT_ID = "53d6d749da0e0f0200e69de7";

    public EventFragment() {
        mHandler = new EventHandler(Looper.getMainLooper());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        DebugLog.d(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.event_fragment, container,
                false);
        mEventName = (TextView) rootView.findViewById(R.id.event_name);
        mEventAddress = (TextView) rootView.findViewById(R.id.location_content);
        mEventTime = (TextView) rootView.findViewById(R.id.time_content);
        mEventOrganizer = (TextView) rootView
                .findViewById(R.id.organizer_content);
        mEventAttendees = (GridLayout) rootView
                .findViewById(R.id.attendee_list);

        mProgressDialog = showProgressDialog("", "");

        EventManager.getInstance(getActivity()).getEvent(EVENT_ID, mHandler);

        EventManager.getInstance(getActivity()).getAttendeeList(EVENT_ID,
                mHandler);

        return rootView;
    }

    public void updateEventInfo(Event event) {
        DebugLog.d(TAG, "updateEventInfo");
        mEventName.setText(event.getName());
        mEventAddress.setText(event.getAddress());
        mEventTime.setText(event.getStartTime());
        mEventOrganizer.setText(event.getOrganizer());
    }

    public void updateAttendeeList(final List<User> userList) {
        DebugLog.d(TAG, "updateAttendeeList");
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (int i = 0; i < userList.size(); ++i) {
            final View view = inflater.inflate(R.layout.attendee, null);
            final ImageView avatar = (ImageView) view.findViewById(R.id.avatar);
            avatar.setContentDescription("" + i);
            avatar.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    DebugLog.d(TAG, "onClick " + avatar.getContentDescription());
                    int index = Integer.valueOf(avatar.getContentDescription()
                            .toString());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constant.Data.PROFILE_DATA,
                            userList.get(index));

                    Intent intent = new Intent(getActivity(),
                            ProfileActivity.class);
                    intent.putExtras(bundle);
                    getActivity().startActivity(intent);
                }
            });
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            mEventAttendees.addView(view, params);
        }
        final View view = inflater.inflate(R.layout.attendee, null);
        final ImageView avatar = (ImageView) view.findViewById(R.id.avatar);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        mEventAttendees.addView(view, params);
        avatar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                AttendeeFragment fragment = new AttendeeFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(AttendeeFragment.ATTENDEE_LIST,
                        (Serializable) userList);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager()
                        .beginTransaction().replace(R.id.tab_join, fragment);
                transaction.commit();
            }
        });
    }

    private ProgressDialog showProgressDialog(String title, String message) {
        return ProgressDialog.show(getActivity(), "", "");
    }

    private void hideProgressDialog(ProgressDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private class EventHandler extends Handler {

        public EventHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case Constant.UI.UPDATE_EVENT_INFO:
                updateEventInfo(JsonParser.parseEvent((String) msg.obj));
                break;
            case Constant.UI.UPDATE_EVENT_ATTENDEE:
                updateAttendeeList(JsonParser.parseUserList((String) msg.obj));
                break;
            default:
                throw new IllegalArgumentException();
            }
            hideProgressDialog(mProgressDialog);
        }
    }
}
