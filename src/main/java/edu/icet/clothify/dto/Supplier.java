package edu.icet.clothify.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private Integer supplierId;
    private String supplierName;
    private String supplierEmail;
    private String supplierMobile;
    private String supplierAddress;
    private int categoryId;
}
