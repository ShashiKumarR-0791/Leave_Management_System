package controller;

import model.User;
import repository.UserRepository;

import java.util.List;
import java.util.Scanner;

public class AdminController {
    private final UserRepository repo = new UserRepository();
    private final Scanner scanner = new Scanner(System.in);

    public void showAdminMenu() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Delete User");
            System.out.println("4. Update User");
            System.out.println("0. Logout");
            System.out.print("Select: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addUser();
                case 2 -> viewUsers();
                case 3 -> deleteUser();
                case 4 -> updateUser();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void addUser() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Role (admin/manager/employee): ");
        String role = scanner.nextLine().toLowerCase();

        User user = new User(username, password, role);
        repo.addUser(user);
        System.out.println("User added successfully.");
    }

    private void viewUsers() {
        List<User> users = repo.getAllUsers();
        users.forEach(System.out::println);
    }

    private void deleteUser() {
        System.out.print("Enter username to delete: ");
        String username = scanner.nextLine();
        if (repo.deleteUser(username)) {
            System.out.println("User deleted.");
        } else {
            System.out.println("User not found.");
        }
    }

    private void updateUser() {
        System.out.print("Enter username to update: ");
        String username = scanner.nextLine();
        User user = repo.getUserByUsername(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.print("New password: ");
        String password = scanner.nextLine();
        System.out.print("New role: ");
        String role = scanner.nextLine();

        user.setPassword(password);
        user.setRole(role);
        repo.updateUser(user);
        System.out.println("User updated.");
    }
}
