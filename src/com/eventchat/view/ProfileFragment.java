package com.eventchat.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eventchat.R;
import com.eventchat.entity.User;
import com.eventchat.manager.ProfileManager;
import com.eventchat.view.adapter.ProfilePagerAdapter;

public class ProfileFragment extends Fragment {

    public static final String USER_PROFILE = "user_profile";

    private static final String TAG = ProfileFragment.class.getSimpleName();

    private User mUser = null;

    private TextView mDesc = null;

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_fragment, container,
                false);
        mDesc = (TextView) rootView.findViewById(R.id.desc);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mUser = (User) bundle.get(USER_PROFILE);
        } else {
            mUser = ProfileManager.getInstance(getActivity()).getCurrentUser();
        }
        mDesc.setText(mUser.getInfo());

        ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);
        pager.setAdapter(new ProfilePagerAdapter(getFragmentManager()));
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
        return rootView;
    }
}
