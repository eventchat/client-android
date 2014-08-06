package com.eventchat.manager;

import org.apache.http.HttpResponse;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.eventchat.entity.Event;
import com.eventchat.util.Constant;
import com.eventchat.util.DebugLog;
import com.eventchat.util.JsonParser;
import com.eventchat.util.WebApiUtil;
import com.eventchat.view.ProgressDialogView;
import com.eventchat.webapi.EventChatClient;
import com.eventchat.webapi.OnReceiveCallback;

public class EventManager implements IDispose {

    private static final String TAG = EventManager.class.getSimpleName();

    private static EventManager sInstance = new EventManager();

    private static Context sContext = null;

    private EventChatClient mClient = null;

    private ProgressDialogView mProgressDialog = null;

    private Event mCurrentEvent = null;

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

    public void getEvent(String eventId, Handler handler) {
        if (mClient != null) {
            mClient.getEvent(eventId, new GetEventCallback(handler));
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

    public void getAttendeeList(String eventId, Handler handler) {
        if (mClient != null) {
            mClient.getAttendeeList(eventId, new GetAttendeeListCallback(
                    handler));
        }
    }

    public void setCurrentEvent(Event event) {
        mCurrentEvent = event;
    }

    public Event getCurrentEvent() {
        return mCurrentEvent;
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
    }

    private class GetEventCallback implements OnReceiveCallback {

        private Handler mHandler = null;

        public GetEventCallback(Handler handler) {
            mHandler = handler;
        }

        @Override
        public void onReceive(HttpResponse response) {
            String res = WebApiUtil.resToString(response);
            DebugLog.d(TAG, res);
            Message msg = mHandler.obtainMessage(Constant.UI.UPDATE_EVENT_INFO,
                    res);
            mHandler.sendMessage(msg);
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
            String res = WebApiUtil.resToString(response);
            DebugLog.d(TAG, res);
            setCurrentEvent(JsonParser.parseEvent(res));
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

    private class GetAttendeeListCallback implements OnReceiveCallback {

        private Handler mHandler = null;

        public GetAttendeeListCallback(Handler handler) {
            mHandler = handler;
        }

        @Override
        public void onReceive(HttpResponse response) {
            String res = WebApiUtil.resToString(response);
            DebugLog.d(TAG, res);
            Message msg = mHandler.obtainMessage(
                    Constant.UI.UPDATE_EVENT_ATTENDEE, res);
            mHandler.sendMessage(msg);
        }
    }
}
