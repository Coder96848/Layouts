package com.example.layouts.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.layouts.R;
import com.squareup.picasso.Picasso;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {

    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int GALLERY_REQUEST = 2;

    private ImageView mProfilePhotoImageView;
    private String mCurrentPhotoPath;

    public ProfileFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        androidx.appcompat.widget.Toolbar toolbar = view.findViewById(R.id.fragment_profile_toolbar);
        toolbar.inflateMenu(R.menu.profile_toolbar_menu);

        mProfilePhotoImageView = view.findViewById(R.id.profile_photo_image_view);
        mProfilePhotoImageView.setOnClickListener(v -> showDialog());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch(requestCode) {
                case REQUEST_TAKE_PHOTO:
                    setImage();
                case GALLERY_REQUEST:
                    if(data != null) {
                        final Uri imageUri = data.getData();
                        mCurrentPhotoPath = imageUri.getPath();
                        Picasso.with(getContext()).load(imageUri).fit().centerCrop().into(mProfilePhotoImageView);
                    }
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("PHOTO", mCurrentPhotoPath);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null) {
            mCurrentPhotoPath  = savedInstanceState.getString("PHOTO");
            setImage();
        }
    }

    private void setImage() {
        Picasso.with(getContext()).load(new File(mCurrentPhotoPath)).fit().centerCrop().into(mProfilePhotoImageView);
    }

    public void doCameraAction(){
        takeCameraIntent();
    }

    public void doDeleteAction(){
        Picasso.with(getContext()).load(R.drawable.user_icon).into(mProfilePhotoImageView);
    }

    public void doChangeAction(){
        takeGalleryIntent();
    }


    private void showDialog() {
            FragmentManager fragmentManager = getChildFragmentManager();
            ChangePhotoFragment changePhotoFragment = new ChangePhotoFragment();
            changePhotoFragment.show(fragmentManager, "dialog");
    }

    private void takeCameraIntent() {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(getActivity() != null) {
            if (pictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(getActivity(),
                            "com.example.layouts.fileprovider",
                            photoFile);
                    pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(pictureIntent, REQUEST_TAKE_PHOTO);
                }
            }
        }
    }

    private File createImageFile() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timeStamp = LocalDateTime.now().format(formatter);
        String imageFileName = "JPEG_" + timeStamp + "_";
        File image = null;
        if(getActivity() != null) {
            File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            image = File.createTempFile(
                    imageFileName,
                    ".jpg",
                    storageDir
            );
        }
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void takeGalleryIntent(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }
}
