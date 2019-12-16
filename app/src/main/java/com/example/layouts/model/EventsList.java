package com.example.layouts.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EventsList {

    @SerializedName("EventsList")
    private ArrayList<Event> eventList;

    public EventsList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    public ArrayList<Event> getEventList() {
        return eventList;
    }

    public void setEventList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }
}
