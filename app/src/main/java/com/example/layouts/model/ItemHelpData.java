package com.example.layouts.model;

import com.example.layouts.R;

import java.util.ArrayList;
import java.util.List;

public class ItemHelpData {

    private String title;
    private int imageId;
    private List<ItemHelpData> cards;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public ItemHelpData() {
    }

    public ItemHelpData(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }

    public List<ItemHelpData> initializeData(){
        cards = new ArrayList<>();
        cards.add(new ItemHelpData("Дети", R.drawable.child_image));
        cards.add(new ItemHelpData("Взрослые",  R.drawable.adult_image));
        cards.add(new ItemHelpData("Пожилые",  R.drawable.old_image));
        cards.add(new ItemHelpData("Животные",  R.drawable.animal_image));
        cards.add(new ItemHelpData("Мероприятия", R.drawable.event_image));

        return cards;
    }


}
