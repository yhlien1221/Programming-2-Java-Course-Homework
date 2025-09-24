package com.ecommerce;
import com.ecommerce.orders.Order;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer with a shopping cart in the e-commerce system.
 */

public class Customer {
    private String customerID;
    private String name;
    private List<Product> shoppingCart;


    public Customer (String customerID, String name) {
        this.customerID = customerID;
        this.name = name;
        this.shoppingCart = new ArrayList<>();
    }


    // Getters
    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }


/**
 * Adds products to the customer's shopping cart.
 * @param products The product to add.
 */

    public void addProductToCart(Product... products) {
        for (Product product : products) {
            this.shoppingCart.add(product);
            System.out.println(product.getName() + " added to " + this.name + "'s cart.");
        }
    }

/**
 * Removes a product from the customer's shopping cart.
 * @param product The product to remove.
 */


   public void removeProductFromCart (Product product) {
       if (this.shoppingCart.remove(product)) {
           System.out.println(product.getName() + " remove from " + this.name + "'s cart.");
       } else {
           System.out.println(product.getName() + " was not found in " + this.name + "'s cart.");
       }
   }


    /**
     * Calculates the total cost of all products in the shopping cart.
     * @return The total cost as a double.
     */

    public double calculateCartTotal() {
        double total = 0.0;
        for (Product product : shoppingCart) {
            total += product.getPrice();
        }
        return total;
    }


    /**
     * Places an order if the shopping cart is not empty.
     * @return The newly created Order object, or null if the cart is empty.
     */
    public Order placeOrder(String orderID) {
        if (this.shoppingCart.isEmpty()) {
            System.out.println("Error: The shopping cart is empty. Please add products before placing an order.");
            return null;
        }

        // Create a new order
        Order newOrder = new Order(orderID, this, new ArrayList<>(this.shoppingCart), this.calculateCartTotal());

        // Clear the cart after placing the order
        this.shoppingCart.clear();
        System.out.println("Order " + orderID + " placed successfully! The cart has been cleared.");

        return newOrder;
    }




    /**
     * Returns a string representation of the Customer object.
     * @return A formatted string with customer details.
     */
    @Override
    public String toString() {
        return "Customer [ID=" + customerID + ", Name=" + name + ", Items in cart: " + shoppingCart.size() + "]";
    }

}