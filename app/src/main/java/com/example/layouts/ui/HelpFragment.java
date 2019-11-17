package com.example.layouts.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.R;
import com.example.layouts.model.CardHelpData;
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
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if (appCompatActivity != null)
            appCompatActivity.setSupportActionBar(toolbar);

        CardHelpData cardHelpData = new CardHelpData();
        RecyclerView recyclerView = view.findViewById(R.id.fragment_help_category_help_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        HelpFragmentRecyclerAdapter adapter = new HelpFragmentRecyclerAdapter(cardHelpData.initializeData());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.empty_toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
