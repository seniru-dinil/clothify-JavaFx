package edu.icet.clothify.repository.custom.impl;

import edu.icet.clothify.dto.Customer;
import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.repository.custom.CustomerDao;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(CustomerEntity customer){
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(customer);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            }
        }
    }

    @Override
    public boolean delete(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                CustomerEntity customer = session.get(CustomerEntity.class, id);
                if (customer != null) {
                    session.remove(customer);
                    transaction.commit();

                    return true;
                }
                return false;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            }
        }
    }

    @Override
    public CustomerEntity get(String name) {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM CustomerEntity WHERE firstName=:name", CustomerEntity.class)
                    .setParameter("name", name)
                    .uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }


    public List<CustomerEntity> getCustomersByName(String name){
        try (Session session = HibernateUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            try{
                String hql = "FROM CustomerEntity c WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%', :name, '%'))";
                Query<CustomerEntity> query = session.createQuery(hql, CustomerEntity.class);
                query.setParameter("name", name);
                return query.list();
            }catch (Exception e){
                return null;
            }
        }
    }

    @Override
    public Map<CustomerEntity,Double> getBestCustomers() {
        Map<CustomerEntity,Double> customerEntityDoubleMap = new HashMap<>();
        try(Session session= HibernateUtil.getSession()){
            try {
                String hql = "SELECT o.customerId, SUM(o.orderTotal) " +
                        "FROM OrderEntity o " +
                        "GROUP BY o.customerId " +
                        "ORDER BY SUM(o.orderTotal) DESC";

                Query<Object[]> query = session.createQuery(hql, Object[].class);
                query.setMaxResults(3);
                List<Object[]> results = query.getResultList();
                for (Object[] i:results){
                    customerEntityDoubleMap.put((CustomerEntity) i[0],(Double) i[1]);
                }
                return customerEntityDoubleMap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public boolean update(CustomerEntity customer) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try{
                CustomerEntity user = session.get(CustomerEntity.class, customer.getCustomerID());
                if (user == null)return false;
                user.setEmail(customer.getEmail());
                user.setAddress(customer.getAddress());
                user.setLastName(customer.getLastName());
                user.setFirstName(customer.getFirstName());
                user.setMobileNumber(customer.getMobileNumber());
                session.merge(user);
                session.getTransaction().commit();
                return true;
            }catch (Exception e){
                transaction.rollback();
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public List<CustomerEntity> getAll(){
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Query<CustomerEntity> query = session.createQuery("FROM CustomerEntity", CustomerEntity.class);
        List<CustomerEntity> customers = query.list();
        tx.commit();
        return customers;
    }
}
