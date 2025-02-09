package edu.icet.clothify.service.custom.impl;

import edu.icet.clothify.dto.Order;
import edu.icet.clothify.entity.OrderEntity;
import edu.icet.clothify.repository.DaoFactory;
import edu.icet.clothify.repository.custom.OrderDao;
import edu.icet.clothify.service.custom.OrderService;
import edu.icet.clothify.util.enums.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public boolean addOrder(Order order) {
        OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);
        return orderDao.save(new ModelMapper().map(order, OrderEntity.class));
    }

    @Override
    public boolean deleteOrder(Integer orderId) {
        OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);
        return orderDao.delete(orderId);
    }

    @Override
    public List<Order> getOrderList() {
        OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);
        List<OrderEntity> all = orderDao.getAll();
        List<Order> list = new ArrayList<>();
        all.forEach(o->list.add(new ModelMapper().map(o,Order.class)));
        return list;
    }

    @Override
    public Order getLastOrder() {
        OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);
        OrderEntity lastOrder = orderDao.getLastOrder();
        return  lastOrder==null?null:new ModelMapper().map(lastOrder,Order.class);
    }

    @Override
    public List<Order> getOrdersByDate() {
        return List.of();
    }

    @Override
    public Double getTotalSales() {
        OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);
        return orderDao.getTotalSales();
    }

    @Override
    public Integer getOrdersCount() {
        OrderDao orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);
        return orderDao.getOrdersCount();
    }
}
