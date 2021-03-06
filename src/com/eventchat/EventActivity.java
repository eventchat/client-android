package com.eventchat;

import java.io.Serializable;
import java.util.ArrayList;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.eventchat.entity.Event;
import com.eventchat.entity.Post;
import com.eventchat.entity.User;
import com.eventchat.manager.EventManager;
import com.eventchat.manager.PostManager;
import com.eventchat.util.Constant;
import com.eventchat.util.DebugLog;
import com.eventchat.util.JsonParser;
import com.eventchat.view.adapter.AttendeeGridAdapter;
import com.eventchat.view.adapter.PostListAdapter;

public class EventActivity extends Activity {

    private static final String TAG = EventActivity.class.getSimpleName();

    private String mEventId = null;

    private TextView mEventName = null;

    private TextView mEventAddress = null;

    private TextView mEventTime = null;

    private TextView mEventOrganizer = null;

    private GridView mEventAttendees = null;

    private ListView mPostListView = null;

    private ProgressDialog mProgressDialog = null;

    private EventHandler mHandler = null;

    private List<User> mAttendeeList = null;

    private List<Post> mPostList = null;

    private ActionBar mActionBar = null;

    private PostListAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_layout);

        mPostList = new ArrayList<Post>();
        mAdapter = new PostListAdapter(this, mPostList);

        Intent intent = getIntent();
        mEventId = (String) intent.getExtras().getSerializable(
                Constant.Data.EVENT_DATA);

        mHandler = new EventHandler(Looper.getMainLooper());

        mEventName = (TextView) findViewById(R.id.event_name);
        mEventAddress = (TextView) findViewById(R.id.location);
        mEventTime = (TextView) findViewById(R.id.time);
        mEventOrganizer = (TextView) findViewById(R.id.organizer);
        mEventAttendees = (GridView) findViewById(R.id.attendee_list);
        mPostListView = (ListView) findViewById(R.id.post_list);
        mPostListView.setAdapter(mAdapter);

        mProgressDialog = showProgressDialog("", "");

        mActionBar = getActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        mActionBar.setBackgroundDrawable(getResources().getDrawable(
                R.color.theme));

        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);

        EventManager.getInstance(this).getEvent(mEventId, mHandler);
        EventManager.getInstance(this).getAttendeeList(mEventId, mHandler);
        PostManager.getInstance().getPostListByEventId(mEventId, mHandler);
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
        DebugLog.d(TAG, "updateAttendeeList size = " + userList.size());
        mAttendeeList = userList;
        mEventAttendees.setAdapter(new AttendeeGridAdapter(this, userList));
        mEventAttendees.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                if (position < Math.min(7, userList.size())) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constant.Data.PROFILE_DATA,
                            userList.get(position));

                    Intent intent = new Intent(EventActivity.this,
                            ProfileActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(EventActivity.this,
                            AttendeeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constant.Data.ATTENDEE_DATA,
                            (Serializable) mAttendeeList);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    public void updatePostList(List<Post> postList) {
        mPostList.clear();
        mPostList.addAll(postList);
        mAdapter.notifyDataSetChanged();
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
                                PostManager.getInstance().createPost(
                                        getResources().getString(
                                                R.string.post_title),
                                        getResources().getString(
                                                R.string.post_type),
                                        content.getEditableText().toString(),
                                        mEventId);
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
            case Constant.UI.UPDATE_POST_LIST:
                updatePostList(JsonParser.parsePostList((String) msg.obj));
                break;
            default:
                throw new IllegalArgumentException();
            }
            hideProgressDialog(mProgressDialog);
        }
    }
}
