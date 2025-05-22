package service;

import java.time.LocalDate;
import java.util.List;
import model.LeaveRequest;
import model.User;
import repository.LeaveRepository;

public class LeaveService {
    private final LeaveRepository leaveRepo = new LeaveRepository();

    public void applyLeave(User user, LocalDate start, LocalDate end, String reason) {
        LeaveRequest request = new LeaveRequest(user.getUsername(), start, end, reason, "pending");
        leaveRepo.addLeave(request);
    }

    public List<LeaveRequest> getPendingLeaves() {
        return leaveRepo.getLeavesByStatus("pending");
    }

    public List<LeaveRequest> getEmployeeLeaves(String username) {
        return leaveRepo.getLeavesByEmployee(username);
    }

    public void updateLeaveStatus(LeaveRequest leave, String status) {
        leaveRepo.updateLeaveStatus(leave, status);
    }
}