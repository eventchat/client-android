package com.eventchat;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.eventchat.entity.User;
import com.eventchat.util.Constant;

public class ProfileActivity extends Activity {

    private static final String TAG = ProfileActivity.class.getSimpleName();

    private ActionBar mActionBar = null;

    private TextView mDescTextView = null;

    private ImageView mAvatarImage = null;

    private User mProfileUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        mActionBar = getActionBar();
        mDescTextView = (TextView) findViewById(R.id.desc);
        mAvatarImage = (ImageView) findViewById(R.id.avatar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mProfileUser = (User) bundle
                .getSerializable(Constant.Data.PROFILE_DATA);

        mActionBar.setTitle(mProfileUser.getName());
        mActionBar.setBackgroundDrawable(getResources().getDrawable(
                R.color.theme));
        mActionBar.setDisplayHomeAsUpEnabled(true);

        mDescTextView.setText(mProfileUser.getInfo());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
