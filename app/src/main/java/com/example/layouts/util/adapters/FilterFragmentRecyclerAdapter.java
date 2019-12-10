package com.example.layouts.util.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.R;

import java.util.List;

public class FilterFragmentRecyclerAdapter extends RecyclerView.Adapter<FilterFragmentRecyclerAdapter.FilterItemViewHolder> {

    private List<String> categories;
    private List<String> selectedCategories;

    public FilterFragmentRecyclerAdapter(List<String> categories, List<String> selectedCategories) {
        this.categories = categories;
        this.selectedCategories = selectedCategories;
    }

    @NonNull
    @Override
    public FilterItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_filter_recycler_view_item, parent, false);
        return new FilterItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterItemViewHolder holder, int position) {
        holder.textView.setText(categories.get(position));
        SwitchCompat switchCategory = holder.switchCategory;
        switchCategory.setChecked(selectedCategories.contains(categories.get(position)));
        switchCategory.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) selectedCategories.add(categories.get(position));
            else selectedCategories.remove(categories.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class FilterItemViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        SwitchCompat switchCategory;
        public FilterItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.fragment_filter_recycler_view_item_text_view);
            switchCategory = itemView.findViewById(R.id.fragment_filter_recycler_view_item_switch);
        }
    }
}
