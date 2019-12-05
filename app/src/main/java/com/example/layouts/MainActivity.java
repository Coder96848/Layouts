package com.example.layouts;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.layouts.ui.ChangePhotoFragment;
import com.example.layouts.ui.HelpFragment;
import com.example.layouts.ui.NewsFragment;
import com.example.layouts.ui.ProfileFragment;
import com.example.layouts.ui.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jakewharton.threetenabp.AndroidThreeTen;


public class MainActivity extends AppCompatActivity implements ChangePhotoFragment.OnFragmentActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidThreeTen.init(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.help_bottom_navigation_item);

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.activity_main_fragment_main);
        if (currentFragment == null) {
            setFragment(new HelpFragment());
            bottomNavigationView.setSelectedItemId(R.id.help_bottom_navigation_item);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.news_bottom_navigation_item:
                        setFragment(new NewsFragment());
                        return true;
                    case R.id.search_bottom_navigation_item:
                        setFragment(new SearchFragment());
                        return true;
                    case R.id.help_bottom_navigation_item:
                        setFragment(new HelpFragment());
                        return true;
                    case R.id.profile_bottom_navigation_item:
                        setFragment(new ProfileFragment());
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof ChangePhotoFragment) {
            ChangePhotoFragment changePhotoFragment = (ChangePhotoFragment) fragment;
            changePhotoFragment.setOnFragmentActionListener(this);
        }
    }

    @Override
    public void onCameraAction() {
        ProfileFragment fragment = (ProfileFragment)
                getSupportFragmentManager().findFragmentById(R.id.activity_main_fragment_main);
        if (fragment != null) {
            fragment.doCameraAction();
        }
    }

    @Override
    public void onDeleteAction() {
        ProfileFragment fragment = (ProfileFragment)
                getSupportFragmentManager().findFragmentById(R.id.activity_main_fragment_main);
        if (fragment != null) {
            fragment.doDeleteAction();
        }
    }

    @Override
    public void onChangeAction() {
        ProfileFragment fragment = (ProfileFragment)
                getSupportFragmentManager().findFragmentById(R.id.activity_main_fragment_main);
        if (fragment != null) {
            fragment.doChangeAction();
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_fragment_main, fragment);
        fragmentTransaction.commit();
    }
}
