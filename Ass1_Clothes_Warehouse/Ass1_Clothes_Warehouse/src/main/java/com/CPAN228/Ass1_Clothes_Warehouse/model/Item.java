package com.CPAN228.Ass1_Clothes_Warehouse.model;

import java.util.Arrays;
import java.util.List;

public class Item {
    private static long counter = 0;
    private long id;
    private String name;
    private Brand brand;
    private int year;
    private double price;

    public enum Brand {
        BALENCIAGA, STONE_ISLAND, DIOR
    }

    public Item() {
        this.id = ++counter;
    }

    public Item(String name, Brand brand, int year, double price) {
        this.id = ++counter;
        this.name = name;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public Brand getBrand() { return brand; }
    public int getYear() { return year; }
    public double getPrice() { return price; }
    
    public void setName(String name) { this.name = name; }
    public void setBrand(Brand brand) { this.brand = brand; }
    public void setYear(int year) { this.year = year; }
    public void setPrice(double price) { this.price = price; }

    public static List<Brand> getBrands() {
        return Arrays.asList(Brand.values());
    }
}
