package com.example.layouts;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.layouts.util.json.JSONLoader;
import com.jakewharton.threetenabp.AndroidThreeTen;

public class App extends Application {

    private static final String APP_PREFERENCES = "appSettings";

    private static JSONLoader jsonLoader;
    private static SharedPreferences sharedPreferences;

    public static JSONLoader getJSONLoader() {
        return jsonLoader;
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        jsonLoader = new JSONLoader(this);
        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        AndroidThreeTen.init(this);
    }
}
