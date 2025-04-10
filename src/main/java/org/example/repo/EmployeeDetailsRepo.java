package org.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.example.model.EmployeeDetails;

public interface EmployeeDetailsRepo extends JpaRepository<EmployeeDetails, Integer> {

    //custom menthods for SSN because of uniqueness
    boolean existsBySsn(String ssn);

    //String findBySsn(String ssn);
    EmployeeDetails findBySsn(String ssn);

    void deleteBySsn(String ssn);

}
