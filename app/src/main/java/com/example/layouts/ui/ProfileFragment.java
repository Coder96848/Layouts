package com.example.layouts.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.layouts.R;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageView profilePhotoImageView = rootView.findViewById(R.id.profile_photo_image_view);
        profilePhotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return rootView;
    }

    private void showDialog() {
        if(getActivity() != null) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            ChangePhotoFragment changePhotoFragment = new ChangePhotoFragment();

            changePhotoFragment.show(fragmentManager, "dialog");
        }
    }
}
