import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.ArrayList;

/**
 * LibraryItem Generic Class (Moved here for clarity)
 * Must be public or package-private for use by the main application.
 */
class LibraryItem<T> {
    private T itemID;
    private String title;
    private String author;

    public LibraryItem(T itemID, String title, String author) {
        this.itemID = itemID;
        this.title = title;
        this.author = author;
    }

    // Getters
    public T getItemID() {
        return itemID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    // Formatted output of item information
    @Override
    public String toString() {
        String idType = itemID.getClass().getSimpleName();
        return String.format("[ID: %s, Type: %s] Title: %s, Author: %s",
                itemID, idType, title, author);
    }
}


/**
 * Main application class: Contains CLI and Testing logic
 */
public class LibraryCatalogApp {

    public static void main(String[] args) {
        // Initializes a catalog for the CLI with Integer IDs
        LibraryCatalog<Integer> cliCatalog = new LibraryCatalog<>();
        Scanner scanner = new Scanner(System.in);

        // Execute automated testing
        performTesting();

        System.out.println("\n=====================================");
        System.out.println("====== Library Catalog CLI ======");
        System.out.println("====== ID Type: Integer ======");
        System.out.println("=====================================");

        // CLI Main Loop
        while (true) {
            System.out.println("\nPlease select an operation:");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. View Catalog");
            System.out.println("4. Get Details by ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            if (!scanner.hasNextInt()) {
                System.out.println("\n[ERROR] Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Item ID (Integer): ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Author/Publisher: ");
                        String author = scanner.nextLine();

                        cliCatalog.addItem(new LibraryItem<>(id, title, author));
                        break;

                    case 2:
                        System.out.print("Enter Item ID to remove (Integer): ");
                        int removeId = scanner.nextInt();
                        scanner.nextLine();

                        cliCatalog.removeItem(removeId);
                        break;

                    case 3:
                        cliCatalog.viewCatalog();
                        break;

                    case 4:
                        System.out.print("Enter Item ID to query (Integer): ");
                        int queryId = scanner.nextInt();
                        scanner.nextLine();

                        LibraryItem<Integer> foundItem = cliCatalog.getItemDetails(queryId);

                        if (foundItem != null) {
                            System.out.println("\n[QUERY RESULT] Item found: " + foundItem);
                        } else {
                            System.out.println("\n[QUERY RESULT] Sorry, item with ID " + queryId + " does not exist.");
                        }
                        break;

                    case 5:
                        System.out.println("Application closing. Goodbye!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("\n[ERROR] Invalid choice. Please enter a number from 1 to 5.");
                }
            } catch (Exception e) {
                System.out.println("\n[OPERATION FAILED] " + e.getMessage());
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
        }
    }

    /**
     * Automated testing method
     */
    private static void performTesting() {
        // 1. Test Integer ID Type
        LibraryCatalog<Integer> intCatalog = new LibraryCatalog<>();
        System.out.println("\n--- Test Case 1: Integer ID (Book) ---");
        intCatalog.addItem(new LibraryItem<>(1001, "Java Generics Handbook", "Joshua Bloch"));

        // Test successful removal
        try {
            intCatalog.removeItem(1001);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] Removal failed: " + e.getMessage());
        }

        // Test error handling (removing non-existent ID)
        System.out.println("\n--- Test Case 2: Error Handling (Remove Non-Existent Item) ---");
        try {
            intCatalog.removeItem(9999);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR HANDING SUCCESS] Exception caught: " + e.getMessage());
        }

        // 2. Test String ID Type (Demonstrating Generics Flexibility)
        LibraryCatalog<String> stringCatalog = new LibraryCatalog<>();
        System.out.println("\n--- Test Case 3: String (UUID) ID (DVD/Magazine) ---");
        String uuid1 = UUID.randomUUID().toString().substring(0, 8);
        stringCatalog.addItem(new LibraryItem<>(uuid1, "Sci-Fi Movie Collection", "Warner Bros"));

        // Test generic compatibility
        List<String> allIDs = stringCatalog.getAllItemIDs();
        System.out.println("[GENERIC COMPATIBILITY] Catalog uses ID Type: String");
        System.out.println("[GENERIC COMPATIBILITY] List of IDs (String): " + allIDs);

        System.out.println("\n====== Automated Testing Complete ======");
    }
}