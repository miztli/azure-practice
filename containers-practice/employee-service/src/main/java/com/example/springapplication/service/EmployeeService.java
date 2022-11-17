package com.example.springapplication.service;

import com.example.springapplication.model.Employee;
import com.example.springapplication.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class EmployeeService {

    @Resource
    private EmployeeRepository employeeRepository;

    @Transactional
    public Employee save(final Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * 1- Transaction is rolled back due to runtime exception. Transactional annotation supports rollbackOn / dontRollbackOn to allow persistence depending on the thrown exception
     * 2- {@link com.example.springapplication.listeners.EmployeePersistenceListener#afterEmployeeCreated(Employee)} is not notified
     */
    @Transactional
    public Employee saveAndThrowError(final Employee employee) {
        final Employee savedEmployee = employeeRepository.save(employee);

        if(!Objects.isNull(employee.getId())) {
            throw new RuntimeException("An error ocurred while saving employee");
        }

        return savedEmployee;
    }

    /**
     * 1- Transaction is not rolled back
     * 2- Employee gets persisted, since RuntimeException is allowed in @Transactional annotation
     * 3- {@link com.example.springapplication.listeners.EmployeePersistenceListener#afterEmployeeCreated(Employee)} is notified regardless of the exception
     */
    @Transactional(dontRollbackOn = {RuntimeException.class})
    public Employee saveAndThrowErrorAllowPersistence(final Employee employee) {
        final Employee savedEmployee = employeeRepository.save(employee);

        if(!Objects.isNull(employee.getId())) {
            throw new RuntimeException("An error ocurred while saving employee");
        }

        return savedEmployee;
    }
}
