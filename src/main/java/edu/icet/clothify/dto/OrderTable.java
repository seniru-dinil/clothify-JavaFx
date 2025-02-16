package edu.icet.clothify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderTable {
    private Integer orderId;
    private String customerName;
    private String mobileNumber;
    private Double orderTotal;
    private LocalDateTime orderDate;
}
