package com.example.layouts.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.layouts.R;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        androidx.appcompat.widget.Toolbar toolbar = view.findViewById(R.id.fragment_profile_toolbar);

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();

        if (appCompatActivity != null)
            appCompatActivity.setSupportActionBar(toolbar);

        ImageView profilePhotoImageView = view.findViewById(R.id.profile_photo_image_view);
        profilePhotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.edit_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void showDialog() {
        if(getActivity() != null) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            ChangePhotoFragment changePhotoFragment = new ChangePhotoFragment();
            changePhotoFragment.show(fragmentManager, "dialog");
        }
    }
}
