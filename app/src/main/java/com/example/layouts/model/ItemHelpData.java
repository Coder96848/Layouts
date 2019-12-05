package com.example.layouts.model;

import com.example.layouts.R;

import java.util.ArrayList;
import java.util.List;

public final class ItemHelpData {

    private static ItemHelpData instance;
    private String title;
    private int imageId;

    private static List<ItemHelpData> items;

    private ItemHelpData() {
    }

    private ItemHelpData(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }

    public static ItemHelpData getInstance() {
        if (instance == null) {
            instance = new ItemHelpData();
            initializeData();
        }
        return instance;
    }

    public String getTitle() {
        return title;
    }

    public int getImageId() {
        return imageId;
    }

    public List<ItemHelpData> getItems() {
        return items;
    }

    private static List<ItemHelpData> initializeData(){
        items = new ArrayList<>();
        items.add(new ItemHelpData("Дети", R.drawable.child_image));
        items.add(new ItemHelpData("Взрослые",  R.drawable.adult_image));
        items.add(new ItemHelpData("Пожилые",  R.drawable.old_image));
        items.add(new ItemHelpData("Животные",  R.drawable.animal_image));
        items.add(new ItemHelpData("Мероприятия", R.drawable.event_image));

        return items;
    }


}
