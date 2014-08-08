package com.eventchat;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.eventchat.entity.User;
import com.eventchat.util.Constant;
import com.eventchat.view.adapter.AttendeeListAdapter;

public class AttendeeActivity extends Activity {

    private static final String TAG = AttendeeActivity.class.getSimpleName();

    private List<User> mAttendeeList = null;

    private ListView mAttendeeListView = null;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendee_fragment);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mAttendeeList = (List<User>) bundle
                .getSerializable(Constant.Data.ATTENDEE_DATA);
        mAttendeeListView = (ListView) findViewById(R.id.attendee_list);
        mAttendeeListView.setAdapter(new AttendeeListAdapter(this,
                mAttendeeList));
        mAttendeeListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Intent intent = new Intent(AttendeeActivity.this,
                        ChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.Data.CHAT_DATA,
                        mAttendeeList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
