package controller;

import model.User;
import service.AuthService;

import java.util.Scanner;

import exception.InvalidCredentialsException;

public class AuthController {
    private final AuthService authService = new AuthService();
    private User loggedInUser;

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            User user = authService.authenticate(username, password);
            System.out.println("Login successful! Welcome " + user.getUsername() + " (" + user.getRole() + ")");
            
            switch (user.getRole().toLowerCase()) {
                case "employee" -> {
                    EmployeeController empController = new EmployeeController(user);
                    empController.showEmployeeMenu();
                }
                case "manager" -> {
                    ManagerController mgrController = new ManagerController();
                    mgrController.showManagerMenu();
                }
                default -> System.out.println("Unknown role: " + user.getRole());
            }
        } catch (InvalidCredentialsException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }


    public User getLoggedInUser() {
        return loggedInUser;
    }
}
