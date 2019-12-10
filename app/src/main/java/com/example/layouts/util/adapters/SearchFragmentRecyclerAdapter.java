package com.example.layouts.util.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.R;
import com.example.layouts.model.SearchResultData;

import java.util.List;

public class SearchFragmentRecyclerAdapter extends RecyclerView.Adapter<SearchFragmentRecyclerAdapter.SearchResultViewHolder> {

    List<SearchResultData> resultData;

    public SearchFragmentRecyclerAdapter(List<SearchResultData> resultData) {
        this.resultData = resultData;
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_search_recycler_view_item, parent, false);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
        holder.resultSearchTextView.setText(resultData.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return resultData.size();
    }

    public class SearchResultViewHolder extends RecyclerView.ViewHolder {

        TextView resultSearchTextView;
        public SearchResultViewHolder(@NonNull View itemView) {
            super(itemView);
            resultSearchTextView = itemView.findViewById(R.id.fragment_search_recycler_view_item_text_view);
        }
    }
}
