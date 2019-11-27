package com.example.layouts.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.layouts.R;
import com.google.android.material.tabs.TabLayout;

public class SearchFragment extends Fragment {

    public SearchFragment() {
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        androidx.appcompat.widget.Toolbar toolbar = view.findViewById(R.id.fragment_search_toolbar);
        toolbar.inflateMenu(R.menu.search_toolbar_menu);

        SearchViewPagerAdapter adapter = new SearchViewPagerAdapter(getChildFragmentManager());
        ViewPager viewPager = view.findViewById(R.id.fragment_search_view_pager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = view.findViewById(R.id.fragment_search_view_pager_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setQueryHint(getString(R.string.fragment_search_toolbar_search_view_hint));
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private static class SearchViewPagerAdapter extends FragmentPagerAdapter {

        int NUM_PAGES = 2;
        public SearchViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new SearchViewPagerEventFragment();
                case 1:
                    return new SearchViewPagerNkoFragment();

                default:
                    return new SearchViewPagerNkoFragment();
            }

        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "По мероприятиям";
                case 1:
                    return "По НКО";

                default:
                    return "";
            }
        }
    }
}
