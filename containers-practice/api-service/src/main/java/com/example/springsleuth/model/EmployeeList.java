package com.example.springsleuth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EmployeeList {

    @JsonProperty("employees")
    private List<Employee> employees;

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
