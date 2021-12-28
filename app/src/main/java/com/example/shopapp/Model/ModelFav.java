package com.example.shopapp.Model;

public class ModelFav {
    int id;
    String image;
    String title;
    String visit;
    String freeprice;
    String price;
    String label;

    public ModelFav()
    {

    }

    public ModelFav(int id, String image, String title, String visit, String freeprice, String price,String label) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.visit = visit;
        this.freeprice = freeprice;
        this.price = price;
        this.label =label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
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
}
