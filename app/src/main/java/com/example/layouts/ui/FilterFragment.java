package com.example.layouts.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.R;
import com.example.layouts.model.ItemHelpData;
import com.example.layouts.util.FilterFragmentRecyclerAdapter;

public class FilterFragment extends Fragment {
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
        toolbar.setNavigationOnClickListener(new Toolbar.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        toolbar.setOnMenuItemClickListener(new androidx.appcompat.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return false;
            }
        });

        ItemHelpData itemHelpData = ItemHelpData.getInstance();
        RecyclerView recyclerView = view.findViewById(R.id.fragment_filter_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());

        FilterFragmentRecyclerAdapter adapter = new FilterFragmentRecyclerAdapter(itemHelpData.getItems());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}
