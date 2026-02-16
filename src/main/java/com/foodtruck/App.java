package com.foodtruck;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {

        port(4567);

        Menu menu = new Menu();

        // Home page with 3 dropdowns (no "None" option)
        get("/", (req, res) -> {
            res.type("text/html");

            String html = "<html><body>";
            html += "<h2>Select your food items:</h2>";

            html += "<form action='/order' method='get'>";

            // Salad
            html += "<p><b>Salad:</b> ";
            html += "<select name='salad' required>";
            html += "<option value='' disabled selected>-- Select a salad --</option>";
            html += "<option value='salad_greek'>Greek Salad ($7.50)</option>";
            html += "<option value='salad_caesar'>Caesar Salad ($7.25)</option>";
            html += "<option value='salad_garden'>Garden Salad ($6.75)</option>";
            html += "<option value='salad_tuna'>Tuna Salad ($7.00)</option>";
            html += "<option value='salad_beef'>Beef Salad ($100.00)</option>";
            html += "</select></p>";

            // Sandwich
            html += "<p><b>Sandwich:</b> ";
            html += "<select name='sandwich' required>";
            html += "<option value='' disabled selected>-- Select a sandwich --</option>";
            html += "<option value='sandwich_turkey'>Turkey Sandwich ($9.50)</option>";
            html += "<option value='sandwich_veggie'>Veggie Sandwich ($8.75)</option>";
            html += "<option value='sandwich_tuna'>Tuna Sandwich ($9.25)</option>";
            html += "<option value='sandwich_steak'>Steak Sandwich ($10.00)</option>";
            html += "<option value='sandwich_philly'>Philly Sandwich ($8.00)</option>";
            html += "</select></p>";

            // Drink
            html += "<p><b>Drink:</b> ";
            html += "<select name='drink' required>";
            html += "<option value='' disabled selected>-- Select a drink --</option>";
            html += "<option value='drink_water'>Water ($1.50)</option>";
            html += "<option value='drink_lemonade'>Lemonade ($2.75)</option>";
            html += "<option value='drink_icedtea'>Iced Tea ($2.50)</option>";
            html += "<option value='drink_coke'>Coke ($2.00)</option>";
            html += "<option value='drink_feetjuice'>Feet Juice ($100.00)</option>";
            html += "</select></p>";

            html += "<button type='submit'>Calculate Total</button>";
            html += "</form>";

            html += "</body></html>";
            return html;
        });

        // Order page: show chosen items + prices + total
        get("/order", (req, res) -> {
            res.type("text/html");

            String saladCode = req.queryParams("salad");
            String sandwichCode = req.queryParams("sandwich");
            String drinkCode = req.queryParams("drink");

            // If any are missing, show an error message
            if (saladCode == null || sandwichCode == null || drinkCode == null ||
                saladCode.equals("") || sandwichCode.equals("") || drinkCode.equals("")) {

                return "<html><body>"
                        + "<h2>Error</h2>"
                        + "<p>You must select 1 salad, 1 sandwich, and 1 drink.</p>"
                        + "<p><a href='/'>Go back</a></p>"
                        + "</body></html>";
            }

            MenuItem salad = menu.getItem(saladCode);
            MenuItem sandwich = menu.getItem(sandwichCode);
            MenuItem drink = menu.getItem(drinkCode);

            // Safety check in case code does not exist
            if (salad == null || sandwich == null || drink == null) {
                return "<html><body>"
                        + "<h2>Error</h2>"
                        + "<p>One of your selections was not found in the menu.</p>"
                        + "<p><a href='/'>Go back</a></p>"
                        + "</body></html>";
            }

            double total = salad.getPrice() + sandwich.getPrice() + drink.getPrice();

            String html = "<html><body>";
            html += "<h2>Your Order</h2>";
            html += "<ul>";
            html += "<li>Salad: " + salad.getName() + " - " + money(salad.getPrice()) + "</li>";
            html += "<li>Sandwich: " + sandwich.getName() + " - " + money(sandwich.getPrice()) + "</li>";
            html += "<li>Drink: " + drink.getName() + " - " + money(drink.getPrice()) + "</li>";
            html += "</ul>";
            if (total > 20.00) {
                html += "<p><b>Total Charge:</b> " + money(total * 0.8) + "</p>";
            }
            else {
                html += "<p><b>Total Charge:</b> " + money(total) + "</p>";
            }
            html += "<p><a href='/'>Back to menu</a></p>";

            html += "</body></html>";
            return html;
        });

        System.out.println("Server started at http://localhost:4567");
    }

    // Format money like $7.50
    private static String money(double value) {
        return String.format("$%.2f", value);
    }
}

