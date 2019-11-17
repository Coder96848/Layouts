package com.example.layouts;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.layouts.ui.HelpFragment;
import com.example.layouts.ui.ProfileFragment;
import com.example.layouts.ui.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private final FragmentManager mFragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.help_bottom_navigation_item);

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.activity_main_fragment_main, new HelpFragment());
        transaction.commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.help_bottom_navigation_item:
                        transaction.replace(R.id.activity_main_fragment_main, new HelpFragment());
                        transaction.commit();
                        return true;
                    case R.id.profile_bottom_navigation_item:
                        transaction.replace(R.id.activity_main_fragment_main, new ProfileFragment());
                        transaction.commit();
                        return true;
                    case R.id.search_bottom_navigation_item:
                        transaction.replace(R.id.activity_main_fragment_main, new SearchFragment());
                        transaction.commit();
                        return true;
                }
                return false;
            }
        });
    }

}
