package service;

import model.User;
import repository.UserRepository;

import java.util.List;
import java.util.Scanner;

public class UserService {
    private final UserRepository repo = new UserRepository();

    public void addUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (ADMIN/MANAGER/EMPLOYEE): ");
        String role = scanner.nextLine();

        List<User> users = repo.getAllUsers();
        users.add(new User(username, password, role));
        repo.saveUsers(users);
        System.out.println("User added successfully.");
    }

    public void viewUsers() {
        repo.getAllUsers().forEach(System.out::println);
    }
}