package com.example.layouts.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.R;
import com.example.layouts.util.adapters.FilterFragmentRecyclerAdapter;

import java.util.ArrayList;

public class FilterFragment extends Fragment {

    private ArrayList<String> categories;
    private ArrayList<String> selectedCategories;

    public FilterFragment(ArrayList<String> categories, ArrayList<String> selectedCategories) {
        this.categories = categories;
        this.selectedCategories = selectedCategories;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        androidx.appcompat.widget.Toolbar toolbar = view.findViewById(R.id.fragment_filter_toolbar);
        toolbar.inflateMenu(R.menu.filter_toolbar_menu);
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(v -> getFragmentManager().popBackStack());

        toolbar.setOnMenuItemClickListener(item -> {

            if(item.getItemId() == R.id.filter_menu_item_ok){
                NewsFragment newsFragment = (NewsFragment) getFragmentManager().findFragmentByTag("NEWS_FRAGMENT");
                newsFragment.setSelectedCategories(selectedCategories);
                getFragmentManager().popBackStack();
            }

            return false;
        });

        RecyclerView recyclerView = view.findViewById(R.id.fragment_filter_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());

        FilterFragmentRecyclerAdapter adapter = new FilterFragmentRecyclerAdapter(categories, selectedCategories);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}
