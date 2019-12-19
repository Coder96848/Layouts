package com.example.layouts.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EventsList implements Parcelable {

    @SerializedName("EventsList")
    private ArrayList<Event> eventList;

    public EventsList() {
    }

    public ArrayList<Event> getEventList() {
        return eventList;
    }

    public void setEventList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.eventList);
    }

    protected EventsList(Parcel in) {
        this.eventList = new ArrayList<>();
        in.readList(this.eventList, Event.class.getClassLoader());
    }

    public static final Parcelable.Creator<EventsList> CREATOR = new Parcelable.Creator<EventsList>() {
        @Override
        public EventsList createFromParcel(Parcel source) {
            return new EventsList(source);
        }

        @Override
        public EventsList[] newArray(int size) {
            return new EventsList[size];
        }
    };
}
