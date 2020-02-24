package com.jimmy.pizzeria.object;

import androidx.annotation.DrawableRes;

import com.google.gson.annotations.SerializedName;

public class Pizza {

    @SerializedName("PizzaID")
    private int pizzaID = 0;
    @SerializedName("Name")
    private String name = "";
    @SerializedName("Description")
    private String description = "";
    @SerializedName("ResourceID")
    private int resourceID = 0;
    @SerializedName("Price")
    private double price = 0.0;

    public Pizza() {
    }

    public Pizza(int pizzaID, String name, String description, int resourceID, double price) {
        this.pizzaID = pizzaID;
        this.name = name;
        this.description = description;
        this.resourceID = resourceID;
        this.price = price;
    }

    public int getPizzaID() {
        return pizzaID;
    }

    public void setPizzaID(int pizzaID) {
        this.pizzaID = pizzaID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DrawableRes
    public int getResourceID() {
        return resourceID;
    }


    public void setResourceID(@DrawableRes int resourceID) {
        this.resourceID = resourceID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
