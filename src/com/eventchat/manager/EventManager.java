package com.eventchat.manager;

import org.apache.http.HttpResponse;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;

import com.eventchat.R;
import com.eventchat.entity.Event;
import com.eventchat.util.DebugLog;
import com.eventchat.util.JsonParser;
import com.eventchat.util.WebApiUtil;
import com.eventchat.view.EventFragment;
import com.eventchat.view.ProgressDialogView;
import com.eventchat.webapi.EventChatClient;
import com.eventchat.webapi.OnReceiveCallback;

public class EventManager implements IDispose {

    private static final String TAG = EventManager.class.getSimpleName();

    private static EventManager sInstance = new EventManager();

    private static Context sContext = null;

    private EventChatClient mClient = null;

    private ProgressDialogView mProgressDialog = null;

    private EventManager() {
        DebugLog.d(TAG, "EventManager");
        mClient = EventChatClient.getInstance();
        mProgressDialog = new ProgressDialogView();
    }

    public static EventManager getInstance(Context context) {
        DebugLog.d(TAG, "getInstance");
        sContext = context;
        return sInstance;
    }

    public void joinEvent(String eventId) {
        if (mClient != null) {
            mProgressDialog.show(sContext);
            mClient.joinEvent(eventId, new JoinEventCallback());
        }
    }

    public void getEvent(String eventId) {
        if (mClient != null) {
            mProgressDialog.show(sContext);
            mClient.getEvent(eventId, new GetEventCallback());
        }
    }

    public void createEvent(String name, double longitude, double latitude,
            String address, String startTime, String endTime, String desc) {
        if (mClient != null) {
            mClient.createEvent(name, longitude, latitude, address, startTime,
                    endTime, desc, new CreateEventCallback());
        }
    }

    public void deleteEvent(String eventId) {
        if (mClient != null) {
            mClient.deleteEvent(eventId, new DeleteEventCallback());
        }
    }

    public void updateEvent(String name, double longitude, double latitude,
            String address, String startTime, String endTime, String desc) {
        if (mClient != null) {
            mClient.updateEvent(name, longitude, latitude, address, startTime,
                    endTime, desc, new UpdateEventCallback());
        }
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
    }

    private class GetEventCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            String res = WebApiUtil.resToString(response);
            DebugLog.d(TAG, res);
            EventFragment fragment = new EventFragment();
            FragmentTransaction transaction = ((Activity) sContext)
                    .getFragmentManager().beginTransaction();
            transaction.replace(R.id.tab_join, fragment);
            transaction.addToBackStack(null);
            transaction.commit();

            mProgressDialog.dismiss();
        }
    }

    private class CreateEventCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }

    private class JoinEventCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }

    private class UpdateEventCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }

    private class DeleteEventCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }
}
