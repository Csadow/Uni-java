package Menu;

import ExceptionHandling.ExceptionHandling;
import StuffnStaff.Clothing;
import StuffnStaff.Staff;
import StuffnStaff.Shirt;
import StuffnStaff.Shorts;
import database.*;

import java.util.List;
import java.util.Scanner;


public class ClothingMenu implements Menu {
    private final Scanner scanner;
    private ClothingDAO clothingDAO;

    public ClothingMenu() {
        this.scanner = new Scanner(System.in);
        this.clothingDAO = new ClothingDAO();
    }

    @Override
    public void displayMenu() {
        System.out.println("\n---------------");
        System.out.println("Clothing Shop Menu");
        System.out.println("---------------");
        System.out.println("1.Add Shirt");
        System.out.println("2.Add Shorts");
        System.out.println("3.View all Clothing");
        System.out.println("4.Look only for shirts");
        System.out.println("5.Look only for shorts");
        System.out.println("6.Update clothing");
        System.out.println("7.Delete clothing");
        System.out.println("8.Search by name");
        System.out.println("9.Search by price range");
        System.out.println("10.High cost clothing");
        System.out.println("11.Polymorphism");
        System.out.println("0.Exit");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.println("Enter your choice");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addShirt();
                        break;
                    case 2:
                        addShorts();
                        break;
                    case 3:
                        viewAllClothing();
                        break;
                    case 4:
                        viewShirts();
                        break;
                    case 5:
                        viewShorts();
                        break;
                    case 6:
                        updateClothing();
                        break;
                    case 7:
                        deleteClothing();
                        break;
                    case 8:
                        searchByName();
                        break;
                    case 9:
                        searchByPriceRange();
                        break;
                    case 10:
                        searchByMinPrice();
                        break;
                    case 11:
                        showPolynorphism();
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Error, invalid input");
                }

