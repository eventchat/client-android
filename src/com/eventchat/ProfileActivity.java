package com.eventchat;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.eventchat.entity.Post;
import com.eventchat.entity.User;
import com.eventchat.manager.PostManager;
import com.eventchat.util.Constant;
import com.eventchat.util.JsonParser;
import com.eventchat.view.adapter.PostListAdapter;

public class ProfileActivity extends Activity {

    private static final String TAG = ProfileActivity.class.getSimpleName();

    private ActionBar mActionBar = null;

    private TextView mDescTextView = null;

    private ImageView mAvatarImage = null;

    private List<Post> mPostList = null;

    private PostListAdapter mAdapter = null;

    private ListView mPostListView = null;

    private ProfileHandler mHandler = null;

    private User mProfileUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_fragment);
        mActionBar = getActionBar();
        mDescTextView = (TextView) findViewById(R.id.desc);
        mAvatarImage = (ImageView) findViewById(R.id.avatar);
        mPostListView = (ListView) findViewById(R.id.post_list);

        mHandler = new ProfileHandler(Looper.getMainLooper());
        mPostList = new ArrayList<Post>();
        mAdapter = new PostListAdapter(this, mPostList);
        mPostListView.setAdapter(mAdapter);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mProfileUser = (User) bundle
                .getSerializable(Constant.Data.PROFILE_DATA);

        mActionBar.setTitle(mProfileUser.getName());
        mActionBar.setBackgroundDrawable(getResources().getDrawable(
                R.color.theme));
        mActionBar.setDisplayHomeAsUpEnabled(true);

        mDescTextView.setText(mProfileUser.getInfo());

        PostManager.getInstance().getPostListByUserId(mProfileUser.getId(),
                mHandler);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void updatePostList(List<Post> postList) {
        mPostList.clear();
        mPostList.addAll(postList);
        mAdapter.notifyDataSetChanged();
    }

    private class ProfileHandler extends Handler {

        public ProfileHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case Constant.UI.UPDATE_POST_LIST:
                updatePostList(JsonParser.parsePostList((String) msg.obj));
                break;
            default:
                throw new IllegalArgumentException();
            }
        }
    }
}
