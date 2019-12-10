package com.example.layouts.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.R;
import com.example.layouts.model.SearchResultData;
import com.example.layouts.util.adapters.SearchFragmentRecyclerAdapter;

public class SearchViewPagerEventFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search_view_pager_event_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SearchResultData searchResultData = new SearchResultData();
        RecyclerView recyclerView = view.findViewById(R.id.fragment_search_view_pager_event_fragment_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        SearchFragmentRecyclerAdapter adapter = new SearchFragmentRecyclerAdapter(searchResultData.initializeData());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        TextView textView = view.findViewById(R.id.fragment_search_view_pager_event_fragment_text_view);
        String text = getString(R.string.search_view_pager_text, adapter.getItemCount());
        textView.setText(text);
    }
}
