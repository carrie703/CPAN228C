package com.CPAN228.Ass1_Clothes_Warehouse.model;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "items")  // JPA annotation to specify the table name
public class Item {
    private static long counter = 0;

    @Id  // Marks the id as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Automatically generates the id value in the database
    private long id;

    @Column(name = "name", nullable = false)  // Column for the item name
    private String name;

    @Enumerated(EnumType.STRING)  // Store the enum as a string (so "BALENCIAGA", "STONE_ISLAND", etc.)
    @Column(name = "brand", nullable = false)  // Column for the item brand
    private Brand brand;

    @Column(name = "year", nullable = false)  // Column for the item year
    private int year;

    @Column(name = "price", nullable = false)  // Column for the item price
    private double price;

    public enum Brand {
        BALENCIAGA, STONE_ISLAND, DIOR
    }

    public Item() {
        // Default constructor for JPA
    }

    public Item(String name, Brand brand, int year, double price) {
        this.name = name;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }

    // Getters and setters for JPA
    public long getId() { return id; }
    public String getName() { return name; }
    public Brand getBrand() { return brand; }
    public int getYear() { return year; }
    public double getPrice() { return price; }
    
    public void setName(String name) { this.name = name; }
    public void setBrand(Brand brand) { this.brand = brand; }
    public void setYear(int year) { this.year = year; }
    public void setPrice(double price) { this.price = price; }

    // A static method to get all possible brands (useful for forms or filters)
    public static List<Brand> getBrands() {
        return Arrays.asList(Brand.values());
    }
}
