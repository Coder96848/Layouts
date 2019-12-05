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

import com.example.layouts.R;
import com.example.layouts.model.ItemNewsData;
import com.example.layouts.util.NewsFragmentRecyclerAdapter;

public class NewsFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        androidx.appcompat.widget.Toolbar toolbar = view.findViewById(R.id.fragment_news_toolbar);
        toolbar.inflateMenu(R.menu.news_toolbar_menu);
        if(getActivity() != null) {
            final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.activity_main_fragment_main, new FilterFragment());
                    fragmentTransaction.commit();
                    return false;
                }
            });
        }

        ItemNewsData itemNewsData = new ItemNewsData();
        RecyclerView recyclerView = view.findViewById(R.id.fragment_news_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());

        NewsFragmentRecyclerAdapter adapter = new NewsFragmentRecyclerAdapter(itemNewsData.initializeData());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}
