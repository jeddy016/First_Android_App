package com.example.jordan.peoplelistapp;

import android.content.ContentValues;

import java.io.Serializable;

class Person implements Serializable{
    private int id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String website;
    private double rating;

    Person(String name, String address, String email, String phone, String website, double rating) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.rating = rating;
    }

    Person(int id, String name, String address, String email, String phone, String website, double rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public String toString() {
        return name;
    }

    public ContentValues toContentValues() {
        ContentValues data = new ContentValues();
        data.put("name", name);
        data.put("rating", rating);
        data.put("address", address);
        data.put("phone", phone);
        data.put("email", email);
        data.put("website", website);

        return data;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }
}
