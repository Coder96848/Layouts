package com.example.layouts.model;

import com.example.layouts.R;

import java.util.ArrayList;
import java.util.List;

public class CardsHelpData {

    private String title;
    private int imageId;
    private List<CardsHelpData> cards;

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

    public CardsHelpData() {
    }

    public CardsHelpData(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }

    public List<CardsHelpData> initializeData(){
        cards = new ArrayList<>();
        cards.add(new CardsHelpData("Дети", R.drawable.child_image));
        cards.add(new CardsHelpData("Взрослые",  R.drawable.adult_image));
        cards.add(new CardsHelpData("Пожилые",  R.drawable.old_image));
        cards.add(new CardsHelpData("Животные",  R.drawable.animal_image));
        cards.add(new CardsHelpData("Мероприятия", R.drawable.event_image));

        return cards;
    }


}
