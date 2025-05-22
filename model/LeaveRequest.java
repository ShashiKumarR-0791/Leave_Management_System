//package model;
//
//public class LeaveRequest {
//    private String employee;
//    private String status;
//
//    public LeaveRequest(String employee, String status) {
//        this.employee = employee;
//        this.status = status;
//    }
//
//    public String getEmployee() { return employee; }
//    public String getStatus() { return status; }
//    public void setStatus(String status) { this.status = status; }
//
//    @Override
//    public String toString() {
//        return employee + "," + status;
//    }
//}
package model;

import java.time.LocalDate;

public class LeaveRequest {
    private String employeeUsername;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String status; // pending, approved, rejected

    public LeaveRequest(String employeeUsername, LocalDate startDate, LocalDate endDate, String reason, String status) {
        this.employeeUsername = employeeUsername;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
    }

    public String getEmployeeUsername() { return employeeUsername; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return employeeUsername + "," + startDate + "," + endDate + "," + reason + "," + status;
    }
}
