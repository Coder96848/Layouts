package com.example.layouts.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.App;
import com.example.layouts.R;
import com.example.layouts.util.adapters.HelpFragmentRecyclerAdapter;
import com.example.layouts.util.load.IntentServiceLoader;

import java.util.ArrayList;

public class HelpFragment extends Fragment {

    private ArrayList<String> categories;
    private BroadcastReceiver broadcastReceiver;
    private RecyclerView recyclerView;

    public HelpFragment() {
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        // load categories with AsyncTask
        App.getAsyncTaskLoader().getCategories((categories) -> {
            view.findViewById(R.id.fragment_help_progress_bar).setVisibility(View.GONE);
            this.categories = categories;
            setCategories();
        });

        // load categories with Executor
        App.getExecutorLoader().getCategories((categories) -> view.post(() -> {
            view.findViewById(R.id.fragment_help_progress_bar).setVisibility(View.GONE);
            this.categories = categories;
            setCategories();
        }));

        //load categories with IntentService
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getIntExtra(IntentServiceLoader.MESSAGE_TYPE, -1) == IntentServiceLoader.MESSAGE_TYPE_CATEGORIES) {
                    view.findViewById(R.id.fragment_help_progress_bar).setVisibility(View.GONE);
                    if(intent.getStringArrayListExtra(IntentServiceLoader.LOAD_CATEGORIES) != null) {
                        categories = intent.getStringArrayListExtra(IntentServiceLoader.LOAD_CATEGORIES);
                    }
                    setCategories();
                }
            }
        };
        LocalBroadcastManager.getInstance(App.getContext()).registerReceiver(broadcastReceiver, new IntentFilter(IntentServiceLoader.BROADCAST));
        Intent intent = new Intent(getContext(), IntentServiceLoader.class);
        if (categories != null){
            intent.putExtra(IntentServiceLoader.NOT_NULL_CATEGORIES, false);
        }
        intent.putExtra(IntentServiceLoader.MESSAGE_TYPE, IntentServiceLoader.MESSAGE_TYPE_CATEGORIES);
        App.getContext().startService(intent);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        androidx.appcompat.widget.Toolbar toolbar = view.findViewById(R.id.fragment_help_toolbar);
        toolbar.inflateMenu(R.menu.help_toolbar_menu);

        recyclerView = view.findViewById(R.id.fragment_help_category_help_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), getResources().getInteger(R.integer.help_column_count));
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroy() {
        App.getAsyncTaskLoader().stopCategories();
        App.getExecutorLoader().stopCategories();

        if(broadcastReceiver != null)
            LocalBroadcastManager.getInstance(App.getContext()).unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    private void setCategories(){
        HelpFragmentRecyclerAdapter adapter = new HelpFragmentRecyclerAdapter(categories);
        recyclerView.setAdapter(adapter);
    }
}
