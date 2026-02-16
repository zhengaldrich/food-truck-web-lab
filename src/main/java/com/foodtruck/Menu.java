package com.foodtruck;

import java.util.HashMap;

public class Menu {
    private HashMap<String, MenuItem> items;

    public Menu() {
        items = new HashMap<>();

        // Salads (3)
        items.put("salad_greek", new Salad("Greek Salad", 7.50));
        items.put("salad_caesar", new Salad("Caesar Salad", 7.25));
        items.put("salad_garden", new Salad("Garden Salad", 6.75));
        items.put("salad_tuna", new Salad("Tuna Salad", 7.00));
        items.put("salad_beef", new Salad("Beef Salad", 100.00));

        // Sandwiches (3)
        items.put("sandwich_turkey", new Sandwich("Turkey Sandwich", 9.50));
        items.put("sandwich_veggie", new Sandwich("Veggie Sandwich", 8.75));
        items.put("sandwich_tuna", new Sandwich("Tuna Sandwich", 9.25));
        items.put("sandwich_steak", new Sandwich("Steak Sandwich", 10.00));
        items.put("sandwich_philly", new Sandwich("Philly Sandwich", 8.00));

        // Drinks (3)
        items.put("drink_water", new Drink("Water", 1.50));
        items.put("drink_lemonade", new Drink("Lemonade", 2.75));
        items.put("drink_icedtea", new Drink("Iced Tea", 2.50));
        items.put("drink_coke", new Drink("Coke", 2.00));
        items.put("drink_feetjuice", new Drink("Feet Juice", 100.00));
    }

    public MenuItem getItem(String code) {
        return items.get(code);
    }
}

