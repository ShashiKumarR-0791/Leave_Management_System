package controller;

import model.User;

import java.util.Scanner;

public class EmployeeController {
    private final User user;

    public EmployeeController(User user) {
        this.user = user;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Employee Menu ---");
            System.out.println("1. Apply for Leave");
            System.out.println("2. View My Leave Requests");
            System.out.println("0. Logout");
            System.out.print("Select: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> applyLeave();
                case 2 -> viewMyLeaves();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void applyLeave() {
        System.out.println("Applying for leave (TODO: Implement logic).");
    }

    private void viewMyLeaves() {
        System.out.println("Viewing your leave requests (TODO: Implement logic).");
    }
}
