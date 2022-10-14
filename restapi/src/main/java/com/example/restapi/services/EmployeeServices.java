package com.example.restapi.services;

import com.example.restapi.entities.Employee;
import com.example.restapi.entities.EmployeeList;

import java.util.List;

public interface EmployeeServices {
    public List<EmployeeList> fetchNameAndId();
    public Employee fetchEmployeeDetails(int employeeId);
}