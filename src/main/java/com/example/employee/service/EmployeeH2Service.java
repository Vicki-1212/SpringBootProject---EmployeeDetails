/*
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 * 
 */

// Write your code here
package com.example.employee.service;

import com.example.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.*;
import com.example.employee.model.Employee;
import com.example.employee.model.EmployeeRowMapper;

@Service
public class EmployeeH2Service implements EmployeeRepository {

    @Autowired
    public JdbcTemplate db;

    @Override
    public ArrayList<Employee> getEmployeeList() {
        List<Employee> employeeList = db.query("SELECT * FROM employeelist", new EmployeeRowMapper());
        ArrayList<Employee> employees = new ArrayList<>(employeeList);
        return employees;

    }

    @Override
    public Employee getEmployee(int employeeId) {
        try {
            Employee employee = db.queryForObject("SELECT * FROM employeelist WHERE employeeId = ?",
                    new EmployeeRowMapper(), employeeId);
            return employee;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Employee addEmployee(Employee employee) {
        db.update("INSERT INTO employeelist(employeeName, email, department) VALUES(?,?,?)", employee.getEmployeeName(),
                employee.getEmail(), employee.getDepartment());
        Employee savedEmployee = db.queryForObject("SELECT * FROM employeelist WHERE employeeName = ? and email = ?",
                new EmployeeRowMapper(), employee.getEmployeeName(), employee.getEmail());
        return savedEmployee;
    }

    @Override
    public Employee updateEmployee(int employeeId, Employee employee) {
        if (employee.getEmployeeName() != null) {
            db.update("UPDATE employeelist SET employeeName = ? WHERE employeeId = ?", employee.getEmployeeName(),
                    employeeId);
        }
        if (employee.getEmail() != null) {
            db.update("UPDATE employeelist SET email = ? WHERE employeeId = ?", employee.getEmail(), employeeId);
        }
        if (employee.getDepartment() != null) {
            db.update("UPDATE employeelist SET department = ? WHERE employeeId = ?", employee.getDepartment(),
                    employeeId);
        }
        return getEmployee(employeeId);
    }

    @Override
    public void deleteEmployee(int employeeId) {
        db.update("DELETE FROM employeelist WHERE employeeId = ?", employeeId);
    }

}
