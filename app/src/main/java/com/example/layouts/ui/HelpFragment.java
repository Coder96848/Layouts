package com.example.layouts.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.R;
import com.example.layouts.model.ItemHelpData;
import com.example.layouts.util.HelpFragmentRecyclerAdapter;

public class HelpFragment extends Fragment {

    public HelpFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_help, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        androidx.appcompat.widget.Toolbar toolbar = view.findViewById(R.id.fragment_help_toolbar);
        toolbar.inflateMenu(R.menu.empty_toolbar_menu);

        ItemHelpData itemHelpData = new ItemHelpData();
        RecyclerView recyclerView = view.findViewById(R.id.fragment_help_category_help_recycler_view);
        GridLayoutManager gridLayoutManager;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        }
        else {
            gridLayoutManager = new GridLayoutManager(view.getContext(), 4);
        }
        HelpFragmentRecyclerAdapter adapter = new HelpFragmentRecyclerAdapter(itemHelpData.initializeData());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}
