package edu.icet.clothify.repository.custom.impl;

import edu.icet.clothify.entity.SupplierEntity;
import edu.icet.clothify.repository.custom.SupplierDao;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity supplierEntity)  {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(supplierEntity);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            }
        }
    }

    @Override
    public boolean delete(Integer t) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                SupplierEntity supplier = session.get(SupplierEntity.class,t);
                if (supplier != null) {
                    session.remove(supplier);
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
    public SupplierEntity get(String id) {
        try (Session session = HibernateUtil.getSession()) {
            SupplierEntity fetchedUser = session.get(SupplierEntity.class,id);
            return fetchedUser;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean update(SupplierEntity supplierEntity) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try{
                SupplierEntity supplier = session.get(SupplierEntity.class, supplierEntity.getSupplierId());
                if (supplier == null)return false;
                supplier.setSupplierAddress(supplierEntity.getSupplierAddress());
                supplier.setSupplierName(supplierEntity.getSupplierName());
                supplier.setSupplierEmail(supplierEntity.getSupplierEmail());
                session.merge(supplier);
                session.getTransaction().commit();
                return true;
            }catch (Exception e){
                transaction.rollback();
                return false;
            }
        }
    }

    @Override
    public List<SupplierEntity> getAll() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Query<SupplierEntity> query = session.createQuery("FROM SupplierEntity", SupplierEntity.class);
        List<SupplierEntity> supplierEntities = query.list();
        tx.commit();
        return supplierEntities;
    }

    public List<SupplierEntity> getSuppliersByName(String name){
        try(Session session= HibernateUtil.getSession()){
            try{
                Transaction transaction = session.beginTransaction();
                String hql = "FROM SupplierEntity s WHERE LOWER(s.supplierName) LIKE LOWER(CONCAT('%', :name, '%'))";
                Query<SupplierEntity> query = session.createQuery(hql, SupplierEntity.class);
                query.setParameter("name", name);
                return query.list();
            }catch (Exception e){
                return  null;
            }
        }
    }

    @Override
    public Integer getTotalSupplierCount() {
        try(Session session = HibernateUtil.getSession()){
            return session.createQuery("SELECT COUNT(s) FROM SupplierEntity s",Long.class)
                    .uniqueResult()
                    .intValue();
        }catch (Exception e){
            return 0;
        }
    }
}
