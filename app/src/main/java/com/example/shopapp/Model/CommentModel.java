package com.example.shopapp.Model;

public class CommentModel {
    String user,comment,positive,image,nagative;
    Float rating;

    public CommentModel(String user, String comment, String positive, String image, String nagative, Float rating) {
        this.user = user;
        this.comment = comment;
        this.positive = positive;
        this.image = image;
        this.nagative = nagative;
        this.rating = rating;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNagative() {
        return nagative;
    }

    public void setNagative(String nagative) {
        this.nagative = nagative;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
