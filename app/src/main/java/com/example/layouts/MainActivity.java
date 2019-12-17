package com.example.layouts;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.layouts.interfaces.ICategory;
import com.example.layouts.interfaces.OnFragmentActionListener;
import com.example.layouts.ui.HelpFragment;
import com.example.layouts.ui.NewsDetailsFragment;
import com.example.layouts.ui.NewsFragment;
import com.example.layouts.ui.ProfileFragment;
import com.example.layouts.ui.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements OnFragmentActionListener, ICategory {

    private BottomNavigationView bottomNavigationView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = App.getSharedPreferences();

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.help_bottom_navigation_item);

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.activity_main_fragment_main);
        if (currentFragment == null) {
            setFragment(new HelpFragment(), "HELP");
            bottomNavigationView.setSelectedItemId(R.id.help_bottom_navigation_item);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(
                menuItem -> {
                    switch (menuItem.getItemId()) {
                        case R.id.news_bottom_navigation_item:
                            setFragment(new NewsFragment(), "NEWS_FRAGMENT");
                            return true;
                        case R.id.search_bottom_navigation_item:
                            setFragment(new SearchFragment(), "SEARCH_FRAGMENT");
                            return true;
                        case R.id.help_bottom_navigation_item:
                            setFragment(new HelpFragment(), "HELP_FRAGMENT");
                            return true;
                        case R.id.profile_bottom_navigation_item:
                            setFragment(new ProfileFragment(this),"PROFILE_FRAGMENT");
                            return true;
                        case R.id.history_bottom_navigation_item:
                            return true;
                    }
                    return false;
                });

    }

    @Override
    public void onAttachFragment(Fragment fragment){
        if(fragment instanceof NewsDetailsFragment){
            setBottomNavigationGone();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount() == 0){
            this.finish();
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

    @Override
    public void onFragmentBackStack() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void setBottomNavigationVisible() {
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setBottomNavigationGone() {
        bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void setFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_fragment_main, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    @Override
    public void onSaveCategories(ArrayList<String> selectedCategories){
        Set<String> categoriesSet = new HashSet<>(selectedCategories);
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putStringSet("selectedCategoriesSetKey", categoriesSet);
        e.apply();
    }

    @Override
    public ArrayList<String> onLoadCategories() {
        Set<String> categoriesSet = sharedPreferences.getStringSet("selectedCategoriesSetKey", new HashSet<>());
        return new ArrayList<>(categoriesSet);
    }
}
