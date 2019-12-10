package com.example.layouts.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Event {

    @SerializedName("Header")
    private String header;
    @SerializedName("BeginEvent")
    private String beginEvent;
    @SerializedName("EndEvent")
    private String endEvent;
    @SerializedName("Organization")
    private String organization;
    @SerializedName("Address")
    private String address;
    @SerializedName("Phones")
    private ArrayList<String> phones;
    @SerializedName("Email")
    private String email;
    @SerializedName("Image")
    private String image;
    @SerializedName("Description")
    private String description;
    @SerializedName("Website")
    private String website;
    @SerializedName("Categories")
    private ArrayList<String> categories;
    @SerializedName("LikesCount")
    private int likesCount;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBeginEvent() {
        return beginEvent;
    }

    public void setBeginEvent(String beginEvent) {
        this.beginEvent = beginEvent;
    }

    public String getEndEvent() {
        return endEvent;
    }

    public void setEndEvent(String endEvent) {
        this.endEvent = endEvent;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<String> phones) {
        this.phones = phones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }
}
