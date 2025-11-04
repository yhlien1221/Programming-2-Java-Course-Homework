import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// This file now only contains the LibraryCatalog class
public class LibraryCatalog <T> {

    // Internal storage structure
    private final List<LibraryItem<T>> items = new ArrayList<>();

    // Retrieves all item IDs (used in performTesting)
    public List<T> getAllItemIDs() {
        return items.stream()
                .map(LibraryItem::getItemID)
                .collect(Collectors.toList());
    }

    public void addItem(LibraryItem <T> item) {
        items.add(item);
        System.out.println("[SUCCESS] Item added: " + item.getTitle());
    }

    public void removeItem(T itemID) throws IllegalArgumentException {
        // Uses removeIf to find and remove the item by ID, returning true if removal occurred
        boolean removed = items.removeIf(item -> item.getItemID().equals(itemID));

        if (!removed) {
            throw new IllegalArgumentException("Item with ID " + itemID + " not found in the catalog.");
        }
        System.out.println("[SUCCESS] Item with ID " + itemID + " removed.");
    }

    public LibraryItem<T> getItemDetails(T itemID) {
        // Uses stream filter to find the first item matching the ID
        return items.stream()
                .filter(item -> item.getItemID().equals(itemID))
                .findFirst()
                .orElse(null);
    }

    public void viewCatalog() {
        if (items.isEmpty()){
            System.out.println("\n--- Catalog is currently empty ---");
            return;
        }

        System.out.println("\n--- Current Library Catalog (" + items.size() + " items) ---");
        for (LibraryItem<T> item : items) {
            System.out.println(item);
        }
        System.out.println("------------------------------------");
    }
}
