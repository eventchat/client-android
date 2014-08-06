package com.eventchat.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.eventchat.R;
import com.eventchat.manager.ChatManager;
import com.eventchat.util.Constant;
import com.eventchat.util.DebugLog;

public class ConversationFragment extends Fragment {

    private static final String TAG = ConversationFragment.class
            .getSimpleName();

    private ListView mListView = null;

    private List<String> mConversationList = null;

    private ConversationHandler mHandler = null;

    private SharedPreferences mPref = null;

    private HashSet<String> mConversationSet = null;

    public ConversationFragment() {
        mHandler = new ConversationHandler(Looper.getMainLooper());
        mConversationList = new ArrayList<String>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.conversation_fragment,
                container, false);
        mListView = (ListView) rootView.findViewById(R.id.conversation_list);
        
        mPref = getActivity().getSharedPreferences(
                Constant.Data.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mHandler = new ConversationHandler(Looper.getMainLooper());
        Set<String> defaultSet = new HashSet<String>();
        mConversationSet = (HashSet<String>) mPref.getStringSet(
                Constant.Data.CONVERSATION, defaultSet);
        for (String conversation : mConversationSet) {
            mConversationList.add(conversation);
        }
        ChatManager.getInstance().getChatMessage(mHandler);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DebugLog.d(TAG, "onDestroy");
        Set<String> set = new HashSet<String>(mConversationList);
        mPref.edit().putStringSet(Constant.Data.CONVERSATION, set).commit();
    }

    private class ConversationHandler extends Handler {

        public ConversationHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}
