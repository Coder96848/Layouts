package com.example.layouts.interfaces;

import androidx.fragment.app.Fragment;

public interface OnFragmentActionListener {
    void onCameraAction();
    void onDeleteAction();
    void onChangeAction();
    void setFragment(Fragment fragment, String tag);
    void onFragmentBackStack();
    void setBottomNavigationVisible();
    void setBottomNavigationGone();
}
