package com.example.layouts.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.layouts.interfaces.IMain;
import com.example.layouts.R;
import com.example.layouts.model.Event;

public class NewsDetailsFragment extends Fragment {

    private Event event;
    private String date;

    public NewsDetailsFragment(Event event, String date) {
        this.event = event;
        this.date = date;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((IMain) getActivity()).setBottomNavigationGone();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        androidx.appcompat.widget.Toolbar toolbar = view.findViewById(R.id.fragment_news_details_toolbar);
        toolbar.inflateMenu(R.menu.news_details_toolbar_menu);
        toolbar.setTitle(event.getHeader());
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(v -> getFragmentManager().popBackStack());

        TextView textViewHeader = view.findViewById(R.id.fragment_news_details_scroll_view_text_view_news_name);
        TextView textViewDate = view.findViewById(R.id.fragment_news_details_scroll_view_text_view_date);
        TextView textViewOrganization = view.findViewById(R.id.fragment_news_details_scroll_view_text_view_organization);
        TextView textViewAddress = view.findViewById(R.id.fragment_news_details_scroll_view_text_view_nav);
        TextView textViewPhone = view.findViewById(R.id.fragment_news_details_scroll_view_text_view_phone);
        TextView textViewDescription = view.findViewById(R.id.fragment_news_details_scroll_view_text_view_detail_1);
        TextView textView = view.findViewById(R.id.fragment_news_details_scroll_view_text_view_detail_2);
        TextView textViewLikes = view.findViewById(R.id.news_details_likes_count);
        TextView textViewEmail = view.findViewById(R.id.fragment_news_details_scroll_view_text_view_link);
        TextView textViewWebsite = view.findViewById(R.id.fragment_news_details_scroll_view_text_view_link_organization);


        textViewHeader.setText(event.getHeader());
        textViewDate.setText(date);
        textViewOrganization.setText(event.getOrganization());
        textViewAddress.setText(event.getAddress());

        String stringPhone = "";
        for (String phone : event.getPhones()) {
            if (event.getPhones().size() > 1){
                stringPhone = stringPhone + "\n" + phone;
            }else{
                stringPhone = phone;
            }
        }
        textViewPhone.setText(stringPhone);
        textViewDescription.setText(event.getDescription());

        if(event.getLikesCount() >  5) {
            textViewLikes.setText("+" + (event.getLikesCount()-5));
        }else {
            textViewLikes.setText(String.valueOf(event.getLikesCount()));
        }

        textViewEmail.setOnClickListener((v) -> {
            try {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", event.getEmail(), null));
                startActivity(Intent.createChooser(emailIntent, "Написать письмо"));
            } catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        textViewWebsite.setOnClickListener((v) -> {
            try {
                Intent intentSite = new Intent(Intent.ACTION_VIEW);
                intentSite.setData(Uri.parse(event.getWebsite()));
                startActivity(intentSite);
            } catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        ((IMain) getActivity()).setBottomNavigationVisible();
    }
}
