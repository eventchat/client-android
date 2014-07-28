package com.eventchat.view.adapter;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.eventchat.util.DebugLog;

public class EventChatPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = EventChatPagerAdapter.class
            .getSimpleName();

    private List<Fragment> mFragmentList = null;

    public EventChatPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        DebugLog.d(TAG, "EventChatPagerAdapter");
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
