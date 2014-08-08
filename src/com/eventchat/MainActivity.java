package com.eventchat;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.eventchat.manager.ChatManager;
import com.eventchat.util.Constant;
import com.eventchat.util.DebugLog;
import com.eventchat.view.ConversationFragment;
import com.eventchat.view.JoinFragment;
import com.eventchat.view.MyEventsFragment;
import com.eventchat.view.ProfileFragment;

public class MainActivity extends Activity implements OnTabChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TabHost mTabHost = null;

    private ActionBar mActionBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DebugLog.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mTabHost = (TabHost) findViewById(R.id.tab_host);
        setupTabs();

        // Set up the action bar.
        mActionBar = getActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        mActionBar.setBackgroundDrawable(getResources().getDrawable(
                R.color.theme));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        ChatManager.getInstance().dispose();
        finish();
    }

    @Override
    public void onTabChanged(String tabId) {
        Log.d(TAG, "onTabChanged = " + tabId);
        // mTabHost.setCurrentTabByTag(tabId);
        if (tabId.equals(Constant.Tag.TAB_JOIN)) {
            mActionBar.setTitle(R.string.app_name);
        } else if (tabId.equals(Constant.Tag.TAB_CHAT)) {
            mActionBar.setTitle(R.string.chat);
        } else if (tabId.equals(Constant.Tag.TAB_EVENT)) {
            mActionBar.setTitle(R.string.my_events);
        } else if (tabId.equals(Constant.Tag.TAB_ME)) {
            mActionBar.setTitle(R.string.me);
        }
    }

    public void setCurrentTab(String tag) {
        mTabHost.setCurrentTabByTag(tag);
    }

    private void setupTabs() {
        if (mTabHost != null) {
            mTabHost.setup();
            mTabHost.addTab(newTab(Constant.Tag.TAB_JOIN, R.string.join,
                    R.id.tab_join, R.drawable.tab_join_selector,
                    new JoinFragment()));
            mTabHost.addTab(newTab(Constant.Tag.TAB_EVENT, R.string.my_events,
                    R.id.tab_event, R.drawable.tab_my_events_selector,
                    new MyEventsFragment()));
            mTabHost.addTab(newTab(Constant.Tag.TAB_CHAT, R.string.chat,
                    R.id.tab_chat, R.drawable.tab_chat_selector,
                    new ConversationFragment()));
            mTabHost.addTab(newTab(Constant.Tag.TAB_ME, R.string.me,
                    R.id.tab_me, R.drawable.tab_me_selector,
                    new ProfileFragment()));
            mTabHost.setOnTabChangedListener(this);
        }
    }

    private TabSpec newTab(String tag, int labelId, int tabContentId,
            int tabDrawable, Fragment fragment) {
        Log.d(TAG, "newTab: tag = " + tag);
        TabSpec tabSpec = mTabHost.newTabSpec(tag);
        LayoutInflater inflater = LayoutInflater.from(this);
        View tabView = inflater.inflate(R.layout.tab_view, null);
        TextView label = (TextView) tabView.findViewById(R.id.tab);
        label.setText(getResources().getText(labelId));
        Drawable drawable = getResources().getDrawable(tabDrawable);
        label.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null,
                null);
        tabSpec.setIndicator(tabView);
        tabSpec.setContent(tabContentId);
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        transaction.add(tabContentId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        return tabSpec;
    }
}
