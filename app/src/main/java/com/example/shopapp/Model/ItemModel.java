package com.example.shopapp.Model;

public class ItemModel {
    int id ;
    String image;
    String name;
    String view;
    String price;
    String freeprice;
    String lable;

    public ItemModel(int id, String image, String name, String view, String price, String freeprice, String lable) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.view = view;
        this.price = price;
        this.freeprice = freeprice;
        this.lable = lable;
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

    public String getFreeprice() {
        return freeprice;
    }

    public void setFreeprice(String freeprice) {
        this.freeprice = freeprice;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }
}
