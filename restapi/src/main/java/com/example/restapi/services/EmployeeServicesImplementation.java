package com.example.restapi.services;

import com.example.restapi.dao.ConnectionImplementation;
import com.example.restapi.entities.Employee;
import com.example.restapi.entities.EmployeeList;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServicesImplementation implements EmployeeServices {

    @Override
    public List<EmployeeList> fetchNameAndId() {
        Connection connection = ConnectionImplementation.getIConnection();
        List<EmployeeList> employeeLists = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee");
            while (resultSet.next()) {
                EmployeeList employeeList = new EmployeeList();
                employeeList.setEmployeeId(resultSet.getInt(1));
                employeeList.setEmployeeName(resultSet.getString(2));
                employeeLists.add(employeeList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeLists;
    }

    @Override
    public Employee fetchEmployeeDetails(int employeeId) {
        Connection connection = ConnectionImplementation.getIConnection();
        Employee employee = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee where employeeId =" + employeeId);
            if (resultSet.next()) {
                employee = new Employee(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }
}