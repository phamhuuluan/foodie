package com.t3h.foodie.model;

public class ShoppingCart {
    private int idProduct;
    private String nameProduct;
    private long priceProduct;
    private String imageProduct;
    private String numberProduct;

    public ShoppingCart(int idProduct, String nameProduct, long priceProduct, String imageProduct, String numberProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.imageProduct = imageProduct;
        this.numberProduct = numberProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public long getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(long priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getNumberProduct() {
        return numberProduct;
    }

    public void setNumberProduct(String numberProduct) {
        this.numberProduct = numberProduct;
    }
}
