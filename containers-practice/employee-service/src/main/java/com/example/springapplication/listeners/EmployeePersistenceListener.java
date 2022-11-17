package com.example.springapplication.listeners;

import com.example.springapplication.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PostPersist;

public class EmployeePersistenceListener {

    private final static Logger LOG = LoggerFactory.getLogger(EmployeePersistenceListener.class);

    @PostPersist
    public void afterEmployeeCreated(final Employee employee) {
        LOG.info("Employee with name [{}] was persisted with id [{}]", employee.getName(), employee.getId());
    }
}
