package controller;

import java.util.List;
import java.util.Scanner;
import model.LeaveRequest;
import service.LeaveService;

public class ManagerController {
    private final LeaveService leaveService = new LeaveService();
    private final Scanner scanner = new Scanner(System.in);

    public void showManagerMenu() {
        while (true) {
            System.out.println("\n--- Manager Menu ---");
            System.out.println("1. View Pending Leaves");
            System.out.println("0. Logout");
            System.out.print("Select: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> approveOrRejectLeaves();
                case 0 -> { return; }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private void approveOrRejectLeaves() {
        List<LeaveRequest> pendingLeaves = leaveService.getPendingLeaves();
        if (pendingLeaves.isEmpty()) {
            System.out.println("No pending leave requests.");
            return;
        }

        for (LeaveRequest leave : pendingLeaves) {
            System.out.println(leave);
            System.out.print("Approve (A) / Reject (R): ");
            String action = scanner.nextLine().toUpperCase();
            if (action.equals("A")) {
                leaveService.updateLeaveStatus(leave, "approved");
                System.out.println("Leave approved.");
            } else if (action.equals("R")) {
                leaveService.updateLeaveStatus(leave, "rejected");
                System.out.println("Leave rejected.");
            } else {
                System.out.println("Invalid input. Skipped.");
            }
        }
    }
}
