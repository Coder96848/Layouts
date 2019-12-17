package com.example.layouts.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.layouts.R;
import com.example.layouts.interfaces.OnFragmentActionListener;


public class ChangePhotoFragment extends DialogFragment {

    private OnFragmentActionListener callback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_change_photo_dialog, container, false);

        if(callback != null) {
            TextView cameraTextView = view.findViewById(R.id.change_photo_dialog_camera_text_view);
            cameraTextView.setOnClickListener(v -> {
                callback.onCameraAction();
                dismiss();
            });

            TextView deletePhotoTextView = view.findViewById(R.id.change_photo_dialog_delete_text_view);
            deletePhotoTextView.setOnClickListener(v -> {
                callback.onDeleteAction();
                dismiss();
            });

            TextView changePhotoTextView = view.findViewById(R.id.change_photo_dialog_upload_text_view);
            changePhotoTextView.setOnClickListener(v -> {
                callback.onChangeAction();
                dismiss();
            });
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(getActivity() instanceof OnFragmentActionListener) {
            callback = (OnFragmentActionListener) getActivity();
        }
    }
}
