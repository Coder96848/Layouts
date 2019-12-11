package com.example.layouts.util;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.layouts.model.Event;

import java.util.List;

public class DiffUtilsEvents extends DiffUtil.Callback {

    List<Event> oldEventsList;
    List<Event> newEventsList;

    public DiffUtilsEvents(List<Event> newEventsList, List<Event> oldEventsList) {
        this.newEventsList = newEventsList;
        this.oldEventsList = oldEventsList;
    }

    @Override
    public int getOldListSize() {
        return oldEventsList.size();
    }

    @Override
    public int getNewListSize() {
        return newEventsList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldEventsList.get(oldItemPosition).getId() == newEventsList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldEventsList.get(oldItemPosition).equals(newEventsList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
