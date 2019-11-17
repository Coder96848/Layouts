package com.example.layouts.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.R;
import com.example.layouts.model.CardHelpData;

import java.util.List;

public class HelpFragmentRecyclerAdapter extends RecyclerView.Adapter<HelpFragmentRecyclerAdapter.CardHelpViewHolder> {

    private List<CardHelpData> cards;

    public HelpFragmentRecyclerAdapter(List<CardHelpData> cards){
        this.cards = cards;
    }

    @NonNull
    @Override
    public CardHelpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_help_recycler_view_item, parent, false);
        return new CardHelpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHelpViewHolder holder, int position) {
        holder.helpTitleTextView.setText(cards.get(position).getTitle());
        holder.helpImageView.setImageResource(cards.get(position).getImageId());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class CardHelpViewHolder extends RecyclerView.ViewHolder {
        CardView cardHelpView;
        TextView helpTitleTextView;
        ImageView helpImageView;

        public CardHelpViewHolder(@NonNull View itemView) {
            super(itemView);
            cardHelpView = itemView.findViewById(R.id.help_item_card_view);
            helpTitleTextView = itemView.findViewById(R.id.help_item_card_view_text_view);
            helpImageView = itemView.findViewById(R.id.help_item_card_view_image_view);
        }
    }
}
