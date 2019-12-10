package com.example.layouts.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.interfaces.ICategory;
import com.example.layouts.R;
import com.example.layouts.model.Event;
import com.example.layouts.model.EventsList;
import com.example.layouts.util.adapters.NewsFragmentRecyclerAdapter;
import com.example.layouts.util.json.JSONLoader;

import java.util.ArrayList;

public class NewsFragment extends Fragment implements ICategory {

    private ArrayList<String> categories;
    private ArrayList<String> selectedCategories;
    private EventsList eventsList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JSONLoader jsonLoader = new JSONLoader(getContext());
        eventsList = new EventsList();
        eventsList.setEventList(jsonLoader.loadEvents());
        categories = jsonLoader.loadCategories();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ArrayList<Event> selectedEvents = new ArrayList<>();
        ArrayList<Event> events = eventsList.getEventList();

        androidx.appcompat.widget.Toolbar toolbar = view.findViewById(R.id.fragment_news_toolbar);
        toolbar.inflateMenu(R.menu.news_toolbar_menu);
        if(getActivity() != null) {
            final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            toolbar.setOnMenuItemClickListener(item -> {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_main_fragment_main, new FilterFragment(categories, selectedCategories));
                fragmentTransaction.addToBackStack("FILTER");
                fragmentTransaction.commit();
                return false;
            });
        }

        RecyclerView recyclerView = view.findViewById(R.id.fragment_news_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());

        if (categories != null && events != null) {
            selectedCategories = (ArrayList<String>) categories.clone();
        }
        NewsFragmentRecyclerAdapter adapter = new NewsFragmentRecyclerAdapter(events, selectedCategories, getFragmentManager(), getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setSelectedCategories(ArrayList<String> selectedCategories) {
        this.selectedCategories = selectedCategories;
    }
}
