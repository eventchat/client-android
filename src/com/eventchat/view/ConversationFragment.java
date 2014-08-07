package com.eventchat.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.eventchat.ChatActivity;
import com.eventchat.R;
import com.eventchat.entity.ChatMessage;
import com.eventchat.manager.ChatManager;
import com.eventchat.util.Constant;
import com.eventchat.util.DebugLog;
import com.eventchat.util.JsonParser;
import com.eventchat.view.adapter.ConversationListAdapter;

public class ConversationFragment extends Fragment {

    private static final String TAG = ConversationFragment.class
            .getSimpleName();

    private ListView mListView = null;

    private List<ChatMessage> mConversationList = null;

    private ConversationHandler mHandler = null;

    private ConversationListAdapter mAdapter = null;

    public ConversationFragment() {
        mConversationList = new ArrayList<ChatMessage>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.conversation_fragment,
                container, false);
        mListView = (ListView) rootView.findViewById(R.id.conversation_list);
        mHandler = new ConversationHandler(Looper.getMainLooper());
        mAdapter = new ConversationListAdapter(getActivity(), mConversationList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.Data.CHAT_DATA,
                        mConversationList.get(position).getFrom());
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
        ChatManager.getInstance().getChatMessage(mHandler);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateConversationList(ChatManager.getInstance().getConversationList());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DebugLog.d(TAG, "onDestroy");
    }

    private void updateConversationList(List<ChatMessage> messages) {
        ChatManager manager = ChatManager.getInstance();
        manager.putChatMessage(messages);
        mConversationList.clear();
        mConversationList.addAll(manager.getConversationList());
        mAdapter.notifyDataSetChanged();
    }

    private class ConversationHandler extends Handler {

        public ConversationHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            DebugLog.d(TAG, "handleMessage");
            switch (msg.what) {
            case Constant.UI.UPDATE_CHAT_MESSAGE:
                updateConversationList(JsonParser
                        .parseReceiveMessages((String) msg.obj));
                break;
            default:
                break;
            }
        }
    }
}
