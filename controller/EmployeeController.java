package controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import model.LeaveRequest;
import model.User;
import service.LeaveService;

public class EmployeeController {
    private final LeaveService leaveService = new LeaveService();
    private final Scanner scanner = new Scanner(System.in);

    private final User user;

    public EmployeeController(User user) {
        this.user = user;
    }

    public void showEmployeeMenu() {
        while (true) {
            System.out.println("\n--- Employee Menu ---");
            System.out.println("1. Apply Leave");
            System.out.println("2. View My Leaves");
            System.out.println("0. Logout");
            System.out.print("Select: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> applyLeave();
                case 2 -> viewLeaves();
                case 0 -> { return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void applyLeave() {
        System.out.print("Enter start date (YYYY-MM-DD): ");
        LocalDate start = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter end date (YYYY-MM-DD): ");
        LocalDate end = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter reason: ");
        String reason = scanner.nextLine();
        leaveService.applyLeave(user, start, end, reason);
        System.out.println("Leave applied successfully.");
    }

    private void viewLeaves() {
        List<LeaveRequest> leaves = leaveService.getEmployeeLeaves(user.getUsername());
        leaves.forEach(System.out::println);
    }
}