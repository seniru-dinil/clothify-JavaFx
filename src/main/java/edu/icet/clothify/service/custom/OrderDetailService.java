package edu.icet.clothify.service.custom;

import edu.icet.clothify.dto.OrderDetail;
import edu.icet.clothify.entity.OrderDetailEntity;
import edu.icet.clothify.service.SupperService;

public interface OrderDetailService extends SupperService {
    OrderDetailEntity getAll();
    boolean save(OrderDetail orderDetail);
    boolean delete(Integer id);
}