                if (choice != 0) {
                    pressEnterToContinue();
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error, invalid input" + e.getMessage());
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
                scanner.nextLine();
            }
        }
        System.out.println("Exiting");
        scanner.close();
    }

    private void addShirt() {
        try {
            System.out.println("\n┌─ ADD SHIRT ────────────────────────────┐");

            System.out.print("│ Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("│ Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("│ Enter Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("│ Enter Size (Numeric): ");
            int size = scanner.nextInt();
            scanner.nextLine();

            System.out.print("│ Enter Sleeve Length: ");
            int sleeveLength = scanner.nextInt();
            scanner.nextLine();

            System.out.println("└────────────────────────────────────────┘");

            Shirt shirt = new Shirt(id, name, price, size, sleeveLength);
            // Note: Ensure your Shirt constructor matches this signature
            boolean success = clothingDAO.insertShirt(shirt);

            if (!success) System.out.println("❌ Failed to add shirt (ID might exist).");

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid input type!");
            scanner.nextLine();
        }
    }

    private void addShorts() {
        try {
            System.out.println("\n┌─ ADD SHORTS ───────────────────────────┐");

            System.out.print("│ Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("│ Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("│ Enter Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("│ Enter Size (Numeric): ");
            int size = scanner.nextInt();
            scanner.nextLine();

            System.out.print("│ Enter Waist Length: ");
            int waist = scanner.nextInt();
            scanner.nextLine();

            System.out.println("└────────────────────────────────────────┘");

            Shorts shorts = new Shorts(id, size, price, name, waist);
            boolean success = clothingDAO.insertShorts(shorts);

            if (!success) System.out.println("❌ Failed to add shorts (ID might exist).");

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Error: Invalid input type!");
            scanner.nextLine();
        }
    }

    // ========================================
    // READ OPERATIONS
    // ========================================

    private void viewAllClothing() {
        List<Clothing> list = clothingDAO.getAllClothing();
        displayList(list, "ALL CLOTHING INVENTORY");
    }

    private void viewShirts() {
        List<Shirt> list = clothingDAO.getAllShirts();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║              SHIRTS ONLY               ║");
        System.out.println("╚════════════════════════════════════════╝");

        if (list.isEmpty()) {
            System.out.println("📭 No shirts in database.");
        } else {
            for (Shirt s : list) {
                System.out.println("• " + s.getName() + " (ID: " + s.getId() + ")");
                System.out.println("  Price: $" + s.getPrice() + " | Size: " + s.getSize());
                System.out.println("  Sleeve Length: " + s.getSleeveLength());
                System.out.println("─────────────────────────────────────────");
            }
            System.out.println("Total Shirts: " + list.size());
        }
    }

    private void viewShorts() {
        List<Shorts> list = clothingDAO.getAllShorts();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║              SHORTS ONLY               ║");
        System.out.println("╚════════════════════════════════════════╝");

        if (list.isEmpty()) {
            System.out.println("📭 No shorts in database.");
        } else {
            for (Shorts s : list) {
                System.out.println("• " + s.getName() + " (ID: " + s.getId() + ")");
                System.out.println("  Price: $" + s.getPrice() + " | Size: " + s.getSize());
                System.out.println("  Waist: " + s.getWaist());
                System.out.println("─────────────────────────────────────────");
            }
            System.out.println("Total Shorts: " + list.size());
        }
    }

    // ========================================
    // UPDATE OPERATION
    // ========================================

    private void updateClothing() {
        System.out.println("\n┌─ UPDATE CLOTHING ──────────────────────┐");
        System.out.print("│ Enter Clothing ID to update: ");

        try {
            int id = scanner.nextInt();
            scanner.nextLine();

            // 1. Fetch existing data
            Clothing existing = clothingDAO.getClothingById(id);

            if (existing == null) {
                System.out.println("❌ No clothing found with ID: " + id);
                return;
            }

            // Display current info
            System.out.println("│ Found: " + existing.getName() + " (" + existing.getClass().getSimpleName() + ")");
            System.out.println("└────────────────────────────────────────┘");

            // 2. Get new values (common fields)
            System.out.println("\n┌─ ENTER NEW VALUES ─────────────────────┐");
            System.out.println("│ (Press Enter to keep current value)    │");

            System.out.print("│ New Name [" + existing.getName() + "]: ");
            String newName = scanner.nextLine();
            if (newName.trim().isEmpty()) newName = existing.getName();

            System.out.print("│ New Price [" + existing.getPrice() + "]: ");
            String priceInput = scanner.nextLine();
            double newPrice = priceInput.trim().isEmpty() ? existing.getPrice() : Double.parseDouble(priceInput);

            System.out.print("│ New Size [" + existing.getSize() + "]: ");
            String sizeInput = scanner.nextLine();
            int newSize = sizeInput.trim().isEmpty() ? existing.getSize() : Integer.parseInt(sizeInput);

            // 3. Handle specific types
            if (existing instanceof Shirt) {
                Shirt shirt = (Shirt) existing;
                System.out.print("│ New Sleeve Length [" + shirt.getSleeveLength() + "]: ");
                String sleeveInput = scanner.nextLine();
                int newSleeve = sleeveInput.trim().isEmpty() ? shirt.getSleeveLength() : Integer.parseInt(sleeveInput);

                Shirt updatedShirt = new Shirt(id, newName, newPrice, newSize, newSleeve);
                clothingDAO.updateShirts(updatedShirt); // Calls specific DAO method

            } else if (existing instanceof Shorts) {
                Shorts shorts = (Shorts) existing;
                System.out.print("│ New Waist Length [" + shorts.getWaist() + "]: ");
                String waistInput = scanner.nextLine();
                int newWaist = waistInput.trim().isEmpty() ? shorts.getWaist() : Integer.parseInt(waistInput);

                Shorts updatedShorts = new Shorts(id, newSize, newPrice, newName, newWaist);
                clothingDAO.updateShorts(updatedShorts); // Calls specific DAO method
            }

            System.out.println("└────────────────────────────────────────┘");

        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Invalid number format!");
        }
    }

    // ========================================
    // DELETE OPERATION
    // ========================================

    private void deleteClothing() {
        System.out.println("\n┌─ DELETE CLOTHING ──────────────────────┐");
        System.out.print("│ Enter Clothing ID to delete: ");

        try {
            int id = scanner.nextInt();
            scanner.nextLine();

            Clothing existing = clothingDAO.getClothingById(id);
            if (existing == null) {
                System.out.println("❌ No clothing found with ID: " + id);
                return;
            }

            System.out.println("│ Item: " + existing.getName() + " ($" + existing.getPrice() + ")");
            System.out.print("⚠️ Are you sure? (yes/no): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("yes")) {
                clothingDAO.deleteClothing(id);
            } else {
                System.out.println("❌ Deletion cancelled.");
            }
            System.out.println("└────────────────────────────────────────┘");

        } catch (Exception e) {
            System.out.println("❌ Error processing delete.");
        }
    }

    // ========================================
    // SEARCH OPERATIONS
    // ========================================

    private void searchByName() {
        System.out.println("\n┌─ SEARCH BY NAME ───────────────────────┐");
        System.out.print("│ Enter name keyword: ");
        String name = scanner.nextLine();
        System.out.println("└────────────────────────────────────────┘");

        List<Clothing> results = clothingDAO.searchByName(name);
        displayList(results, "Search Results: '" + name + "'");
    }

    private void searchByPriceRange() {
        try {
            System.out.println("\n┌─ SEARCH BY PRICE RANGE ────────────────┐");
            System.out.print("│ Min Price: ");
            double min = scanner.nextDouble();
            System.out.print("│ Max Price: ");
            double max = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("└────────────────────────────────────────┘");

            List<Clothing> results = clothingDAO.searchByPriceRange(min, max);
            displayList(results, "Price Range: $" + min + " - $" + max);

        } catch (Exception e) {
            System.out.println("❌ Invalid input.");
            scanner.nextLine();
        }
    }

    private void searchByMinPrice() {
        try {
            System.out.println("\n┌─ SEARCH BY MIN PRICE ──────────────────┐");
            System.out.print("│ Min Price: ");
            double min = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("└────────────────────────────────────────┘");

            List<Clothing> results = clothingDAO.searchByMinPrice(min);
            displayList(results, "Price >= $" + min);

        } catch (Exception e) {
            System.out.println("❌ Invalid input.");
            scanner.nextLine();
        }
    }

    private void displayList(List<Clothing> list, String title) {
        System.out.println("Search results");

        if (list.isEmpty()) {
            System.out.println(" No items found.");
        } else {
            for (Clothing c : list) {
                // Using instanceof to print specific details inside general list
                String type = (c instanceof Shirt) ? "[SHIRT]" : (c instanceof Shorts) ? "[SHORTS]" : "[ITEM]";

                System.out.println(type + " " + c.getName() + " (ID: " + c.getId() + ")");
                System.out.println("   Price: $" + c.getPrice() + " | Size: " + c.getSize());

                if (c instanceof Shirt) {
                    System.out.println("   Sleeve: " + ((Shirt) c).getSleeveLength());
                } else if (c instanceof Shorts) {
                    System.out.println("   Waist: " + ((Shorts) c).getWaist());
                }
                System.out.println("─────────────────────────────────────────");
            }
            System.out.println("Total Items: " + list.size());
        }
    }

    private void showPolynorphism() {
        clothingDAO.demonstratePolymorphism();
    }


    // ========================================
    // HELPERS
    // ========================================

    private void pressEnterToContinue() {
        System.out.println("\n[Press Enter to continue...]");
        scanner.nextLine();
    }
}