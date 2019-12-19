package com.example.layouts.util.load;

import com.example.layouts.model.EventsList;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorLoader {

    private ArrayList<String> categories;
    private EventsList eventsList;
    private JSONLoader jsonLoader;
    private ExecutorService executor;
    private IOnLoadCategoriesHandler onLoadCategoriesHandler;
    private IOnLoadEventsHandler onLoadEventsHandler;

    public ExecutorLoader(JSONLoader jsonLoader) {
        this.jsonLoader = jsonLoader;
        executor = Executors.newSingleThreadExecutor();
    }

    public interface IOnLoadCategoriesHandler {
        void onLoadCategories(ArrayList<String> categories);
    }

    public interface IOnLoadEventsHandler {
        void onLoadEvents(EventsList eventsList);
    }

    public void getEvents(IOnLoadEventsHandler handler) {
        this.onLoadEventsHandler = handler;
        executor.execute(() -> {
            if (this.onLoadEventsHandler != null) {
                if (eventsList == null) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    eventsList = jsonLoader.loadEvents();
                }
                this.onLoadEventsHandler.onLoadEvents(eventsList);
            }
        });
    }

    public void getCategories(IOnLoadCategoriesHandler handler) {
        this.onLoadCategoriesHandler = handler;
        executor.execute(() -> {
            if (this.onLoadCategoriesHandler != null) {
                if (categories == null) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    categories = jsonLoader.loadCategories();
                }
                this.onLoadCategoriesHandler.onLoadCategories(categories);
            }
        });
    }

    public ArrayList<String> getCategoriesWithoutExecutor() {
        categories = jsonLoader.loadCategories();
        return categories;
    }

    public void stopCategories() {
        onLoadCategoriesHandler = null;
    }

    public void stopEvents() {
        onLoadEventsHandler = null;
    }
}
