package com.example.layouts.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.App;
import com.example.layouts.R;
import com.example.layouts.interfaces.ICategory;
import com.example.layouts.interfaces.OnFragmentActionListener;
import com.example.layouts.util.adapters.FilterFragmentRecyclerAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FilterFragment extends Fragment {

    private ArrayList<String> selectedCategories;

    public FilterFragment() {
        SharedPreferences sharedPreferences = App.getSharedPreferences();
        Set<String> categoriesSet = sharedPreferences.getStringSet("selectedCategoriesSetKey", new HashSet<>());
        selectedCategories = new ArrayList<>(categoriesSet);
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
        if(getFragmentManager() != null) {
            toolbar.setNavigationOnClickListener(v -> onBack());

            toolbar.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.filter_menu_item_ok) {
                    if(getActivity() instanceof ICategory) {

                        if(selectedCategories.size() != 0) {
                            ((ICategory) getActivity()).onSaveCategories(selectedCategories);
                            onBack();
                        }else{
                            Toast.makeText(getActivity(), "Не выбрана ни одна категория", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                return false;
            });
        }
        RecyclerView recyclerView = view.findViewById(R.id.fragment_filter_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new FilterFragmentRecyclerAdapter(App.getJSONLoader().loadCategories(), selectedCategories));
    }

    private void onBack(){
        if(getActivity() instanceof OnFragmentActionListener){
            ((OnFragmentActionListener) getActivity()).onFragmentBackStack();
        }
    }
}
