package controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import model.LeaveRequest;
import model.User;
import service.LeaveService;

public class LeaveController {
    private final LeaveService leaveService = new LeaveService();
    private final Scanner scanner = new Scanner(System.in);

    // Employee menu to apply and view leaves
    public void employeeMenu(User user) {
        while (true) {
            System.out.println("\n--- Employee Leave Menu ---");
            System.out.println("1. Apply for Leave");
            System.out.println("2. View My Leaves");
            System.out.println("0. Logout");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> applyLeave(user);
                case 2 -> viewMyLeaves(user);
                case 0 -> {
                    System.out.println("Logging out from Employee menu.");
                    return;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    public void managerMenu() {
        while (true) {
            System.out.println("\n--- Manager Leave Menu ---");
            System.out.println("1. View Pending Leave Requests");
            System.out.println("0. Logout");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> approvePendingLeaves();
                case 0 -> {
                    System.out.println("Logging out from Manager menu.");
                    return;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private void applyLeave(User user) {
        try {
            System.out.print("Enter start date (YYYY-MM-DD): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter end date (YYYY-MM-DD): ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter reason for leave: ");
            String reason = scanner.nextLine();

            leaveService.applyLeave(user, startDate, endDate, reason);
            System.out.println("Leave applied successfully and pending approval.");
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    private void viewMyLeaves(User user) {
        List<LeaveRequest> leaves = leaveService.getEmployeeLeaves(user.getUsername());
        if (leaves.isEmpty()) {
            System.out.println("No leave requests found.");
        } else {
            System.out.println("Your leave requests:");
            leaves.forEach(System.out::println);
        }
    }

    private void approvePendingLeaves() {
        List<LeaveRequest> pendingLeaves = leaveService.getPendingLeaves();
        if (pendingLeaves.isEmpty()) {
            System.out.println("No pending leave requests.");
            return;
        }

        for (LeaveRequest leave : pendingLeaves) {
            System.out.println("\nLeave Request:");
            System.out.println(leave);

            System.out.print("Approve (A) / Reject (R) / Skip (S): ");
            String decision = scanner.nextLine().trim().toUpperCase();

            switch (decision) {
                case "A" -> {
                    leaveService.updateLeaveStatus(leave, "approved");
                    System.out.println("Leave approved.");
                }
                case "R" -> {
                    leaveService.updateLeaveStatus(leave, "rejected");
                    System.out.println("Leave rejected.");
                }
                case "S" -> System.out.println("Skipped.");
                default -> System.out.println("Invalid input. Skipped.");
            }
        }
    }
}
