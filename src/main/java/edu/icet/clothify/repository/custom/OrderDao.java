package edu.icet.clothify.repository.custom;

import edu.icet.clothify.dto.OrderTable;
import edu.icet.clothify.entity.OrderEntity;
import edu.icet.clothify.repository.CrudDao;

import java.util.List;

public interface OrderDao extends CrudDao<OrderEntity> {
    OrderEntity getLastOrder();
    Double getTotalSales();
    Integer getOrdersCount();
    List<OrderTable> getOrderTableData();
}
