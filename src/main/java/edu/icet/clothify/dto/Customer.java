package edu.icet.clothify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String address;
}
