package app;

import controller.AdminController;
import controller.AuthController;
import java.util.Scanner;

public class LeaveManagementApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		AuthController authController = new AuthController();
		AdminController adminController = new AdminController();

		while (true) {
			System.out.println("\n=== Leave Management System ===");
			System.out.println("1. Login as User");
			System.out.println("2. Login as Admin");
			System.out.println("0. Exit");
			System.out.print("Enter choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // clear buffer

			switch (choice) {
			case 1 -> authController.login();
			case 2 -> {
				System.out.print("Enter admin username: ");
				String username = scanner.nextLine();
				System.out.print("Enter admin password: ");
				String password = scanner.nextLine();

				if (username.equals("admin") && password.equals("admin123")) {
					System.out.println("Admin login successful!");
					adminController.showAdminMenu();
				} else {
					System.out.println("Invalid admin credentials.");
				}
			}
			case 0 -> {
				System.out.println("Exiting...");
				return;
			}
			default -> System.out.println("Invalid choice.");
			}
		}
	}
}
