package edu.icet.clothify.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Supplier {
    private int supplierId;
    private String supplierName;
    private String supplierEmail;
    private String supplierMobile;
    private String supplierAddress;
    private int categoryId;
}
