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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.eventchat.util.DebugLog;
import com.eventchat.view.ChatTabFragment;
import com.eventchat.view.EventFragment;
import com.eventchat.view.JoinTabFragment;
import com.eventchat.view.MyEventsTabFragment;
import com.eventchat.view.ProfileTabFragment;

public class MainActivity extends Activity implements OnTabChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String TAB_JOIN = "join";

    private static final String TAB_EVENT = "event";

    private static final String TAB_CHAT = "chat";

    private static final String TAB_ME = "me";

    private TabHost mTabHost = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DebugLog.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mTabHost = (TabHost) findViewById(R.id.tab_host);
        setupTabs();

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GridView view = (GridView) findViewById(R.id.pattern);
        if (view != null) {
            view.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                        int position, long id) {
                    DebugLog.d(TAG, "onItemClick");
                    EventFragment fragment = new EventFragment();
                    FragmentTransaction transaction = getFragmentManager()
                            .beginTransaction();
                    transaction.replace(R.id.tab_join, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        finish();
    }

    @Override
    public void onTabChanged(String tabId) {
        Log.d(TAG, "onTabChanged = " + tabId);
        mTabHost.setCurrentTabByTag(tabId);

    }

    private void setupTabs() {
        if (mTabHost != null) {
            mTabHost.setup();
            mTabHost.addTab(newTab(TAB_JOIN, R.string.join, R.id.tab_join,
                    R.drawable.tab_join_selector, new JoinTabFragment()));
            mTabHost.addTab(newTab(TAB_EVENT, R.string.my_events,
                    R.id.tab_event, R.drawable.tab_my_events_selector,
                    new MyEventsTabFragment()));
            mTabHost.addTab(newTab(TAB_CHAT, R.string.chat, R.id.tab_chat,
                    R.drawable.tab_chat_selector, new ChatTabFragment()));
            mTabHost.addTab(newTab(TAB_ME, R.string.me, R.id.tab_me,
                    R.drawable.tab_me_selector, new ProfileTabFragment()));
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
