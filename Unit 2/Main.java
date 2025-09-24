import com.ecommerce.Product;
import com.ecommerce.Customer;
import com.ecommerce.orders.Order;

/**
 * The main program to demonstrate the e-commerce system.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting the E-commerce System Demonstration...");

        // 1. Create products and a customer
        Product laptop = new Product("P001", "Laptop", 1200.00);
        Product mouse = new Product("P002", "Wireless Mouse", 10.00);
        Product keyboard = new Product("P003", "Mechanical Keyboard", 75.00);

        System.out.println("\nAvailable Products:");
        System.out.println(laptop);
        System.out.println(mouse);
        System.out.println(keyboard);

        // Create a customer
        Customer alice = new Customer("C001", "Alice");

        // 2. Add products to the cart
        alice.addProductToCart(mouse, laptop);

        // Display the customer's cart
        System.out.println("\n" + alice.getName() + "'s cart contains " + alice.getShoppingCart().size() + " items.");
        System.out.println("Cart total before placing order: $" + String.format("%.2f", alice.calculateCartTotal()));

        // 3. Place a valid order
        System.out.println("\nPlacing a valid order...");
        String orderID = "ORD-2025-001";
        Order newOrder = alice.placeOrder(orderID);

        // Display order information if the order was placed successfully
        if (newOrder != null) {
            System.out.println(newOrder.generateOrderSummary());

            // Demonstrate updating the order status
            System.out.println("Updating order status to 'SHIPPED'...");
            newOrder.setStatus(Order.OrderStatus.SHIPPED);
            System.out.println("New order status: " + newOrder.getStatus());
        }

        System.out.println("\n------------------------------");
        System.out.println("Demonstrating error handling for an empty cart...");

        // 4. Try to place an order again with an empty cart
        // Note: The placeOrder() method has already cleared the cart
        Order emptyCartOrder = alice.placeOrder("ORD-2025-002");
        if (emptyCartOrder == null) {
            System.out.println("Order failed because the cart is empty. This is the expected behavior.");
        }

    }
}