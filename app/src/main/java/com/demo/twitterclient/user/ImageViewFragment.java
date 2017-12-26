package com.demo.twitterclient.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.twitterclient.R;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;


public class ImageViewFragment extends Fragment {
    private View mainView;
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    BlurView blurView;


    public ImageViewFragment() {
        // Required empty public constructor
    }


    public static ImageViewFragment newInstance(String param1) {
        ImageViewFragment fragment = new ImageViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_user_info, container, false);
        View decorView = getActivity().getWindow().getDecorView();
        //Activity's root View. Can also be root View of your layout (preferably)
        ViewGroup rootView = (ViewGroup) decorView.findViewById(R.id.mainView);
        //set background, if your root layout doesn't have one

        blurView = mainView.findViewById(R.id.blurView);
        blurView.setupWith(rootView)
                .blurAlgorithm(new RenderScriptBlur(getActivity()))
                .blurRadius(20);
        return mainView;
    }

}
