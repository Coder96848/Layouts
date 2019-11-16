package com.example.layouts;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.layouts.ui.HelpFragment;
import com.example.layouts.ui.ProfileFragment;
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

        final androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.profile_toolbar);
        final TextView toolbarTextView = toolbar.findViewById(R.id.toolbar_text_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.help_bottom_navigation_item:
                        transaction.replace(R.id.activity_main_fragment_main, new HelpFragment());
                        transaction.commit();
                        toolbar.getMenu().clear();
                        toolbar.inflateMenu(R.menu.help_toolbar_menu);
                        toolbarTextView.setText("Помочь");
                        return true;
                    case R.id.profile_bottom_navigation_item:
                        transaction.replace(R.id.activity_main_fragment_main, new ProfileFragment());
                        transaction.commit();
                        toolbar.getMenu().clear();
                        toolbar.inflateMenu(R.menu.edit_toolbar_menu);
                        toolbarTextView.setText("Профиль");
                        return true;
                }
                return false;
            }
        });


        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.help_toolbar_menu, menu);
        return true;
    }

}
