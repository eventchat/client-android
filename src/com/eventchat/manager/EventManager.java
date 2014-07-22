package com.eventchat.manager;

import org.apache.http.HttpResponse;

import android.content.Context;
import android.util.LruCache;

import com.eventchat.util.DebugLog;
import com.eventchat.util.WebApiUtil;
import com.eventchat.webapi.EventChatClient;
import com.eventchat.webapi.OnReceiveCallback;

public class EventManager implements IDispose {

    private static final String TAG = EventManager.class.getSimpleName();

    private static EventManager sInstance = new EventManager();

    private static Context sContext = null;

    private EventChatClient mClient = null;

    private EventManager() {
        DebugLog.d(TAG, "EventManager");
        mClient = EventChatClient.getInstance();
    }

    public static EventManager getInstance(Context context) {
        DebugLog.d(TAG, "getInstance");
        sContext = context;
        return sInstance;
    }

    public void getEvent(String eventId) {
        if (mClient != null) {
            mClient.getEvent(eventId, new GetEventCallback());
        }
    }

    public void createEvent(String name, double longitude, double latitude,
            String startTime, String endTime, String desc) {
        if (mClient != null) {
            mClient.createEvent(name, longitude, latitude, startTime, endTime,
                    desc, new CreateEventCallback());
        }
    }

    public void deleteEvent(String eventId) {
        if (mClient != null) {
            mClient.deleteEvent(eventId, new DeleteEventCallback());
        }
    }

    public void updateEvent(String name, double longitude, double latitude,
            String startTime, String endTime, String desc) {
        if (mClient != null) {
            mClient.updateEvent(name, longitude, latitude, startTime, endTime,
                    desc, new UpdateEventCallback());
        }
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
    }

    private class GetEventCallback implements OnReceiveCallback {

        @Override
        public void onReceive(HttpResponse response) {
            DebugLog.d(TAG, WebApiUtil.resToString(response));
        }
    }

    private class CreateEventCallback implements OnReceiveCallback {

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
