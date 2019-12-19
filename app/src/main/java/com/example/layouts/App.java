package com.example.layouts;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.layouts.util.load.AsyncTaskLoader;
import com.example.layouts.util.load.ExecutorLoader;
import com.example.layouts.util.load.JSONLoader;
import com.jakewharton.threetenabp.AndroidThreeTen;

public class App extends Application {

    private static final String APP_PREFERENCES = "appSettings";

    private static JSONLoader jsonLoader;
    private static AsyncTaskLoader asyncTaskLoader;
    private static ExecutorLoader executorLoader;
    private static SharedPreferences sharedPreferences;
    private static Context context;

    public static JSONLoader getJSONLoader() {
        return jsonLoader;
    }

    public static AsyncTaskLoader getAsyncTaskLoader() {
        return asyncTaskLoader;
    }

    public static ExecutorLoader getExecutorLoader() {
        return executorLoader;
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        jsonLoader = new JSONLoader(this);
        asyncTaskLoader = new AsyncTaskLoader(jsonLoader);
        executorLoader = new ExecutorLoader(jsonLoader);
        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        context = getApplicationContext();
        AndroidThreeTen.init(this);
    }

}
