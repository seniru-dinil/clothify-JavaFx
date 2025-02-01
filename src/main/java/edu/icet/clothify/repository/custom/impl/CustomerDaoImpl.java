package edu.icet.clothify.repository.custom.impl;

import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.repository.custom.CustomerDao;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

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
    public CustomerEntity get(String id) {
        try (Session session = HibernateUtil.getSession()) {
            CustomerEntity fetchedUser = session.get(CustomerEntity.class,id);
            if (fetchedUser!=null)return fetchedUser;
            return null;
        }catch (Exception e){
            return null;
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
