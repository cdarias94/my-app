package com.code.myapp.model;


import java.util.List;




public class Product {
    public long id;
    public String name;
    public boolean availability;
    public double price;

   public List<Long> productSimilar;


    public Product(long id, String name, boolean availability, double price, List<Long> productSimilar) {
        this.id = id;
        this.name = name;
        this.availability = availability;
        this.price = price;
        this.productSimilar = productSimilar;
    }

    public Product(long id) {
        this.id = id;
    }

}
