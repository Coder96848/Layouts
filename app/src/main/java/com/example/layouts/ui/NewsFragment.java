package com.example.layouts.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.layouts.App;
import com.example.layouts.R;
import com.example.layouts.interfaces.ICategory;
import com.example.layouts.interfaces.OnFragmentActionListener;
import com.example.layouts.model.Event;
import com.example.layouts.model.EventsList;
import com.example.layouts.util.DiffUtilsEvents;
import com.example.layouts.util.adapters.NewsFragmentRecyclerAdapter;
import com.example.layouts.util.load.IntentServiceLoader;

import org.threeten.bp.LocalDate;
import org.threeten.bp.Period;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    private ArrayList<String> selectedCategories;
    private EventsList eventsList;
    private ArrayList<String> categories;
    private RecyclerView recyclerView;
    private ICategory listener;
    private BroadcastReceiver broadcastReceiver;

    public NewsFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        // load events with AsyncTask
        App.getAsyncTaskLoader().getEvents((events) -> {
            view.findViewById(R.id.fragment_news_progress_bar).setVisibility(View.GONE);
            eventsList = events;
            setNews();
        });

        // load events with Executor
        App.getExecutorLoader().getEvents((events) -> view.post(() -> {
            view.findViewById(R.id.fragment_news_progress_bar).setVisibility(View.GONE);
            eventsList = events;
            setNews();
        }));

        //load events with IntentService
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getIntExtra(IntentServiceLoader.MESSAGE_TYPE, -1) == IntentServiceLoader.MESSAGE_TYPE_EVENTS) {
                    view.findViewById(R.id.fragment_news_progress_bar).setVisibility(View.GONE);
                    if(intent.getParcelableExtra(IntentServiceLoader.LOAD_EVENTS) != null) {
                        eventsList = intent.getParcelableExtra(IntentServiceLoader.LOAD_EVENTS);
                    }
                    setNews();
                }
            }
        };
        LocalBroadcastManager.getInstance(App.getContext()).registerReceiver(broadcastReceiver, new IntentFilter(IntentServiceLoader.BROADCAST));
        Intent intent = new Intent(getContext(), IntentServiceLoader.class);
        if (eventsList != null){
            intent.putExtra(IntentServiceLoader.NOT_NULL_EVENTS, false);
        }
        intent.putExtra(IntentServiceLoader.MESSAGE_TYPE, IntentServiceLoader.MESSAGE_TYPE_EVENTS);
        App.getContext().startService(intent);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        androidx.appcompat.widget.Toolbar toolbar = view.findViewById(R.id.fragment_news_toolbar);
        toolbar.inflateMenu(R.menu.news_toolbar_menu);
        if(getActivity() != null) {
            toolbar.setOnMenuItemClickListener(item -> {
                ((OnFragmentActionListener) getActivity()).setFragment(new FilterFragment(), "FILTER");
                return false;
            });
        }

        recyclerView = view.findViewById(R.id.fragment_news_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onDestroy() {
        App.getAsyncTaskLoader().stopEvents();
        App.getExecutorLoader().stopEvents();

        if(broadcastReceiver != null)
            LocalBroadcastManager.getInstance(App.getContext()).unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    private void setNews(){
        //categories = App.getAsyncTaskLoader().getCategoriesWithoutAsyncTask();
        //categories = App.getExecutorLoader().getCategoriesWithoutExecutor();
        categories = IntentServiceLoader.getCategoriesWithoutIntentService();
        if(getActivity() instanceof ICategory) {
            listener = (ICategory) getActivity();
            if (categories != null && eventsList.getEventList() != null) {
                selectedCategories = listener.onLoadCategories();
                if (selectedCategories.size() == 0) {
                    selectedCategories.addAll(categories);
                }
                setSelectedCategories(selectedCategories);
            }
        }
    }

    private void setSelectedCategories(ArrayList<String> selectedCategories) {
        ArrayList<Event> selectedEvents = new ArrayList<>();

        for (Event event: eventsList.getEventList()) {
            for (String category: selectedCategories) {
                if (event.getCategories().contains(category)) {
                    selectedEvents.add(event);
                    break; }
            }
        }

        NewsFragmentRecyclerAdapter adapter = (NewsFragmentRecyclerAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            recyclerView.setAdapter(new NewsFragmentRecyclerAdapter(selectedEvents, getActivity(), getDate()));
        } else {
            DiffUtil.DiffResult newsDiffResult = DiffUtil.calculateDiff(new DiffUtilsEvents(adapter.getSelectedEvents(), selectedEvents));
            adapter.setSelectedEvents(selectedEvents);
            newsDiffResult.dispatchUpdatesTo(adapter);
            this.selectedCategories = selectedCategories;
            listener.onSaveCategories(selectedCategories);
        }
    }

    private ArrayList<String> getDate(){

        ArrayList<Event> events = eventsList.getEventList();
        String[] months = getResources().getStringArray(R.array.months);

        ArrayList<String> resultStringList = new ArrayList<>();
        DateTimeFormatter formatterFullDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatterDateTypeOne = DateTimeFormatter.ofPattern("dd.MM");
        DateTimeFormatter formatterDateTypeTwo = DateTimeFormatter.ofPattern("dd, yyyy");
        LocalDate currentDate = LocalDate.now();

        for (Event event : events){
            String strDate;
            LocalDate eventBegin = LocalDate.parse(event.getBeginEvent(), formatterFullDate);
            LocalDate eventEnd= LocalDate.parse(event.getEndEvent(), formatterFullDate);
            int days = Period.between(currentDate, eventEnd).getDays();

            if (currentDate.equals(eventBegin) || currentDate.isAfter(eventBegin) && currentDate.isBefore(eventEnd)){
                strDate = getString(R.string.remained) + " "
                        + days + " "
                        + getResources().getQuantityString(R.plurals.plural_day, days) + " "
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
