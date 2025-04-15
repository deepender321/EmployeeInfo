package org.example.service.impl;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org com.example.demo.dto.DeletionDto;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//mport org.example.Exception.EmployeeDoesNotExistsException;
//import org.example.Exception.SsnAlreadyExistsException;
//import com.example.demo.model.EmployeeDetails;
//import com.example.demo.repo.EmployeeDetailsRepo;
//import com.example.demo.service.EmployeeService;

//import org.example.service.Exception.EmployeeDoesNotExistsException;
//import org.example.service.Exception.SsnAlreadyExistsException;


import org.example.Exception.EmployeeDoesNotExistsException;
import org.example.Exception.SsnAlreadyExistsException;
import org.example.dto.DeletionDto;
import org.example.model.EmployeeDetails;
import org.example.repo.EmployeeDetailsRepo;
import org.example.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService  {

    private final EmployeeDetailsRepo employeeDetailsRepo;

    public EmployeeServiceImpl(EmployeeDetailsRepo employeeDetailsRepo) {
        this.employeeDetailsRepo=employeeDetailsRepo;
    }

//	public EmployeeDetails createEmployee(EmployeeDetails employeeDetails) {
//		 if (employeeDetailsRepo.existsBySsn(employeeDetails.getSsn())) {
//	            throw new SsnAlreadyExistsException("Employee with SSN already exists");
//	        }
//		return employeeDetailsRepo.save(employeeDetails);
//	}

    // @Override
    // public List<EmployeeDetails> createMultipleEmployees(List<EmployeeDetails> employees) {
    //     List<EmployeeDetails> toBeSaved = new ArrayList<>();

    //     for (EmployeeDetails emp : employees) {
    //         if (!employeeDetailsRepo.existsBySsn(emp.getSsn())) {
    //             toBeSaved.add(emp);
    //         }
    //     }

    //     return employeeDetailsRepo.saveAll(toBeSaved);
    // }


      @Override
    public List<EmployeeDetails> createMultipleEmployees(List<EmployeeDetails> employees) {
        List<EmployeeDetails> toBeSaved = new ArrayList<>();
        for (EmployeeDetails emp : employees) {
            if (employeeDetailsRepo.existsBySsn(emp.getSsn())) {
                throw new SsnAlreadyExistsException("Employee with SSN already exists");
            }
            toBeSaved.add(emp);
        }
        return employeeDetailsRepo.saveAll(toBeSaved);
    }

    public EmployeeDetails getEmployeeBySsn(String ssn) {
        return employeeDetailsRepo.findBySsn(ssn);
    }


    public EmployeeDetails updateEmploye(EmployeeDetails employeeDetails) {
        if (employeeDetailsRepo.findById(employeeDetails.getId()).isEmpty()) {
            throw new EmployeeDoesNotExistsException("Employee with employeeId does not exists");
        }
        return employeeDetailsRepo.save(employeeDetails);
    }

    public void deleteEmployee(int id) {
        if (employeeDetailsRepo.findById(id).isEmpty()) {
            throw new EmployeeDoesNotExistsException("Employee with employeeId does not exists");
        }
        employeeDetailsRepo.deleteById(id);
    }

    @Override
    public void deleteMultipleEmployees(DeletionDto deletionDto) {
        employeeDetailsRepo.deleteAllById(deletionDto.getMultiEmployeeIds());
    }

    public EmployeeDetails getEmployeeDetails(int id) {

        return employeeDetailsRepo.findById(id).orElse(null);

    }

    public List<EmployeeDetails> getEmployeeDetails() {
        return employeeDetailsRepo.findAll();
    }
}
