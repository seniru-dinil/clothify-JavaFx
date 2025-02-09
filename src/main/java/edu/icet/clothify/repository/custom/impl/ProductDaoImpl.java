package edu.icet.clothify.repository.custom.impl;


import edu.icet.clothify.entity.MostPurchasedProductEntity;
import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.repository.custom.ProductDao;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
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

    @Override
    public List<ProductEntity> getProductsByCategory(Integer productType) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Query<ProductEntity> query = session.createQuery(
                    "FROM ProductEntity p WHERE p.productCategoryID = :categoryId", ProductEntity.class);
            query.setParameter("categoryId", productType);

            List<ProductEntity> resultList = query.getResultList();
            transaction.commit();
            return resultList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ProductEntity> getProductsByStatus(Integer stock) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Query<ProductEntity> query = session.createQuery(
                        "FROM ProductEntity p WHERE p.productStock>0", ProductEntity.class);
                List<ProductEntity> resultList = query.getResultList();
                transaction.commit();
                return resultList;
            } catch (Exception e) {
                return null;
            }
        }
    }

    @Override
    public List<ProductEntity> getProductsByStatus() {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Query<ProductEntity> query = session.createQuery(
                        "FROM ProductEntity p WHERE p.productStock<=0", ProductEntity.class);
                List<ProductEntity> resultList = query.getResultList();
                transaction.commit();
                return resultList;
            } catch (Exception e) {
                return null;
            }
        }
    }

    @Override
    public List<ProductEntity> getProductsByName(String name) {
        try (Session session = HibernateUtil.getSession()) {
            try {
                String hql = "FROM ProductEntity p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :name, '%'))";
                Query<ProductEntity> query = session.createQuery(hql, ProductEntity.class);
                query.setParameter("name", name);
                return query.list();
            } catch (Exception e) {
                return null;
            }
        }
    }

    @Override
    public List<MostPurchasedProductEntity> getMostPurchasedProducts() {
        try (Session session = HibernateUtil.getSession()) {
            try {
                String hql = "SELECT od.product, SUM(od.quantity), SUM(od.quantity * od.price) " +
                        "FROM OrderDetailEntity od " +
                        "GROUP BY od.product " +
                        "ORDER BY SUM(od.quantity) DESC";

                Query<Object[]> query = session.createQuery(hql, Object[].class);
                query.setMaxResults(3);
                List<Object[]> results = query.getResultList();
                List<MostPurchasedProductEntity> mostPurchasedProductEntities = new ArrayList<>();
                results.forEach(i -> {
                    mostPurchasedProductEntities.add(new MostPurchasedProductEntity(
                            (ProductEntity) i[0],
                            (Long) i[1],
                            (Double) i[2]
                    ));
                });
                return mostPurchasedProductEntities;
            } catch (Exception e) {
                return null;
            }
        }
    }

    @Override
    public boolean updateProductByQuantity(Integer qty, Integer productId) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                boolean isUpdated = session.createQuery("UPDATE ProductEntity SET productStock = :qty WHERE productID = :productId")
                        .setParameter("qty", qty)
                        .setParameter("productId", productId)
                        .executeUpdate() > 0;
                if (isUpdated) transaction.commit();
                return isUpdated;
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                return false;
            }
        }
    }

    @Override
    public boolean updateProductByPrice(Double price, Integer pid) {
        try(Session session = HibernateUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            try {
                boolean b = session.createQuery("UPDATE ProductEntity SET productPrice = :price WHERE productID = :pid")
                        .setParameter("price", price)
                        .setParameter("pid", pid)
                        .executeUpdate() > 0;
                if (b){
                    transaction.commit();
                    return b;
                }else {
                    transaction.rollback();
                }
            }catch (Exception e){
                if (transaction!=null)transaction.rollback();
                return false;
            }
        }
        return false;
    }

    @Override
    public Integer getTotalProductCount() {
        try(Session session = HibernateUtil.getSession()){
            return session.createQuery("SELECT COUNT(p) FROM ProductEntity p",Long.class)
                    .uniqueResult()
                    .intValue();
        }catch (Exception e){
            return 0;
        }
    }
}
