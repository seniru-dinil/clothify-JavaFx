package edu.icet.clothify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Product {
  private int productID;
  private String productName;
  private Double productPrice;
  private String productDescription;
  private int productStock;
  private String productImagePath;
  private int productCategoryID;
  private int productSupplierID;
}
