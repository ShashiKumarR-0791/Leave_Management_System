package util;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import model.LeaveRequest;
import model.User;

public class CSVUtil {

    public static List<User> readUsers(String filePath) {
        List<User> users = new ArrayList<>();
        try {
            Files.createDirectories(Paths.get(filePath).getParent());
            File file = new File(filePath);
            if (!file.exists()) file.createNewFile();

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length >= 3) {
                    User user = new User(parts[0], parts[1], parts[2]);
                    if (parts.length >= 4 && !parts[3].isEmpty())
                        user.setFailedAttempts(Integer.parseInt(parts[3]));
                    if (parts.length == 5 && !parts[4].isEmpty())
                        user.setLockoutTime(LocalDateTime.parse(parts[4]));
                    users.add(user);
                } else {
                    System.out.println("Invalid user entry (skipped): " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void writeUsers(String filePath, List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (User user : users) {
                writer.write(user.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<LeaveRequest> readLeaves(String filePath) {
        List<LeaveRequest> leaves = new ArrayList<>();
        try {
            Files.createDirectories(Paths.get(filePath).getParent());
            File file = new File(filePath);
            if (!file.exists()) file.createNewFile();

            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length == 5) {
                    LeaveRequest leave = new LeaveRequest(
                            parts[0],
                            LocalDate.parse(parts[1]),
                            LocalDate.parse(parts[2]),
                            parts[3],
                            parts[4]
                    );
                    leaves.add(leave);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return leaves;
    }

    public static void writeLeaves(String filePath, List<LeaveRequest> leaves) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (LeaveRequest leave : leaves) {
                writer.write(leave.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
