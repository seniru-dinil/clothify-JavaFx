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
@Table(name = "product")
public class ProductEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int productID;
  private String productName;
  private Double productPrice;
  private int productStock;
  private String productImagePath;
  private int productCategoryID;
  private int productSupplierID;
  private String productDescription;
}
