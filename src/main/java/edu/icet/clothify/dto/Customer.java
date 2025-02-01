package edu.icet.clothify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Customer {
    private Integer customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String address;
}
