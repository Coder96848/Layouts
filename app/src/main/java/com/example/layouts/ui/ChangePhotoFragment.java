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


public class ChangePhotoFragment extends DialogFragment {

    private OnFragmentActionListener callback;
    private String action;

    public void setOnFragmentActionListener(OnFragmentActionListener callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_change_photo_dialog, container, false);

        TextView cameraTextView = view.findViewById(R.id.change_photo_dialog_camera_text_view);
        cameraTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = getString(R.string.change_photo_action_camera);
                callback.onFragmentAction(action);
                dismiss();
            }
        });

        TextView deletePhotoTextView = view.findViewById(R.id.change_photo_dialog_delete_text_view);
        deletePhotoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action = getString(R.string.change_photo_action_delete);
                callback.onFragmentAction(action);
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnFragmentActionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + getString(R.string.change_fragment_exception_text));
        }
    }

    public interface OnFragmentActionListener {

        void onFragmentAction(String action);
    }

}
