package com.example.layouts.model;

import com.example.layouts.R;

import java.util.ArrayList;
import java.util.List;

public class ItemNewsData {

    private String nameNews;

    public String getDescriptionNews() {
        return descriptionNews;
    }

    private String descriptionNews;
    private int imageId;
    private List<ItemNewsData> news;

    public ItemNewsData(String nameNews, String descriptionNews) {
        this.nameNews = nameNews;
        this.descriptionNews = descriptionNews;
    }

    public ItemNewsData() {
    }

    public String getNameNews() {
        return nameNews;
    }

    public void setNameNews(String nameNews) {
        this.nameNews = nameNews;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public List<ItemNewsData> getNews() {
        return news;
    }

    public void setNews(List<ItemNewsData> news) {
        this.news = news;
    }

    public List<ItemNewsData> initializeData() {
        news = new ArrayList<>();
        news.add(new ItemNewsData("Спонсоры отремонтируют\nшколу-интернат","Дубовская школа-интернат для детей\n" +
                "с ограниченными возможностями\nздоровья стала первой в области …"));
        news.add(new ItemNewsData("Конкурс по вокальному\nпению в детском доме №6", "Дубовская школа-интернат для детей\n" +
                "с ограниченными возможностями\nздоровья стала первой в области …" ));
        return news;
    }
}
