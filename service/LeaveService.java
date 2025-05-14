package service;

import model.LeaveRequest;
import model.User;
import util.CSVUtil;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LeaveService {
    private final String filePath = "leaves.csv";

    public void applyLeave(User user) {
        List<LeaveRequest> leaves = CSVUtil.readLeaves(filePath);
        leaves.add(new LeaveRequest(user.getUsername(), "PENDING"));
        CSVUtil.writeLeaves(filePath, leaves);
        System.out.println("Leave request submitted.");
    }

    public void approveLeave(User manager) {
        List<LeaveRequest> leaves = CSVUtil.readLeaves(filePath);
        List<LeaveRequest> pending = leaves.stream()
                .filter(l -> l.getStatus().equals("PENDING"))
                .collect(Collectors.toList());

        for (int i = 0; i < pending.size(); i++) {
            System.out.println(i + 1 + ". " + pending.get(i));
        }

        if (!pending.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter leave number to approve: ");
            int index = scanner.nextInt() - 1;
            pending.get(index).setStatus("APPROVED");
            CSVUtil.writeLeaves(filePath, leaves);
            System.out.println("Leave approved.");
        } else {
            System.out.println("No pending leaves.");
        }
    }
}