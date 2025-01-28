package edu.icet.clothify.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Collection;

@Data
@ToString
@AllArgsConstructor
public class Employee {
    private int employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String email;
    private String joinedDate;
    private String password;
}
