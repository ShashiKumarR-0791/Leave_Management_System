package util;

import model.LeaveRequest;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {
	public static List<User> readUsers(String filePath) {
	    List<User> users = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] parts = line.split(",");
	            if (parts.length == 3) {
	                users.add(new User(parts[0].trim(), parts[1].trim(), parts[2].trim()));
	            } else {
	                System.err.println("Invalid user entry (skipped): " + line);
	            }
	        }
	    } catch (IOException e) {
	        System.err.println("Error reading users: " + e.getMessage());
	    }
	    return users;
	}



	public static void writeUsers(String filePath, List<User> users) {
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
	        for (User user : users) {
	            String line = user.getUsername() + "," + user.getPassword() + "," + user.getRole();
	            bw.write(line);
	            bw.newLine();
	        }
	    } catch (IOException e) {
	        System.err.println("Error writing users: " + e.getMessage());
	    }
	}


    public static List<LeaveRequest> readLeaves(String filePath) {
        List<LeaveRequest> leaves = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                leaves.add(new LeaveRequest(data[0], data[1]));
            }
        } catch (IOException e) {
            // Ignore
        }
        return leaves;
    }

    public static void writeLeaves(String filePath, List<LeaveRequest> leaves) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (LeaveRequest l : leaves) {
                bw.write(l.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing leaves.");
        }
    }
}