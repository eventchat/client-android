package com.eventchat;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.eventchat.entity.EntityFactory;
import com.eventchat.entity.Event;
import com.eventchat.manager.PostManager;
import com.eventchat.util.DebugLog;
import com.eventchat.view.EventFragment;
import com.eventchat.view.EventPatternAdapter;

public class MainActivity extends Activity implements OnTabChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String TAB_JOIN = "join";

    private static final String TAB_EVENT = "event";

    private static final String TAB_CHAT = "chat";

    private static final String TAB_ME = "me";

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
                        PostManager.getInstance(MainActivity.this).updateView(
                                position);
                        mTabHost.setCurrentTab(position);
                    }
                });

        PostManager.getInstance(MainActivity.this).updateView(0);
        PostManager.getInstance(MainActivity.this).getPost(
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
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
            case 0:
                return getString(R.string.join);
            case 1:
                return getString(R.string.my_events);
            case 2:
                return getString(R.string.chat);
            case 3:
                return getString(R.string.me);
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
        public View onCreateView(LayoutInflater inflater,
                final ViewGroup container, Bundle savedInstanceState) {
            int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
            View rootView = null;
            switch (sectionNumber) {
            case 1:
                rootView = inflater.inflate(R.layout.join_fragment, container,
                        false);
                GridView view = (GridView) rootView.findViewById(R.id.pattern);
                List<Event> eventList = new ArrayList<Event>();
                eventList.add(EntityFactory.createEvent("1"));
                eventList.add(EntityFactory.createEvent("2"));
                eventList.add(EntityFactory.createEvent("3"));
                eventList.add(EntityFactory.createEvent("4"));
                view.setAdapter(new EventPatternAdapter(inflater, eventList));
                view.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long id) {
                        Log.d(TAG, "id = " + id);
                        getFragmentManager()
                                .beginTransaction()
                                .replace(container.getId(),
                                        new EventFragment(), "event").commit();
                    }
                });
                break;
            case 2:
                rootView = inflater.inflate(R.layout.my_event_fragment, container,
                        false);
                break;
            case 3:
                rootView = inflater.inflate(R.layout.chat_fragment, container,
                        false);
                break;
            case 4:
                rootView = inflater.inflate(R.layout.me_fragment, container,
                        false);
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
            mTabHost.addTab(newTab(TAB_JOIN, R.string.join, R.id.tab_join));
            mTabHost.addTab(newTab(TAB_EVENT, R.string.my_events,
                    R.id.tab_event));
            mTabHost.addTab(newTab(TAB_CHAT, R.string.chat, R.id.tab_chat));
            mTabHost.addTab(newTab(TAB_ME, R.string.me, R.id.tab_me));
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
