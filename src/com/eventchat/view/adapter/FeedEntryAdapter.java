package com.eventchat.view.adapter;

import java.util.List;

import com.eventchat.R;
import com.eventchat.entity.Post;
import com.eventchat.util.DebugLog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class FeedEntryAdapter extends BaseAdapter {

    private static final String TAG = FeedEntryAdapter.class.getSimpleName();

    private Context mContext = null;

    private List<Post> mPostList = null;

    public FeedEntryAdapter(Context context, List<Post> postList) {
        DebugLog.d(TAG, "FeedEntryAdapter");
        mContext = context;
        mPostList = postList;
    }

    @Override
    public int getCount() {
        return mPostList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPostList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            // TODO
        } else {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.post_entry, parent);
        }
        return convertView;
    }
}
