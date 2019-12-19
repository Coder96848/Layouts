package com.example.layouts.util.load;

import android.content.Context;

import com.example.layouts.model.Categories;
import com.example.layouts.model.EventsList;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JSONLoader {

    private final String CATEGORIES_FILE_NAME = "categories.json";
    private final String EVENTS_FILE_NAME = "events.json";
    private Context context;

    public JSONLoader(Context context) {
        this.context = context;
    }

    public ArrayList<String> loadCategories(){
        Gson gson = new Gson();
        Categories categories = gson.fromJson(importFromJSON(CATEGORIES_FILE_NAME), Categories.class);
        return categories.getCategoriesList();
    }

    public EventsList loadEvents(){
        Gson gson = new Gson();
        return gson.fromJson(importFromJSON(EVENTS_FILE_NAME), EventsList.class);
    }

    public String importFromJSON(String fileName) {

        InputStreamReader streamReader = null;
        InputStream inputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        try{
            inputStream = context.getAssets().open(fileName);
            streamReader = new InputStreamReader(inputStream);
            char[] chars = new char[inputStream.available()];
            streamReader.read(chars);
            for (char aChar : chars) {
                stringBuilder.append(aChar);
            }
            return stringBuilder.toString().trim();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
