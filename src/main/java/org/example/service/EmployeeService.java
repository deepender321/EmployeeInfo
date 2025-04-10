package org.example.service;

import org.example.dto.DeletionDto;
import org.example.model.EmployeeDetails;

import java.util.List;


public interface EmployeeService {

    //EmployeeDetails createEmployee(EmployeeDetails employeeDetails);

    List<EmployeeDetails> createMultipleEmployees(List<EmployeeDetails> employeeDetails);

    EmployeeDetails updateEmploye(EmployeeDetails employeeDetails);

    EmployeeDetails getEmployeeBySsn(String ssn);

    void deleteEmployee(int id);

    void deleteMultipleEmployees(DeletionDto deletionDto);

    EmployeeDetails getEmployeeDetails(int id);

    List<EmployeeDetails> getEmployeeDetails();
}
