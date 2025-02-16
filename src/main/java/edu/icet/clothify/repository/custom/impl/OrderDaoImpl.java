package edu.icet.clothify.repository.custom.impl;

import edu.icet.clothify.dto.OrderTable;
import edu.icet.clothify.entity.OrderEntity;
import edu.icet.clothify.repository.custom.OrderDao;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
            return session.createQuery("FROM OrderEntity ORDER BY orderId DESC", OrderEntity.class)
                    .setMaxResults(1)
            .uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Double getTotalSales() {
        try(Session session=HibernateUtil.getSession()){
            return session.createQuery("SELECT SUM(o.orderTotal) FROM OrderEntity o", Double.class)
                    .uniqueResult();
        }catch (Exception e){
            return 0.0;
        }
    }

    @Override
    public Integer getOrdersCount() {
      try(Session session = HibernateUtil.getSession()){
          return session.createQuery("SELECT COUNT(o) FROM OrderEntity o", Long.class)
                  .uniqueResult()
                  .intValue();
      }catch (Exception e){
          return 0;
      }
    }

    @Override
    public List<OrderTable> getOrderTableData() {
        try (Session session = HibernateUtil.getSession()) {

            String hql = "SELECT o.orderId, c.firstName, c.lastName, c.mobileNumber, o.orderTotal, o.orderDate " +
                    "FROM OrderEntity o " +
                    "JOIN o.customerId c";


            List<Object[]> results = session.createQuery(hql, Object[].class).getResultList();


            return results.stream()
                    .map(row -> new OrderTable(
                            (Integer) row[0],
                            row[1] + " " + row[2],
                            (String) row[3],
                            (Double) row[4],
                            (LocalDateTime) row[5]
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
