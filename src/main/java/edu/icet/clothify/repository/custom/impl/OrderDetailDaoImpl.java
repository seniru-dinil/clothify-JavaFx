package edu.icet.clothify.repository.custom.impl;

import edu.icet.clothify.entity.OrderDetailEntity;
import edu.icet.clothify.repository.custom.OrderDetailDao;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    @Override
    public boolean save(OrderDetailEntity orderDetailEntity) {
        try(Session session = HibernateUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            try{
                session.merge(orderDetailEntity);
                transaction.commit();
                return true;
            }catch (Exception e){
                transaction.rollback();
                return false;
            }
        }
    }

    @Override
    public boolean delete(Integer t) {
        return false;
    }

    @Override
    public OrderDetailEntity get(String id) {
        return null;
    }

    @Override
    public boolean update(OrderDetailEntity orderDetailEntity) {
        return false;
    }

    @Override
    public List<OrderDetailEntity> getAll() {
        return List.of();
    }
}
