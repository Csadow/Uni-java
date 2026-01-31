package Menu;

import ExceptionHandling.ExceptionHandling;
import StuffnStaff.Clothing;
import StuffnStaff.Staff;
import StuffnStaff.Shirt;
import StuffnStaff.Shorts;

import java.util.ArrayList;
import java.util.Scanner;


public class ClothingMenu implements Menu{
    private final Scanner scanner;
    private ArrayList<Clothing> clothingList;
    private ArrayList<Staff> staffList;

    public ClothingMenu() {
        clothingList = new ArrayList<>();
        staffList = new ArrayList<>();
        scanner = new Scanner(System.in);

        try {
            clothingList.add(new Shirt(5, "One Piece Special", 19.9, 42, 12));
            clothingList.add(new Shorts(9, 14, 21.0, "Hawaii", 30));

            staffList.add(new Staff("Jow", 120000, "Consultant"));
            staffList.add(new Staff("Ali", 50000, "Janitor"));
            staffList.add(new Staff("Nurik", 320000, "Manager"));
        }
        catch(IllegalArgumentException e){
            System.out.println("Error initializing");
        }

    }

    @Override
    public void displayMenu(){
        System.out.println("\n---------------");
        System.out.println("Clothing Shop Menu");
        System.out.println("---------------");
        System.out.println("1.Add Shirt");
        System.out.println("2.Add Shorts");
        System.out.println("3.View all Clothing");
        System.out.println("4.Look only for shirts");
        System.out.println("5.Look only for shorts");
        System.out.println("6.Global event \"cloth\"");
        System.out.println("7.Hire Staff");
        System.out.println("8.View all of the Staff");
        System.out.println("9.Fire someone from staff");
        System.out.println("0.Exit");
    }

    @Override
    public void run(){
        boolean running = true;

        while(running){
            displayMenu();
            System.out.println("Enter your choice");

            try{
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice){
                    case 1:
                        addShirt();
                        break;
                    case 2:
                        addShorts();
                        break;
                    case 3:
                        viewClothing();
                        break;
                    case 4:
                        viewShirts();
                        break;
                    case 5:
                        viewShorts();
                        break;
                    case 6:
                        doCloth();
                        break;
                    case 7:
                        hireStaff();
                        break;
                    case 8:
                        viewStaff();
                        break;
                    case 9:
                        fireStaff();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Error, invalid input");
                }
            }
            catch (java.util.InputMismatchException e){
                System.out.println("Error, invalid input" + e.getMessage());
                scanner.nextLine();
            }
            catch (Exception e){
                System.out.println("Error" + e.getMessage());
                scanner.nextLine();
            }
        }
        System.out.println("Exiting");
        scanner.close();
    }

    private void addShirt(){
        System.out.println("\n---Addning Shirt---");

        try{
            System.out.println("Enter id ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter name ");
            String name = scanner.nextLine();

            System.out.println("Enter price ");
            double price = scanner.nextDouble();

            System.out.println("Enter size ");
            int size = scanner.nextInt();

            System.out.println("Enter sleeve length ");
            int sleeveLength =  scanner.nextInt();

            clothingList.add(new Shirt(id, name, price, size, sleeveLength));
            System.out.println(name + "Shirt added ");
        }
        catch (java.util.InputMismatchException e){
            System.out.println("Error invalid input");
            scanner.nextLine();
        }
        catch (IllegalArgumentException e){
            System.out.println("Error" + e.getMessage());
            scanner.nextLine();
        }
    }

    private void addShorts() {
        System.out.println("\n---Addning Shorts---");

        try {
            System.out.println("Enter id ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter name ");
            String name = scanner.nextLine();

            System.out.println("Enter price ");
            double price = scanner.nextDouble();

            System.out.println("Enter size ");
            int size = scanner.nextInt();

            System.out.println("Enter waist length ");
            int waist = scanner.nextInt();

            clothingList.add(new Shirt(id, name, price, size, waist));
            System.out.println(name + "Short added");
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error invalid input");
            scanner.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println("Error" + e.getMessage());
            scanner.nextLine();
        }
    }
    public void viewClothing(){
        System.out.println("\n---All clothing---");

        if(clothingList.isEmpty()){
            System.out.println("No clothing found");
            return;
        }

        for(int i=0; i<clothingList.size(); i++){
            Clothing s = clothingList.get(i);
            System.out.println( (i + 1) + ". ");

            if(s instanceof Shirt){
                System.out.println("[Shirt] ");
            } else if (s instanceof Shorts) {
                System.out.println("[Shorts]");
            }
            System.out.println(s.toString());
        }
    }

    private void viewShirts(){
        System.out.println("\n---Shirts---");
        boolean found = false;

        for(Clothing s : clothingList){
            if(s instanceof Shirt){
                Shirt shirt = (Shirt) s;
                System.out.println(shirt.toString());
                System.out.println();
                found = true;
            }
        }
        if(!found){
            System.out.println("Shirt not found");
        }
    }

    private void  viewShorts(){
        System.out.println("\n---Shorts---");
        boolean found = false;

        for(Clothing s : clothingList){
            if(s instanceof Shorts){
                Shorts shorts = (Shorts) s;
                System.out.println(shorts.toString());
                System.out.println();
                found = true;
            }
        }
        if(!found){
            System.out.println("Shorts not found");
        }
    }

    private void doCloth(){
        System.out.println("\n---Doing Cloth---");

        if(clothingList.isEmpty()){
            System.out.println("No clothing found");
            return;
        }
        for(Clothing s: clothingList){
            s.cloth();
        }
    }

    private void hireStaff(){
        try {
            System.out.println("\n---Hire Staff---");
            System.out.println("Enter name ");
            String name = scanner.nextLine();

            System.out.println("Enter salary ");
            int salary = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter position ");
            String position = scanner.nextLine();

            staffList.add(new Staff(name, salary, position));
            System.out.println("Succesfully hired");
        }
        catch (java.util.InputMismatchException e){
            System.out.println("Error, invalid input type");
        }
        catch (IllegalArgumentException e){
            System.out.println("Failed validation" + e.getMessage());
        }
    }

    private void viewStaff(){
        System.out.println("/n---View Staff---");

        if(staffList.isEmpty()){
            System.out.println("There are no staff");
            return;
        }

        for(int i=0; i<staffList.size(); i++){
            System.out.println((i+1) +  ". " + staffList.get(i).getName());
        }
    }

    private void fireStaff(){
        System.out.println("\n---Firing Stuff---");

        if(staffList.isEmpty()){
            System.out.println("No staff available");
            return;
        }

        for(int i=0; i<staffList.size();i++){
            System.out.println((i+1) + ". " + staffList.get(i).getName());
            staffList.get(i).work();
        }

        try{
            System.out.println("Select someone to fire ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice<1 || choice>staffList.size()){
                throw new ExceptionHandling("Invalid choice ");
            }

            Staff staff = staffList.get(choice-1);
            staff.fire();
        }
        catch (java.util.InputMismatchException e){
            System.out.println("Error, invalid input");
        }
        catch (ExceptionHandling e){
            System.out.println("Error" + e.getMessage());
        }
    }
}
