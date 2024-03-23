// Write your code here
package com.example.employee.model;

import java.util.*;

public class Employee {

    private int employeeId;
    private String employeeName;
    private String email;
    private String department;

    public Employee(int employeeId, String employeeName, String email, String department) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.email = email;
        this.department = department;
    }

    // Get and Set Method of employeeId
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return this.employeeId;
    }

    // Get and Set Method of employeeName
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    // Get and Set Method of email
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    // Get and Set Method of department
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return this.department;
    }
}