package edu.icet.clothify.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "supplier")
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId;
    private String supplierName;
    private String supplierEmail;
    private String supplierMobile;
    private String supplierAddress;
    private int categoryId;
}
