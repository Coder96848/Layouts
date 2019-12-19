package com.example.layouts.util.load;

import android.os.AsyncTask;

import com.example.layouts.model.EventsList;

import java.util.ArrayList;

public class AsyncTaskLoader {

    private ArrayList<String> categories;
    private EventsList eventsList;
    private JSONLoader jsonLoader;
    private IOnLoadCategoriesHandler onLoadCategoriesHandler;
    private IOnLoadEventsHandler onLoadEventsHandler;

    public AsyncTaskLoader(JSONLoader jsonLoader) {
        this.jsonLoader = jsonLoader;
    }

    public interface IOnLoadCategoriesHandler {
        void onLoadCategories(ArrayList<String> categories);
    }

    public interface IOnLoadEventsHandler {
        void onLoadEvents(EventsList eventsList);
    }

    private class AsyncTaskLoadEventsList extends AsyncTask<Void, Void, EventsList> {

        @Override
        protected EventsList doInBackground(Void... params) {
            if (eventsList == null){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                eventsList = jsonLoader.loadEvents();
            }
            return eventsList;
        }

        @Override
        protected void onPostExecute(EventsList events) {
            super.onPostExecute(events);
            if (onLoadEventsHandler != null) onLoadEventsHandler.onLoadEvents(events);
        }
    }

    private class categoriesLoadAsyncTask extends AsyncTask<Void, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            if (categories == null){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                categories = jsonLoader.loadCategories();
            }
            return categories;
        }

        @Override
        protected void onPostExecute(ArrayList<String> categories) {
            super.onPostExecute(categories);
            if (onLoadCategoriesHandler != null) onLoadCategoriesHandler.onLoadCategories(categories);
        }
    }

    public void getEvents(IOnLoadEventsHandler handler) {
        this.onLoadEventsHandler = handler;
        new AsyncTaskLoadEventsList().execute();
    }

    public void getCategories(IOnLoadCategoriesHandler handler) {
        this.onLoadCategoriesHandler = handler;
        new categoriesLoadAsyncTask().execute();
    }

    public ArrayList<String> getCategoriesWithoutAsyncTask() {
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
