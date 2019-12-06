package com.example.layouts.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.R;
import com.example.layouts.model.ItemNewsData;

import java.util.List;

public class NewsFragmentRecyclerAdapter extends RecyclerView.Adapter<NewsFragmentRecyclerAdapter.NewsItemViewHolder> {

    private List<ItemNewsData> news;
    //private Context context;

    public NewsFragmentRecyclerAdapter(List<ItemNewsData> news/*, Context context*/) {
        this.news = news;
        //this.context = context;
    }

    @NonNull
    @Override
    public NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_news_recycler_view_item, parent, false);
        return new NewsFragmentRecyclerAdapter.NewsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsItemViewHolder holder, int position) {
        holder.textViewName.setText(news.get(position).getNameNews());
        holder.textViewDescription.setText(news.get(position).getDescriptionNews());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imageViewMain;
        TextView textViewName;
        TextView textViewDescription;
        Button button;

        public NewsItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.news_item_card_view);
            imageViewMain = itemView.findViewById(R.id.news_item_card_view_image_view_main);
            textViewName = itemView.findViewById(R.id.news_item_card_view_text_view_name);
            textViewDescription = itemView.findViewById(R.id.news_item_card_view_text_view_description);
            button = itemView.findViewById(R.id.news_item_card_view_button);

        }
    }
}
