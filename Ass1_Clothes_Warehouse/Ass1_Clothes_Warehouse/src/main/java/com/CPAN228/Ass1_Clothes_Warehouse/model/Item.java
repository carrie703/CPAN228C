package com.warehouse.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Item {

    private static long counter = 1; // Simulate auto-increment ID

    private long id;
    
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Brand is required")
    private Brand brand;

    @Min(value = 2022, message = "Year must be more than 2021")
    private int yearOfCreation;

    @Min(value = 1001, message = "Price must be more than 1000")
    private double price;

    // Constructor
    public Item() {
        this.id = counter++; // Simulate auto-increment ID
    }

    // Getters & Setters
    public long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }

    public int getYearOfCreation() { return yearOfCreation; }
    public void setYearOfCreation(int yearOfCreation) { this.yearOfCreation = yearOfCreation; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
