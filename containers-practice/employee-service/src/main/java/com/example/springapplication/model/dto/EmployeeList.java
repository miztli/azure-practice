package com.example.springapplication.model.dto;

import com.example.springapplication.model.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EmployeeList {

    public EmployeeList(List<Employee> employees) {
        this.employees = employees;
    }

    @JsonProperty("employees")
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public static EmployeeList from(List<Employee> employees) {
        return new EmployeeList(employees);
    }
}
