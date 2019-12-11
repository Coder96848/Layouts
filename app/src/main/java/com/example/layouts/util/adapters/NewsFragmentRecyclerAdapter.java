package com.example.layouts.util.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.R;
import com.example.layouts.model.Event;
import com.example.layouts.ui.NewsDetailsFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsFragmentRecyclerAdapter extends RecyclerView.Adapter<NewsFragmentRecyclerAdapter.NewsItemViewHolder> {

    private ArrayList<Event> selectedEvents;
    private Context context;
    private FragmentManager fragmentManager;
    private ArrayList<String> date;

    public NewsFragmentRecyclerAdapter(ArrayList<Event> selectedEvents, FragmentManager fragmentManager, Context context, ArrayList<String> date) {
        this.selectedEvents = selectedEvents;
        this.fragmentManager = fragmentManager;
        this.context = context;
        this.date = date;
    }

    public ArrayList<Event> getSelectedEvents() {
        return selectedEvents;
    }

    public void setSelectedEvents(ArrayList<Event> selectedEvents) {
        this.selectedEvents = selectedEvents;
    }

    @NonNull
    @Override
    public NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_news_recycler_view_item, parent, false);
        return new NewsFragmentRecyclerAdapter.NewsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsItemViewHolder holder, int position) {
        Picasso.with(context).load(R.drawable.img).into(holder.imageViewMain);
        holder.textViewName.setText(selectedEvents.get(position).getHeader());
        holder.textViewDescription.setText(selectedEvents.get(position).getDescription());
        holder.button.setText(date.get(position));
        holder.cardView.setOnClickListener(v -> openNewsDetailFragment(selectedEvents.get(position), date.get(position)));
    }

    @Override
    public int getItemCount() {
        return selectedEvents.size();
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

    private void openNewsDetailFragment(Event event, String date) {
        NewsDetailsFragment fragment = new NewsDetailsFragment(event, date);
        fragmentManager
                .beginTransaction()
                .replace(R.id.activity_main_fragment_main, fragment, "DETAIL_NEWS_FRAGMENT")
                .addToBackStack(null)
                .commit();
    }
}
