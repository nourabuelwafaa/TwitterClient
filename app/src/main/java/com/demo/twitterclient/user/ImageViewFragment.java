package com.demo.twitterclient.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.twitterclient.R;
import com.demo.twitterclient.utils.Utils;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class ImageViewFragment extends Fragment {
    private static final String PHOTO_URL = "photoUrl";
    private String photoUrl;


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
        PhotoView photoView = mainView.findViewById(R.id.imageView);
        if (photoUrl != null) {
            Picasso.with(getActivity()).load(Utils.getBiggerBackground(photoUrl)).into(photoView);
        }
        return mainView;
    }

}
