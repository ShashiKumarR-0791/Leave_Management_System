package model;

public class LeaveRequest {
    private String employee;
    private String status;

    public LeaveRequest(String employee, String status) {
        this.employee = employee;
        this.status = status;
    }

    public String getEmployee() { return employee; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return employee + "," + status;
    }
}