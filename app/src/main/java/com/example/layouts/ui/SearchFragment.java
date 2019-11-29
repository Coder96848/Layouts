package com.example.layouts.ui;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    private SearchView mSearchView = null;
    private SearchView.OnQueryTextListener mQueryTextListener;

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
        mSearchView = (SearchView) searchItem.getActionView();
        final SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (mSearchView != null) {
            mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            mQueryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return true;
                }
            };
            mSearchView.setOnQueryTextListener(mQueryTextListener);
            mSearchView.setIconifiedByDefault(false);
            mSearchView.setQueryHint(getResources().getString(R.string.fragment_search_toolbar_search_view_hint));
            mSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    int id = mSearchView.getContext().getResources()
                            .getIdentifier("android:id/search_view_text_view", null, null);
                    TextView textView = mSearchView.findViewById(id);

                    if (b) {
                        mSearchView.setBackgroundColor(getResources().getColor(R.color.white));
                        textView.setTextColor(getResources().getColor(R.color.cool_grey));

                    } else {
                        mSearchView.setBackgroundColor(getResources().getColor(R.color.white));
                        textView.setTextColor(getResources().getColor(R.color.black_87));
                    }
                }
            });
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:

                return true;

            default:
                break;
        }

        mSearchView.setOnQueryTextListener(mQueryTextListener);
        return super.onOptionsItemSelected(item);
    }

    private class SearchViewPagerAdapter extends FragmentPagerAdapter {

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
                    return getString(R.string.search_page_event_title);
                case 1:
                    return getString(R.string.search_page_nko_title);

                default:
                    return "";
            }
        }
    }
}
