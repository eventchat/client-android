package com.eventchat.view.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eventchat.R;
import com.eventchat.entity.Post;

public class PostListAdapter extends BaseAdapter {

    private Context mContext = null;

    private List<Post> mPostList = null;

    public PostListAdapter(Context context, List<Post> postList) {
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
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.post_entry, null);
        }
        TextView authorText = (TextView) convertView
                .findViewById(R.id.autor_name);
        TextView postDateText = (TextView) convertView
                .findViewById(R.id.post_date);
        TextView postContentText = (TextView) convertView
                .findViewById(R.id.post_content);
        TextView likeNumText = (TextView) convertView
                .findViewById(R.id.post_like_num);
        TextView commentNumText = (TextView) convertView
                .findViewById(R.id.post_comment_num);
        Post post = mPostList.get(position);
        authorText.setText(post.getAuthor().getName());
        postDateText.setText(post.getCreatedAt());
        postContentText.setText(post.getBody());
        likeNumText.setText("" + post.getLikedUserList().size());
        commentNumText.setText("" + post.getCommentList().size());
        return convertView;
    }

}
