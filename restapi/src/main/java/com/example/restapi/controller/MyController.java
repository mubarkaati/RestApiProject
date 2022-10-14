package com.example.restapi.controller;

import com.example.restapi.entities.Employee;
import com.example.restapi.entities.EmployeeList;
import com.example.restapi.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    EmployeeServices employeeServices;

    @GetMapping("/fetchNameAndId")
    public List<EmployeeList> fetchNameAndId() {
        return employeeServices.fetchNameAndId();
    }

    @GetMapping("/fetchEmployee/{employeeId}")
    public Employee fetchEmployeeDetails(@PathVariable String employeeId) {
        return employeeServices.fetchEmployeeDetails(Integer.parseInt(employeeId));
    }
}
