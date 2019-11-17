package com.example.layouts.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchResultData {

    private String title;
    private List<SearchResultData> titles;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SearchResultData() {
    }

    public SearchResultData(String title) {
        this.title = title;
    }

    public List<SearchResultData> initializeData(){
        titles = new ArrayList<>();
        titles.add(new SearchResultData("\"Дети цветы жизни\""));
        titles.add(new SearchResultData("\"Фонд ветеранов\""));
        titles.add(new SearchResultData("\"Кошачья радость\""));
        titles.add(new SearchResultData("\"Деревянный домик\""));
        titles.add(new SearchResultData("\"Утренняя неожиданность\""));

        Collections.shuffle(titles);

        return titles;
    }


}
