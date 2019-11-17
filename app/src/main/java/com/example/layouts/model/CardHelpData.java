package com.example.layouts.model;

import com.example.layouts.R;

import java.util.ArrayList;
import java.util.List;

public class CardHelpData {

    private String title;
    private int imageId;
    private List<CardHelpData> cards;

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

    public CardHelpData() {
    }

    public CardHelpData(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }

    public List<CardHelpData> initializeData(){
        cards = new ArrayList<>();
        cards.add(new CardHelpData("Дети", R.drawable.child_image));
        cards.add(new CardHelpData("Взрослые",  R.drawable.adult_image));
        cards.add(new CardHelpData("Пожилые",  R.drawable.old_image));
        cards.add(new CardHelpData("Животные",  R.drawable.animal_image));
        cards.add(new CardHelpData("Мероприятия", R.drawable.event_image));

        return cards;
    }


}
