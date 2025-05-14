package controller;

import java.util.Scanner;

public class ManagerController {

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Manager Menu ---");
            System.out.println("1. View Leave Requests");
            System.out.println("2. Approve/Reject Leave");
            System.out.println("0. Logout");
            System.out.print("Select: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> viewLeaveRequests();
                case 2 -> approveOrRejectLeave();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void viewLeaveRequests() {
        System.out.println("Displaying leave requests (TODO: Implement logic).");
        // LeaveController.viewAllPendingRequests();
    }

    private void approveOrRejectLeave() {
        System.out.println("Approving/rejecting leave (TODO: Implement logic).");
        // LeaveController.processLeaveRequest();
    }
}
