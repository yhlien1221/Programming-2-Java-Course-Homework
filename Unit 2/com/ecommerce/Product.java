package com.ecommerce;

/**
 * Represents a product available for purchase in the e-commerce system.
 */
public class Product {
    // Private attributes to encapsulate product data
    private String productID;
    private String name;
    private double price;

    /**
     * Constructor for the Product class.
     * Initializes a new Product object with a given ID, name, and price.
     * @param productID The unique identifier for the product.
     * @param name The name of the product.
     * @param price The price of the product.
     */
    public Product(String productID, String name, double price) {
        this.productID = productID;
        this.name = name;
        this.price = price;
    }

    // --- Getter Methods ---
    // Public methods to allow external access to private attributes
    public String getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // --- Setter Methods ---
    // Public methods to allow controlled modification of private attributes
    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Provides a string representation of the Product object.
     * This is useful for printing and debugging.
     * @return A formatted string with product details.
     */
    @Override
    public String toString() {
        return "Product [ID=" + productID + ", Name=" + name + ", Price=$" + String.format("%.2f", price) + "]";
    }
}
