package com.example.layouts.util;

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

    private List<ItemHelpData> cards;

    public HelpFragmentRecyclerAdapter(List<ItemHelpData> cards){
        this.cards = cards;
    }

    @NonNull
    @Override
    public HelpItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_help_recycler_view_item, parent, false);
        return new HelpItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HelpItemViewHolder holder, int position) {
        holder.helpTitleTextView.setText(cards.get(position).getTitle());
        holder.helpImageView.setImageResource(cards.get(position).getImageId());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class HelpItemViewHolder extends RecyclerView.ViewHolder {
        TextView helpTitleTextView;
        ImageView helpImageView;

        public HelpItemViewHolder(@NonNull View itemView) {
            super(itemView);
            helpTitleTextView = itemView.findViewById(R.id.help_item_card_view_text_view);
            helpImageView = itemView.findViewById(R.id.help_item_card_view_image_view);
        }
    }
}
