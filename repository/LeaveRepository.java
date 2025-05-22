package repository;

import java.util.List;
import java.util.stream.Collectors;
import model.LeaveRequest;
import util.CSVUtil;

public class LeaveRepository {
    private static final String FILE_PATH = "data/leaves.csv";

    public List<LeaveRequest> getAllLeaves() {
        return CSVUtil.readLeaves(FILE_PATH);
    }

    public void saveLeaves(List<LeaveRequest> leaves) {
        CSVUtil.writeLeaves(FILE_PATH, leaves);
    }

    public void addLeave(LeaveRequest leave) {
        List<LeaveRequest> leaves = getAllLeaves();
        leaves.add(leave);
        saveLeaves(leaves);
    }

    public List<LeaveRequest> getLeavesByStatus(String status) {
        return getAllLeaves().stream()
                .filter(l -> l.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public List<LeaveRequest> getLeavesByEmployee(String username) {
        return getAllLeaves().stream()
                .filter(l -> l.getEmployeeUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());
    }

    public void updateLeaveStatus(LeaveRequest updatedLeave, String status) {
        List<LeaveRequest> leaves = getAllLeaves().stream()
                .map(l -> {
                    if (l.getEmployeeUsername().equalsIgnoreCase(updatedLeave.getEmployeeUsername()) &&
                        l.getStartDate().equals(updatedLeave.getStartDate()) &&
                        l.getEndDate().equals(updatedLeave.getEndDate())) {
                        l.setStatus(status);
                    }
                    return l;
                })
                .collect(Collectors.toList());
        saveLeaves(leaves);
    }
}