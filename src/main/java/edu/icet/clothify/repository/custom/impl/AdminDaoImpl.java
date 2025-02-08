package edu.icet.clothify.repository.custom.impl;

import edu.icet.clothify.entity.AdminEntity;
import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.repository.custom.AdminDao;
import edu.icet.clothify.util.HibernateUtil;
import edu.icet.clothify.util.PasswordUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AdminDaoImpl implements AdminDao {
    @Override
    public boolean save(AdminEntity adminEntity) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(adminEntity);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            }
        }
    }

    @Override
    public boolean update(AdminEntity adminEntity) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try{
                AdminEntity user = session.get(AdminEntity.class, adminEntity.getId());
                if (user == null)return false;
                user.setEmail(adminEntity.getEmail());
                user.setFirstName(adminEntity.getFirstName());
                user.setLastName(adminEntity.getLastName());
                user.setPassword(adminEntity.getPassword());
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
    public AdminEntity getAdmin(String email) {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM AdminEntity WHERE email = :email", AdminEntity.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(String email,String password) {
        String encryptPassword = PasswordUtil.getInstance().encryptPassword(password);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();

            int updatedRows = session.createQuery("UPDATE AdminEntity SET password = :password WHERE email = :email")
                    .setParameter("password", encryptPassword)
                    .setParameter("email", email)
                    .executeUpdate();
            transaction.commit();
            if (updatedRows == 0) {
                System.out.println("No admin found with email: " + email);
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
        return true;
    }



}
