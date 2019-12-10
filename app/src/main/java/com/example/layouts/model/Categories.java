package com.example.layouts.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Categories {

    @SerializedName("CategoriesList")
    private ArrayList<String> categoriesList;

    public Categories(ArrayList<String> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public ArrayList<String> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(ArrayList<String> categoriesList) {
        this.categoriesList = categoriesList;
    }
}
