package com.gthr.android.view_objects;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gthr.android.R;

/**
 * Created by noshaf on 6/22/15.
 */
public class OnboardingViewPager extends Fragment {
    private String type;
    private String desc;

    public static OnboardingViewPager newInstance(String type, String desc) {
        OnboardingViewPager fragmentFirst = new OnboardingViewPager();
        Bundle args = new Bundle();
        args.putString("type", type);
        args.putString("desc", desc);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getString("type");
        desc = getArguments().getString("desc");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;

        if (type.equals("edit")) {
            view = inflater.inflate(R.layout.layout_onboard_edit, container, false);
            ((TextView)view.findViewById(R.id.onboard_desc)).setText(desc);
        } else if (type.equals("invite")) {
            view = inflater.inflate(R.layout.layout_onboard_invite, container, false);
            ((TextView)view.findViewById(R.id.onboard_desc)).setText(desc);
        } else if (type.equals("done")) {
        }

        return view;
    }
}
