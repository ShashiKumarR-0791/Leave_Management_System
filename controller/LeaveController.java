package controller;

import model.User;
import service.LeaveService;

import java.util.Scanner;

public class LeaveController {
    private final LeaveService leaveService = new LeaveService();

    public void employeeMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Apply for Leave");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            leaveService.applyLeave(user);
        }
    }

    public void managerMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. View Pending Leaves");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            leaveService.approveLeave(user);
        }
    }
}