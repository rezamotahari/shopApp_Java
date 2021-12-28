package com.example.shopapp.Model;

public class CategoryModel {

    int id;
    String title;
    String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategoryModel(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }
}
