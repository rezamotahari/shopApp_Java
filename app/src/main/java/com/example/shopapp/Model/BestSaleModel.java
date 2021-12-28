package com.example.shopapp.Model;

public class BestSaleModel {

    int id;
    String image;

    String name;
    String view;
    String price;


    public BestSaleModel(int id, String image, String name, String view, String price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.view = view;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
