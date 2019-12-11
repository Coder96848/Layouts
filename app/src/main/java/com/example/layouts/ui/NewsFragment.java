package com.example.layouts.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.interfaces.ICategory;
import com.example.layouts.R;
import com.example.layouts.model.Event;
import com.example.layouts.model.EventsList;
import com.example.layouts.util.DiffUtilsEvents;
import com.example.layouts.util.adapters.NewsFragmentRecyclerAdapter;
import com.example.layouts.util.json.JSONLoader;

import org.threeten.bp.LocalDate;
import org.threeten.bp.Period;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NewsFragment extends Fragment implements ICategory {

    private static final String APP_PREFERENCES = "appSettings";

    private ArrayList<String> categories;
    private ArrayList<String> selectedCategories;
    private EventsList eventsList;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private NewsFragmentRecyclerAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JSONLoader jsonLoader = new JSONLoader(getContext());
        eventsList = new EventsList();
        eventsList.setEventList(jsonLoader.loadEvents());
        categories = jsonLoader.loadCategories();
        sharedPreferences = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        androidx.appcompat.widget.Toolbar toolbar = view.findViewById(R.id.fragment_news_toolbar);
        toolbar.inflateMenu(R.menu.news_toolbar_menu);
        if(getActivity() != null) {
            final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            toolbar.setOnMenuItemClickListener(item -> {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_main_fragment_main, new FilterFragment(categories, selectedCategories));
                fragmentTransaction.addToBackStack("FILTER");
                fragmentTransaction.commit();
                return false;
            });
        }

        recyclerView = view.findViewById(R.id.fragment_news_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = (NewsFragmentRecyclerAdapter) recyclerView.getAdapter();
        if (categories != null && eventsList.getEventList() != null) {
            if(selectedCategories == null) {
                selectedCategories = onLoadCategories();
                if(selectedCategories.size() == 0) {
                    selectedCategories = (ArrayList<String>) categories.clone();
                }
            }
            setSelectedCategories(selectedCategories);
        }
    }

    @Override
    public void setSelectedCategories(ArrayList<String> selectedCategories) {
        ArrayList<Event> selectedEvents = new ArrayList<>();

        for (Event event: eventsList.getEventList()) {
            for (String category: selectedCategories) {
                if (event.getCategories().contains(category)) {
                    selectedEvents.add(event);
                    break; }
            }
        }

        if (adapter == null) {
            recyclerView.setAdapter(new NewsFragmentRecyclerAdapter(selectedEvents, getFragmentManager(), getContext(), getDate()));
        } else {
            DiffUtil.DiffResult newsDiffResult = DiffUtil.calculateDiff(new DiffUtilsEvents(adapter.getSelectedEvents(), selectedEvents));
            adapter.setSelectedEvents(selectedEvents);
            newsDiffResult.dispatchUpdatesTo(adapter);
            this.selectedCategories = selectedCategories;
            onSaveCategories(selectedCategories);
        }
    }

    private void onSaveCategories(ArrayList<String> selectedCategories){
        Set<String> categoriesSet = new HashSet<>(selectedCategories);
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putStringSet("selectedCategoriesSetKey", categoriesSet);
        e.apply();
    }

    private ArrayList<String> onLoadCategories() {
        ArrayList<String> selectedCategories = new ArrayList<>();
        Set<String> categoriesSet = sharedPreferences.getStringSet("selectedCategoriesSetKey", new HashSet<>());
        selectedCategories.addAll(categoriesSet);
        return selectedCategories;
    }

    private ArrayList<String> getDate(){

        ArrayList<Event> events = eventsList.getEventList();
        String[] months = getResources().getStringArray(R.array.months);
        String[] formatDay = getResources().getStringArray(R.array.formatDay);

        ArrayList<String> resultStringList = new ArrayList<>();
        DateTimeFormatter formatterFullDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatterDateTypeOne = DateTimeFormatter.ofPattern("dd.MM");
        DateTimeFormatter formatterDateTypeTwo = DateTimeFormatter.ofPattern("dd, yyyy");
        LocalDate currentDate = LocalDate.now();

        for (Event event : events){
            String strDate;
            String dayWord;
            LocalDate eventBegin = LocalDate.parse(event.getBeginEvent(), formatterFullDate);
            LocalDate eventEnd= LocalDate.parse(event.getEndEvent(), formatterFullDate);

            switch (Period.between(currentDate, eventEnd).getDays()){
                case(1): dayWord = formatDay[0]; break;
                case(2):
                case(3):
                case(4): dayWord = formatDay[1]; break;
                default: dayWord = formatDay[2];
            }

            if (currentDate.equals(eventBegin) || currentDate.isAfter(eventBegin) && currentDate.isBefore(eventEnd)){
                strDate = getString(R.string.remained) + " "
                        + Period.between(currentDate, eventEnd).getDays() + " "
                        + dayWord + " "
                        + getString(R.string.left_bracket)
                        + eventBegin.format(formatterDateTypeOne) + " "
                        + getString(R.string.dash) + " "
                        + eventEnd.format(formatterDateTypeOne)
                        + getString(R.string.right_bracket);
            }else if(currentDate.isAfter(eventEnd)) {
                strDate = getString(R.string.event_be_over) + " "
                        + eventBegin.format(formatterDateTypeOne) + " "
                        + getString(R.string.dash) + " "
                        + eventEnd.format(formatterDateTypeOne);
            }else {
                strDate = months[eventBegin.getMonthValue() - 1] + " " + eventBegin.format(formatterDateTypeTwo);
            }

            resultStringList.add(strDate);
        }

        return resultStringList;

    }
}
