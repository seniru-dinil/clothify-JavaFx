package edu.icet.clothify.service.custom;

import edu.icet.clothify.dto.Order;
import edu.icet.clothify.dto.OrderTable;
import edu.icet.clothify.service.SupperService;

import java.util.List;

public interface OrderService extends SupperService {
    boolean addOrder(Order order);
    boolean deleteOrder(Integer orderId);
    List<Order> getOrderList();
    Order getLastOrder();
    List<Order> getOrdersByDate();
    Double getTotalSales();
    Integer getOrdersCount();
    List<OrderTable> getOrderTableData();
}
