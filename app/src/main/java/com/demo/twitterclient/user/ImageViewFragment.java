package com.demo.twitterclient.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.twitterclient.R;
import com.demo.twitterclient.Utils;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;


public class ImageViewFragment extends Fragment {
    private static final String PHOTO_URL = "photoUrl";
    private String photoUrl;
    BlurView blurView;


    public ImageViewFragment() {
        // Required empty public constructor
    }


    public static ImageViewFragment newInstance(String photo) {
        ImageViewFragment fragment = new ImageViewFragment();
        Bundle args = new Bundle();
        args.putString(PHOTO_URL, photo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            photoUrl = getArguments().getString(PHOTO_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_image_view, container, false);
        View decorView = getActivity().getWindow().getDecorView();
        ViewGroup rootView = decorView.findViewById(R.id.mainView);

        blurView = mainView.findViewById(R.id.blurView);
        blurView.setupWith(rootView)
                .blurAlgorithm(new RenderScriptBlur(getActivity()))
                .blurRadius(20);
        PhotoView photoView = mainView.findViewById(R.id.imageView);
        Picasso.with(getActivity()).load(Utils.getBiggerPhoto(photoUrl)).into(photoView);
        return mainView;
    }

}
