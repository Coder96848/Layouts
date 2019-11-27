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




//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            if(getActivity() != null) {
//                ImageView imageView = getActivity().findViewById(R.id.profile_photo_image_view);
//                Picasso.with(getContext()).load(photoFile).fit().centerCrop().into(imageView);
//                dismiss();
//            }
//        }
//    }

//    private void takePictureIntent() {
//        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        if(getActivity() != null) {
//            if (pictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//                photoFile = null;
//                try {
//                    photoFile = createImageFile();
//                } catch (IOException ex) {
//                    Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//                if (photoFile != null) {
//                    Uri photoURI = FileProvider.getUriForFile(getActivity(),
//                            "com.example.layouts.fileprovider",
//                            photoFile);
//                    pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                    startActivityForResult(pictureIntent, REQUEST_TAKE_PHOTO);
//                }
//            }
//        }
//    }
//
//    private File createImageFile() throws IOException {
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File image = null;
//        if(getActivity() != null) {
//            File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//             image = File.createTempFile(
//                    imageFileName,
//                    ".jpg",
//                    storageDir
//            );
//        }
//        return image;
//    }


}
