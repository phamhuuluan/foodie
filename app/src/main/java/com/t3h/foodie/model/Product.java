package com.t3h.foodie.model;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private Integer price;
    @SerializedName("image")
    private String image;
    @SerializedName("address")
    private String address;
    @SerializedName("id_category")
    private int id_category;

    public Product(int id, String name, Integer price, String image, String address, int id_category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.address = address;
        this.id_category = id_category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }
}
