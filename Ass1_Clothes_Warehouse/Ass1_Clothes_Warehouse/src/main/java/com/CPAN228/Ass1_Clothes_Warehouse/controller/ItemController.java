package com.CPAN228.Ass1_Clothes_Warehouse.controller;

import com.CPAN228.Ass1_Clothes_Warehouse.model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {

    private final List<Item> stock = new ArrayList<>();

    @GetMapping("/add-item")
    public String showForm(Model model) {
        Item item = new Item();
        item.setYear(2022);
        item.setPrice(1001);
        model.addAttribute("item", item);
        model.addAttribute("brands", Item.getBrands());
        model.addAttribute("items", stock); 
        return "add-item";
    }

    @PostMapping("/add-item")
    public String addItem(@ModelAttribute("item") Item item, Model model) {
        System.out.println("Inside addItem() method"); 
        System.out.flush();
        List<String> errors = new ArrayList<>();

        if (item.getName() == null || item.getName().trim().length() < 2) {
            errors.add("Name should have at least 2 characters.");
        }
        if (item.getBrand() == null) {
            errors.add("Brand must be selected.");
        }
        if (item.getYear() <= 2021) {
            errors.add("Year must be greater than 2021.");
        }
        if (item.getPrice() <= 1000) {
            errors.add("Price must be more than 1000.");
        }

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("brands", Item.getBrands());
            model.addAttribute("items", stock); 
            return "add-item";
        }

        stock.add(item);

        System.out.println("Current stock items: " + stock.size());
        System.out.flush(); 

        model.addAttribute("success", "Item added successfully!");
        model.addAttribute("brands", Item.getBrands());
        model.addAttribute("items", stock); 

        return "add-item"; 

    }

}
