package com.example.layouts.util.load;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.layouts.App;
import com.example.layouts.model.EventsList;

import java.util.ArrayList;

public class IntentServiceLoader extends IntentService {

    private static final String SERVICE_NAME = "INTENT_SERVICE_LOADER_NAME";

    public static final String MESSAGE_TYPE = "INTENT_SERVICE_LOADER_BROADCAST_MESSAGE_TYPE";
    public static final int MESSAGE_TYPE_CATEGORIES = 0;
    public static final int MESSAGE_TYPE_EVENTS = 1;
    public static final String BROADCAST = "INTENT_SERVICE_LOADER_BROADCAST";
    public static final String LOAD_CATEGORIES = "INTENT_SERVICE_LOADER_CATEGORIES";
    public static final String LOAD_EVENTS = "INTENT_SERVICE_LOADER_EVENTS";
    public static final String NOT_NULL_EVENTS = "NOT_NULL_EVENTS";
    public static final String NOT_NULL_CATEGORIES = "NOT_NULL_CATEGORIES";

    private static ArrayList<String> categories;
    private EventsList eventsList;
    private static JSONLoader jsonLoader;

    public IntentServiceLoader() {
        super(SERVICE_NAME);
        jsonLoader = App.getJSONLoader();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Intent broadcastIntent = new Intent(BROADCAST);
        Bundle bundle = new Bundle();
        int type = intent.getIntExtra(MESSAGE_TYPE, MESSAGE_TYPE_CATEGORIES);
        switch (type) {
            case MESSAGE_TYPE_CATEGORIES:
                if (intent.getBooleanExtra(NOT_NULL_CATEGORIES, true)) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    categories = jsonLoader.loadCategories();
                    bundle.putStringArrayList(LOAD_CATEGORIES, categories);
                    broadcastIntent.putExtras(bundle);
                }
                break;
            case MESSAGE_TYPE_EVENTS:
                if (intent.getBooleanExtra(NOT_NULL_EVENTS, true)) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    eventsList = jsonLoader.loadEvents();
                    bundle.putParcelable(LOAD_EVENTS, eventsList);
                    broadcastIntent.putExtras(bundle);
                }
                break;
            default:
                return;
        }
        bundle.putInt(MESSAGE_TYPE, type);
        broadcastIntent.putExtras(bundle);
        LocalBroadcastManager.getInstance(App.getContext()).sendBroadcast(broadcastIntent);
    }

    public static ArrayList<String> getCategoriesWithoutIntentService() {
        categories = jsonLoader.loadCategories();
        return categories;
    }
}
