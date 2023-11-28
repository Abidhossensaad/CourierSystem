package com.mysystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourierSystem courierSystem = new CourierSystem();
        User currentUser = null;

        while (true) {
            System.out.println("\nCourier Ordering System\n");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String registerUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String registerPassword = scanner.nextLine();
                    courierSystem.registerUser(registerUsername, registerPassword);
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    currentUser = courierSystem.loginUser(loginUsername, loginPassword);


                    if (currentUser != null) {
                        courierMenu(scanner, courierSystem, currentUser);
                    }
                    break;

                case 0:
                    System.out.println("Exiting program. Goodbye!");
                    courierSystem.saveUsers();
                    courierSystem.saveOrders();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void courierMenu(Scanner scanner, CourierSystem courierSystem, User user) {
        while (true) {
            System.out.println("\nCourier Menu\n");
            System.out.println("1. Add Order");
            System.out.println("2. View Orders");
            System.out.println("3. Delete Order");
            System.out.println("0. Logout");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter order ID: ");
                    String orderId = scanner.nextLine();
                    System.out.print("Enter order name: ");
                    String orderName = scanner.nextLine();
                    System.out.print("Enter service price: ");
                    double servicePrice = scanner.nextDouble();
                    courierSystem.addOrder(user, orderId, orderName, servicePrice);
                    break;

                case 2:
                    courierSystem.viewOrders(user);
                    break;

                case 3:
                    System.out.print("Enter order ID to delete: ");
                    String orderIdToDelete = scanner.nextLine();
                    courierSystem.deleteOrder(user, orderIdToDelete);
                    break;

                case 0:
                    System.out.println("Logging out. Goodbye!");
                    courierSystem.saveOrders();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
