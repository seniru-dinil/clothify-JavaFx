package edu.icet.clothify.service.custom.impl;

import edu.icet.clothify.dto.OrderDetail;
import edu.icet.clothify.entity.OrderDetailEntity;
import edu.icet.clothify.repository.DaoFactory;
import edu.icet.clothify.repository.custom.OrderDetailDao;
import edu.icet.clothify.service.custom.OrderDetailService;
import edu.icet.clothify.util.enums.DaoType;
import org.modelmapper.ModelMapper;

public class OrderDetailServiceImpl implements OrderDetailService {
    @Override
    public OrderDetailEntity getAll() {
        return null;
    }

    @Override
    public boolean save(OrderDetail orderDetail) {
        OrderDetailDao orderDetailDao = DaoFactory.getInstance().getDao(DaoType.ORDER_DETAIL);
        return orderDetailDao.save(new ModelMapper().map(orderDetail,OrderDetailEntity.class));
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
