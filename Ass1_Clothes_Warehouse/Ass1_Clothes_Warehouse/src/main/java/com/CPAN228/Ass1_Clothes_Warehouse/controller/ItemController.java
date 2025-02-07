package com.warehouse.controller;

import com.warehouse.model.Brand;
import com.warehouse.model.Item;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final List<Item> items = new ArrayList<>(); // Store items in memory

    @GetMapping("/add")
    public String showAddItemForm(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("brands", Brand.values()); // Send brands to the form
        return "add-item";
    }

    @PostMapping("/add")
    public String addItem(@Valid @ModelAttribute("item") Item item, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("brands", Brand.values());
            return "add-item";
        }
        items.add(item); // Add item to in-memory list
        return "redirect:/items/list";
    }

    @GetMapping("/list")
    public String showItemList(Model model) {
        model.addAttribute("items", items); // Send stored items to template
        return "item-list";
    }
}
