package com.example.shopapp.Model;

public class FavModel {
    int id;
    String image;
    String name;
    String view;
    String freeprice;
    String price;
    String label;

    public FavModel()
    {

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

    public String getFreeprice() {
        return freeprice;
    }

    public void setFreeprice(String freeprice) {
        this.freeprice = freeprice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public FavModel(int id, String image, String name, String view, String freeprice, String price, String label) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.view = view;
        this.freeprice = freeprice;
        this.price = price;
        this.label = label;
    }
}
