package edu.icet.clothify.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MostPurchasedProductEntity {
    private ProductEntity product;
    private Long totalQuantity;
    private Double totalSales;
}
