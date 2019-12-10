package com.example.layouts.util.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.R;
import com.example.layouts.model.ItemHelpData;

import java.util.List;

public class HelpFragmentRecyclerAdapter extends RecyclerView.Adapter<HelpFragmentRecyclerAdapter.HelpItemViewHolder> {

    private List<ItemHelpData> items;

    public HelpFragmentRecyclerAdapter(List<ItemHelpData> items){
        this.items = items;
    }

    @NonNull
    @Override
    public HelpItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_help_recycler_view_item, parent, false);
        return new HelpItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HelpItemViewHolder holder, int position) {
        holder.helpTitleTextView.setText(items.get(position).getTitle());
        holder.helpImageView.setImageResource(items.get(position).getImageId());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class HelpItemViewHolder extends RecyclerView.ViewHolder {
        TextView helpTitleTextView;
        ImageView helpImageView;

        public HelpItemViewHolder(@NonNull View itemView) {
            super(itemView);
            helpTitleTextView = itemView.findViewById(R.id.help_item_text_view);
            helpImageView = itemView.findViewById(R.id.help_item_image_view);
        }
    }
}
