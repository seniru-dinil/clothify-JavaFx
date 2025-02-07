package edu.icet.clothify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
  private int productID;
  private String productName;
  private Double productPrice;
  private int productStock;
  private String productImagePath;
  private int productCategoryID;
  private int productSupplierID;
  private String productDescription;

  @Override
  public boolean equals(Object o) {
    Product o1 = (Product) o;
    return o1.getProductName().equals(this.productName);
  }
}
