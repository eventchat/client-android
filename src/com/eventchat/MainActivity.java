package com.eventchat;

import java.util.ArrayList;
import java.util.List;

import com.eventchat.entity.Post;
import com.eventchat.manager.FeedManager;
import com.eventchat.util.DebugLog;
import com.eventchat.view.FeedEntryAdapter;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class MainActivity extends Activity implements OnTabChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String TAB_FEED = "feed";

    private static final String TAB_CHAT = "chat";

    private static final String TAB_PROFILE = "profile";

    private static final String TAB_CREATE = "create";

    private static final String TAB_NOTIFICATION = "notification";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this
     * becomes too memory intensive, it may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter = null;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager = null;

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

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager
                .setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        DebugLog.d(TAG, "onPageSelected position = " + position);
                        FeedManager.getInstance(MainActivity.this).updateView(
                                position);
                        mTabHost.setCurrentTab(position);
                    }
                });

        FeedManager.getInstance(MainActivity.this).updateView(0);
        FeedManager.getInstance(MainActivity.this).getPost(
                "53b0df57a5e69302004d0898");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabChanged(String tabId) {
        Log.d(TAG, "onTabChanged = " + tabId);
        mViewPager.setCurrentItem(mTabHost.getCurrentTab());
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class
            // below).
            return TabFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
            case 0:
                return getString(R.string.feed);
            case 1:
                return getString(R.string.chat);
            case 2:
                return getString(R.string.profile);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class TabFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section number.
         */
        public static TabFragment newInstance(int sectionNumber) {
            TabFragment fragment = new TabFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public TabFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            View rootView = null;
            switch (sectionNumber) {
            case 1:
                rootView = inflater.inflate(R.layout.feed_fragment, container,
                        false);
                break;
            case 2:
                rootView = inflater.inflate(R.layout.chat_fragment, container,
                        false);
                break;
            case 3:
                rootView = inflater.inflate(R.layout.profile_fragment,
                        container, false);
                break;
            default:
                break;
            }
            return rootView;
        }
    }

    private void setupTabs() {
        if (mTabHost != null) {
            mTabHost.setup();
            mTabHost.addTab(newTab(TAB_FEED, R.string.feed, R.id.tab_feed));
            mTabHost.addTab(newTab(TAB_CHAT, R.string.chat, R.id.tab_chat));
            // mTabHost.addTab(newTab(TAB_CREATE, R.string.create,
            // R.id.tab_create));
            // mTabHost.addTab(newTab(TAB_NOTIFICATION, R.string.notification,
            // R.id.tab_notification));
            mTabHost.addTab(newTab(TAB_PROFILE, R.string.profile,
                    R.id.tab_profile));
            mTabHost.setOnTabChangedListener(this);
        }
    }

    private TabSpec newTab(String tag, int labelId, int tabContentId) {
        Log.d(TAG, "newTab: tag = " + tag);
        TabSpec tabSpec = mTabHost.newTabSpec(tag);
        tabSpec.setIndicator(getResources().getText(labelId));
        tabSpec.setContent(tabContentId);
        return tabSpec;
    }
}
