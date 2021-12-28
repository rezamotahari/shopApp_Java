package com.example.shopapp.Model;

public class BasketModel {

    String image;
    String number;
    String color;
    String garanty;
    String price;
    String allprice;
    String title;
    int id;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGaranty() {
        return garanty;
    }

    public void setGaranty(String garanty) {
        this.garanty = garanty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAllprice() {
        return allprice;
    }

    public void setAllprice(String allprice) {
        this.allprice = allprice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BasketModel(String image, String number, String color, String garanty, String price, String allprice, String title, int id) {
        this.image = image;
        this.number = number;
        this.color = color;
        this.garanty = garanty;
        this.price = price;
        this.allprice = allprice;
        this.title = title;
        this.id = id;
    }
}
