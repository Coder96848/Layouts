package com.example.layouts.util.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.R;

import java.util.ArrayList;

public class HelpFragmentRecyclerAdapter extends RecyclerView.Adapter<HelpFragmentRecyclerAdapter.HelpItemViewHolder> {

    private ArrayList<String> categories;

    public HelpFragmentRecyclerAdapter(ArrayList<String> categories){
        this.categories = categories;
    }

    @NonNull
    @Override
    public HelpItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_help_recycler_view_item, parent, false);
        return new HelpItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HelpItemViewHolder holder, int position) {
        switch (categories.get(position)){
            case "Дети":
                holder.helpTitleTextView.setText(categories.get(position));
                holder.helpImageView.setImageResource(R.drawable.child_image);
                break;
            case "Взрослые":
                holder.helpTitleTextView.setText(categories.get(position));
                holder.helpImageView.setImageResource( R.drawable.adult_image);
                break;
            case "Пожилые":
                holder.helpTitleTextView.setText(categories.get(position));
                holder.helpImageView.setImageResource(R.drawable.old_image);
                break;
            case "Животные":
                holder.helpTitleTextView.setText(categories.get(position));
                holder.helpImageView.setImageResource(R.drawable.animal_image);
                break;
            case "Мероприятия":
                holder.helpTitleTextView.setText(categories.get(position));
                holder.helpImageView.setImageResource(R.drawable.event_image);
                break;

        }

    }

    @Override
    public int getItemCount() {
        return categories.size();
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
