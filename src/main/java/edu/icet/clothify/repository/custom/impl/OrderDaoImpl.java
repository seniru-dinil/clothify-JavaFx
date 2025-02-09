package edu.icet.clothify.repository.custom.impl;

import edu.icet.clothify.entity.OrderEntity;
import edu.icet.clothify.repository.custom.OrderDao;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderEntity orderEntity) {
        try(Session session = HibernateUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            try{
                session.merge(orderEntity);
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
        try(Session session = HibernateUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            try{
                OrderEntity orderEntity = session.get(OrderEntity.class, t);
                if (orderEntity!=null){
                    session.remove(orderEntity);
                    transaction.commit();
                    return true;
                }
                return false;
            }catch (Exception e){
                transaction.rollback();
                return false;
            }
        }
    }

    @Override
    public OrderEntity get(String id) {
        return null;
    }

    @Override
    public boolean update(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public List<OrderEntity> getAll() {
        try(Session session = HibernateUtil.getSession()){
            Query<OrderEntity> fromOrderEntity = session.createQuery("FROM OrderEntity", OrderEntity.class);
            return fromOrderEntity.list();
        }
    }

    @Override
    public OrderEntity getLastOrder() {
        try(Session session = HibernateUtil.getSession()){
            return session.createQuery("FROM OrderEntity ORDER BY orderID DESC", OrderEntity.class)
                    .setMaxResults(1)
            .uniqueResult();
        }
    }
}
