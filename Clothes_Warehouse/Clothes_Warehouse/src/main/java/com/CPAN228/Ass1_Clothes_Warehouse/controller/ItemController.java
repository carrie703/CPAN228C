package com.CPAN228.Ass1_Clothes_Warehouse.controller;

import com.CPAN228.Ass1_Clothes_Warehouse.model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.CPAN228.Ass1_Clothes_Warehouse.data.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort; 


import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {

    private final List<Item> stock = new ArrayList<>();
    private final ItemRepository itemRepository;
    private static final List<String> BRANDS = List.of("BALENCIAGA", "STONE_ISLAND", "DIOR");

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/add-item")
    public String showForm(Model model) {
        Item item = new Item();
        item.setYear(2022);
        item.setPrice(1001);
        model.addAttribute("item", item);
        model.addAttribute("brands", BRANDS);
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
            model.addAttribute("brands", BRANDS);
            model.addAttribute("items", stock);
            return "add-item";
        }

        itemRepository.save(item);

        model.addAttribute("success", "Item added successfully!");
        model.addAttribute("brands", BRANDS);
        return "redirect:/items";
    }

    @GetMapping("/items")
    public String listItems(Model model, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "name") String sortBy) {
        Page<Item> itemPage = itemRepository.findAll(PageRequest.of(page, size).withSort(Sort.by(sortBy)));
        model.addAttribute("items", itemPage.getContent());
        model.addAttribute("page", itemPage);
        return "item-list";
    }

    @GetMapping("/items/brand/{brand}")
    public String getItemsByBrand(@PathVariable String brand, Model model) {
        System.out.println("Requested brand: " + brand);  // Debugging log

        List<Item> items = itemRepository.findByBrandAndYear2022(brand.trim().toUpperCase());

        if (items == null || items.isEmpty()) {
            model.addAttribute("error", "No items found for brand: " + brand);
            model.addAttribute("items", List.of()); // Ensure Thymeleaf does not break
        } else {
            model.addAttribute("items", items);
        }

        model.addAttribute("page", null);
        return "item-list";
    }

}