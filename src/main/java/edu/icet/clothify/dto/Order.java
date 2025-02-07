package edu.icet.clothify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private Integer orderId;
    private Integer customerId;
    private Double orderTotal;
    private LocalDateTime orderDate;
}
