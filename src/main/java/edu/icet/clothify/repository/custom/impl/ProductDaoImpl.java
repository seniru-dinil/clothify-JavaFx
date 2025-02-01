package edu.icet.clothify.repository.custom.impl;


import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.repository.custom.ProductDao;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public boolean save(ProductEntity productEntity) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(productEntity);
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
                ProductEntity productEntity = session.get(ProductEntity.class, t);
                if (productEntity != null) {
                    session.remove(productEntity);
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
    public ProductEntity get(String id) {
        try (Session session = HibernateUtil.getSession()) {
            ProductEntity productEntity = session.get(ProductEntity.class, id);
            return productEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(ProductEntity productEntity) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                ProductEntity product = session.get(ProductEntity.class, productEntity.getProductID());
                if (product == null) return false;
                product.setProductPrice(productEntity.getProductPrice());
                product.setProductStock(productEntity.getProductStock());
                session.merge(product);
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            }
        }
    }

    @Override
    public List<ProductEntity> getAll() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Query<ProductEntity> query = session.createQuery("FROM ProductEntity", ProductEntity.class);
        List<ProductEntity> productEntities = query.list();
        tx.commit();
        return productEntities;
    }
}
