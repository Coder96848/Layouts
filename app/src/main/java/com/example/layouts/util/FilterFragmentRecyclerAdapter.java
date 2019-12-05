package com.example.layouts.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.R;
import com.example.layouts.model.ItemHelpData;

import java.util.List;

public class FilterFragmentRecyclerAdapter extends RecyclerView.Adapter<FilterFragmentRecyclerAdapter.FilterItemViewHolder> {

    private List<ItemHelpData> items;

    public FilterFragmentRecyclerAdapter() {
    }

    public FilterFragmentRecyclerAdapter(List<ItemHelpData> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public FilterItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_filter_recycler_view_item, parent, false);
        return new FilterItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterItemViewHolder holder, int position) {
        holder.textView.setText(items.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class FilterItemViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        Switch aSwitch;
        public FilterItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.fragment_filter_recycler_view_item_text_view);
            aSwitch = itemView.findViewById(R.id.fragment_filter_recycler_view_item_switch);
        }
    }
}
