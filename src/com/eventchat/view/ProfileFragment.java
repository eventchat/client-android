package com.eventchat.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.eventchat.R;
import com.eventchat.entity.Post;
import com.eventchat.entity.User;
import com.eventchat.manager.PostManager;
import com.eventchat.manager.ProfileManager;
import com.eventchat.util.Constant;
import com.eventchat.util.JsonParser;
import com.eventchat.view.adapter.PostListAdapter;

public class ProfileFragment extends Fragment {

    private static final String TAG = ProfileFragment.class.getSimpleName();

    private User mUser = null;

    private TextView mDesc = null;

    private List<Post> mPostList = null;

    private PostListAdapter mAdapter = null;

    private ListView mPostListView = null;

    private PostHandler mHandler = null;

    public ProfileFragment() {
        mPostList = new ArrayList<Post>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_fragment, container,
                false);
        mDesc = (TextView) rootView.findViewById(R.id.desc);
        mPostListView = (ListView) rootView.findViewById(R.id.post_list);
        mHandler = new PostHandler(Looper.getMainLooper());

        Bundle bundle = getArguments();
        if (bundle != null) {
            mUser = (User) bundle.get(Constant.Data.PROFILE_DATA);
        }
        if (mUser == null) {
            mUser = ProfileManager.getInstance(getActivity()).getCurrentUser();
        }
        mDesc.setText(mUser.getInfo());

        mAdapter = new PostListAdapter(getActivity(), mPostList);
        mPostListView.setAdapter(mAdapter);

        PostManager.getInstance().getPostListByUserId(mUser.getId(), mHandler);

        return rootView;
    }

    private void updatePostList(List<Post> postList) {
        mPostList.clear();
        mPostList.addAll(postList);
        mAdapter.notifyDataSetChanged();
    }

    private class PostHandler extends Handler {

        public PostHandler(Looper looper) {
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
