package com.example.layouts.interfaces;

import java.util.ArrayList;

public interface ICategory {
    void onSaveCategories(ArrayList<String> selectedCategories);
    ArrayList<String> onLoadCategories();
}
