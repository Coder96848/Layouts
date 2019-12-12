package com.example.layouts.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import androidx.core.content.ContextCompat;
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

    private static final String APP_PREFERENCES = "appSettings";

    private static final int REQUEST_EXTERNAL_STORAGE_PERMISSION = 0;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int GALLERY_REQUEST = 2;

    private ImageView mProfilePhotoImageView;
    private Uri mCurrentPhotoUri;
    private SharedPreferences sharedPreferences;
    private Context context;

    public ProfileFragment(Context context) {
        this.context = context;
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

        sharedPreferences = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        androidx.appcompat.widget.Toolbar toolbar = view.findViewById(R.id.fragment_profile_toolbar);
        toolbar.inflateMenu(R.menu.profile_toolbar_menu);

        onLoadPhotoUri();
        mProfilePhotoImageView = view.findViewById(R.id.profile_photo_image_view);
        if (mCurrentPhotoUri.getPath().equals("")) {
            doDeleteAction();
        } else {
            setImage();
        }
        mProfilePhotoImageView.setOnClickListener(v -> {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE_PERMISSION);
        }else {
            showDialog();
        }
        });
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
                        mCurrentPhotoUri = imageUri;
                        onSavePhoto();
                        Picasso.with(getContext()).load(imageUri).fit().centerCrop().into(mProfilePhotoImageView);
                    }
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("PHOTO", mCurrentPhotoUri.getPath());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null) {
            mCurrentPhotoUri = Uri.parse(savedInstanceState.getString("PHOTO"));
            setImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length > 0) {
            for (int result: grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) return;
            }
            switch (requestCode) {
                case REQUEST_EXTERNAL_STORAGE_PERMISSION:
                    showDialog();
                    break;
                case REQUEST_TAKE_PHOTO:
                    takeCameraIntent();
                    break;
                case GALLERY_REQUEST:
                    takeGalleryIntent();
                    break;
            }
        }
    }

    private void setImage() {
        Picasso.with(getContext()).load(new File(mCurrentPhotoUri.getPath())).fit().centerCrop().into(mProfilePhotoImageView);
    }

    public void doCameraAction(){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    REQUEST_TAKE_PHOTO);
        } else {
            takeCameraIntent();
        }
    }

    public void doDeleteAction(){
        Picasso.with(getContext()).load(R.drawable.user_icon).into(mProfilePhotoImageView);
        mCurrentPhotoUri = Uri.parse("");
    }

    public void doChangeAction(){
        if (ContextCompat.checkSelfPermission(context,  Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, GALLERY_REQUEST);
        }else {
            takeGalleryIntent();
        }
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
        mCurrentPhotoUri = Uri.parse(image.getAbsolutePath());
        onSavePhoto();
        return image;
    }

    private void takeGalleryIntent(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }


    private void onSavePhoto(){
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putString("currentPhotoUriKey", mCurrentPhotoUri.toString());
        e.apply();
    }

    private void onLoadPhotoUri() {
        mCurrentPhotoUri = Uri.parse(sharedPreferences.getString("currentPhotoUriKey", ""));
    }
}
