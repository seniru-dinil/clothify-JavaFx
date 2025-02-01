package edu.icet.clothify.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String email;
    private LocalDateTime joinedDate;
    private String password;
}
