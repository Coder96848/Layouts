package com.example.layouts.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.R;
import com.example.layouts.model.CardsHelpData;
import com.example.layouts.util.RecyclerAdapter;

public class HelpFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_help, container, false);

        CardsHelpData cardsHelpData = new CardsHelpData();
        RecyclerView recyclerView = rootView.findViewById(R.id.fragment_help_category_help_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(rootView.getContext(), 2);
        RecyclerAdapter adapter = new RecyclerAdapter(cardsHelpData.initializeData());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
