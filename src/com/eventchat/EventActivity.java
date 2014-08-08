package com.eventchat;

import java.io.Serializable;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.eventchat.entity.Event;
import com.eventchat.entity.User;
import com.eventchat.manager.EventManager;
import com.eventchat.manager.PostManager;
import com.eventchat.util.Constant;
import com.eventchat.util.DebugLog;
import com.eventchat.util.JsonParser;

public class EventActivity extends Activity {

    private static final String TAG = EventActivity.class.getSimpleName();

    private String mEventId = null;

    private TextView mEventName = null;

    private TextView mEventAddress = null;

    private TextView mEventTime = null;

    private TextView mEventOrganizer = null;

    private GridLayout mEventAttendees = null;

    private ProgressDialog mProgressDialog = null;

    private EventHandler mHandler = null;

    private List<User> mAttendeeList = null;

    private ActionBar mActionBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_layout);

        Intent intent = getIntent();
        mEventId = (String) intent.getExtras().getSerializable(
                Constant.Data.EVENT_DATA);

        mHandler = new EventHandler(Looper.getMainLooper());

        mEventName = (TextView) findViewById(R.id.event_name);
        mEventAddress = (TextView) findViewById(R.id.location_content);
        mEventTime = (TextView) findViewById(R.id.time_content);
        mEventOrganizer = (TextView) findViewById(R.id.organizer_content);
        mEventAttendees = (GridLayout) findViewById(R.id.attendee_list);

        mProgressDialog = showProgressDialog("", "");

        mActionBar = getActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        mActionBar.setBackgroundDrawable(getResources().getDrawable(
                R.color.theme));

        EventManager.getInstance(this).getEvent(mEventId, mHandler);
        EventManager.getInstance(this).getAttendeeList(mEventId, mHandler);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.compose:
            showComposeDialog();
            break;
        default:
            break;
        }
        return true;
    }

    public void updateEventInfo(Event event) {
        DebugLog.d(TAG, "updateEventInfo");
        mActionBar.setTitle(event.getName());
        mEventName.setText(event.getName());
        mEventAddress.setText(event.getAddress());
        mEventTime.setText(event.getStartTime());
        mEventOrganizer.setText(event.getOrganizer());
    }

    public void updateAttendeeList(final List<User> userList) {
        DebugLog.d(TAG, "updateAttendeeList");
        mAttendeeList = userList;
        LayoutInflater inflater = LayoutInflater.from(this);
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

                    Intent intent = new Intent(EventActivity.this,
                            ProfileActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
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
                Intent intent = new Intent(EventActivity.this,
                        AttendeeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.Data.ATTENDEE_DATA,
                        (Serializable) mAttendeeList);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private ProgressDialog showProgressDialog(String title, String message) {
        return ProgressDialog.show(this, "", "");
    }

    private void hideProgressDialog(ProgressDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private void showComposeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.compose_dialog, null);
        final EditText content = (EditText) view.findViewById(R.id.content);
        builder.setView(view)
                .setCancelable(false)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.post,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                    int which) {
                                PostManager.getInstance(EventActivity.this)
                                        .createPost(
                                                getResources().getString(
                                                        R.string.post_title),
                                                getResources().getString(
                                                        R.string.post_type),
                                                content.getEditableText()
                                                        .toString(), mEventId);
                            }
                        }).setTitle(R.string.post_title).create().show();
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
