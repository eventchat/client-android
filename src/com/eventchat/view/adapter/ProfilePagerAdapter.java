package com.eventchat.view.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.eventchat.view.EventListFragment;
import com.eventchat.view.PostListFragment;

public class ProfilePagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = ProfilePagerAdapter.class.getSimpleName();

    private static final int PAGE_SIZE = 2;

    public ProfilePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        Fragment fragment = null;
        switch (arg0) {
        case 0:
            fragment = new PostListFragment();
            break;
        case 1:
            fragment = new EventListFragment();
            break;
        default:
            break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_SIZE;
    }
}
