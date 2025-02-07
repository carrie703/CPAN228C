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
        model.addAttribute("item", new Item());
        model.addAttribute("brands", Item.getBrands());
        return "add-item";
    }

    @PostMapping("/add-item")
    public String addItem(@ModelAttribute("item") Item item, Model model) {
        List<String> errors = new ArrayList<>();

        // Manual validation
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

        // If there are errors, return to the form
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("brands", Item.getBrands());
            return "add-item";
        }

        // Add item to stock and redirect on success
        stock.add(item);
        return "redirect:/add-item?success";
    }
}
