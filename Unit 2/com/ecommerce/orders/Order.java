package com.ecommerce.orders;
import com.ecommerce.Customer;
import com.ecommerce.Product;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an order placed by a customer in the e-commerce system.
 */
public class Order {
    private String orderID;
    private Customer customer;
    private List<Product> products;
    private double orderTotal;
    private LocalDate orderDate;
    private OrderStatus status;

    /**
     * Constructor for the Order class.
     * Initializes a new Order object with a unique ID, customer, list of products, and total cost.
     * The order date is automatically set to the current date.
     * @param orderID The unique identifier for the order.
     * @param customer The customer who placed the order.
     * @param products The list of products included in the order.
     * @param orderTotal The total cost of the order.
     */
    public Order(String orderID, Customer customer, List<Product> products, double orderTotal){
        this.orderID = orderID;
        this.customer = customer;
        this.products = products;
        this.orderTotal = orderTotal;
        this.orderDate = LocalDate.now();
        this.status = OrderStatus.PENDING;
    }

    // --- Getter Methods ---
    public String getOrderID() {
        return orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    // Setter
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * Enumerates the possible states of an order in the e-commerce system.
     */
    public enum OrderStatus {
        PENDING,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }

    /**
     * Generates a detailed, formatted summary of the order.
     * @return A string containing all the order details.
     */
    public String generateOrderSummary() {
        // StringBuilder is used for efficient string concatenation.
        StringBuilder summary = new StringBuilder();
        summary.append("--- Order Summary ---\n");
        summary.append("Order ID: ").append(orderID).append("\n");
        summary.append("Customer: ").append(customer.getName()).append(" (ID: ").append(customer.getCustomerID()).append(")\n");
        summary.append("Order Date: ").append(orderDate).append("\n");
        summary.append("Status: ").append(status).append("\n");
        summary.append("Products:\n");

        // Loops through each product in the order and appends it to the summary.
        for (Product product : products) {
            summary.append("  - ").append(product.getName()).append(" ($").append(String.format("%.2f", product.getPrice())).append(")\n");
        }
        summary.append("Order Total: $").append(String.format("%.2f", orderTotal)).append("\n");
        summary.append("---------------------\n");

        // Converts the StringBuilder to a final String and returns it.
        return summary.toString();
    }
}
