package org.example.model;

//import java.time.LocalDate;
//import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="employye_details",schema = "dbo")
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id")
    private Integer id;
    private String firstName;
    private String lastName;

    private String ssn;
  //  private LocalDate dob;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Date dob;
    private String city;
    private String zip;

}
