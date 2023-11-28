package com.mysystem;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourierSystem {
    private List<User> users;
    private List<Order> orders;
    private static final String USER_DETAILS_FILE = "userDetails.txt";
    private static final String ORDER_DETAILS_FILE = "orderDetails.txt";
    private static final String DELIMITER = ",";

    public CourierSystem() {
        this.users = new ArrayList<>();
        this.orders = new ArrayList<>();
        loadUsers();
        loadOrders();
    }

    private void loadUsers() {
        try (Scanner scanner = new Scanner(new File(USER_DETAILS_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(DELIMITER);
                users.add(new User(parts[0], parts[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("User details file not found.");
        }
    }

    private void loadOrders() {
        try (Scanner scanner = new Scanner(new File(ORDER_DETAILS_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(DELIMITER);
                orders.add(new Order(parts[0], parts[1], Double.parseDouble(parts[2])));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Order details file not found.");
        }
    }

    public void saveUsers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_DETAILS_FILE))) {
            for (User user : users) {
                writer.println(user.getUsername() + DELIMITER + user.getPassword());
            }
        } catch (IOException e) {
            System.out.println("Error saving user details.");
        }
    }

    public void saveOrders() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ORDER_DETAILS_FILE))) {
            for (Order order : orders) {
                writer.println(order.getOrderId() + DELIMITER + order.getOrderName() + DELIMITER + order.getServicePrice());
            }
        } catch (IOException e) {
            System.out.println("Error saving order details.");
        }
    }

    public void registerUser(String username, String password) {
        users.add(new User(username, password));
        saveUsers();
        System.out.println("User registered successfully!");
    }

    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                System.out.println("Login successful!");
                return user;
            }
        }
        System.out.println("Invalid username or password.");
        return null;
    }

    public void addOrder(User user, String orderId, String orderName, double servicePrice) {
        if (user != null) {
            orders.add(new Order(orderId, orderName, servicePrice));
            saveOrders();
            System.out.println("Order added successfully!");
        } else {
            System.out.println("Please log in first.");
        }
    }

    public void viewOrders(User user) {
        if (user != null) {
            if (orders.isEmpty()) {
                System.out.println("No orders available.");
            } else {
                System.out.println("Orders:");
                for (Order order : orders) {
                    System.out.println(order);
                }
            }
        } else {
            System.out.println("Please log in first.");
        }
    }

    public void deleteOrder(User user, String orderId) {
        if (user != null) {
            orders.removeIf(order -> order.getOrderId().equals(orderId));
            saveOrders();
            System.out.println("Order deleted successfully!");
        } else {
            System.out.println("Please log in first.");
        }
    }
}
