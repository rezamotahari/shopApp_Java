package com.example.shopapp.Model;

public class ModelSearch {
    int id;
    String image;
    String name;
    String price;
    String cat;
    String lable;
    String freeprice;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getFreeprice() {
        return freeprice;
    }

    public void setFreeprice(String freeprice) {
        this.freeprice = freeprice;
    }

    public ModelSearch(int id, String image, String name, String price, String cat, String lable, String freeprice) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.cat = cat;
        this.lable = lable;
        this.freeprice = freeprice;
    }
}
