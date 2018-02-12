package com.mapx.kosten.infocomarca.fragments;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mapx.kosten.infocomarca.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    private TextView mVersionName;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about, container, false);

        mVersionName = (TextView)view.findViewById(R.id.txtView_appVersion);

        try {
            PackageInfo pInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
            String version = pInfo.versionName;
            int vcode = pInfo.versionCode;
            mVersionName.setText("V " +String.valueOf(vcode) + "." + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return view;

    }

}
