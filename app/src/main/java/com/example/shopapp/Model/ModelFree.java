package com.example.shopapp.Model;

public class ModelFree {
    int id;
    String image;
    String lable;
    String name;
    String view;
    String price;
    String freeprice;
    Float finalrating;

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

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
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

    public Float getFinalrating() {
        return finalrating;
    }

    public void setFinalrating(Float finalrating) {
        this.finalrating = finalrating;
    }

    public ModelFree(int id, String image, String lable, String name, String view, String price, String freeprice, Float finalrating) {
        this.id = id;
        this.image = image;
        this.lable = lable;
        this.name = name;
        this.view = view;
        this.price = price;
        this.freeprice = freeprice;
        this.finalrating = finalrating;
    }
}
