package edu.icet.clothify.repository.custom.impl;


import edu.icet.clothify.entity.AdminEntity;
import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.entity.EmployeeEntity;
import edu.icet.clothify.repository.custom.EmployeeDao;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity employeeEntity)  {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(employeeEntity);
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
                EmployeeEntity employee = session.get(EmployeeEntity.class, id);
                if (employee != null) {
                    session.remove(employee);
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
    public EmployeeEntity get(String email) {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM EmployeeEntity WHERE email=:email", EmployeeEntity.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean update(EmployeeEntity employeeEntity) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            try{
                EmployeeEntity user = session.get(EmployeeEntity.class, employeeEntity.getEmployeeId());
                if (user == null)return false;
                user.setEmail(employeeEntity.getEmail());
                user.setPassword(employeeEntity.getPassword());
                user.setEmployeeFirstName(employeeEntity.getEmployeeFirstName());
                user.setEmployeeLastName(employeeEntity.getEmployeeLastName());
                session.merge(user);
                session.getTransaction().commit();
                return true;
            }catch (Exception e){
                transaction.rollback();
                return false;
            }
        }
    }

    @Override
    public List<EmployeeEntity> getAll() {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        Query<EmployeeEntity> query = session.createQuery("FROM EmployeeEntity", EmployeeEntity.class);
        List<EmployeeEntity> employeeEntities = query.list();
        tx.commit();
        return employeeEntities;
    }

    @Override
    public List<EmployeeEntity> getEmployeesByName(String name) {
        try(Session session = HibernateUtil.getSession()){
            Transaction transaction = session.beginTransaction();
            try{
                String hql = "FROM EmployeeEntity e WHERE LOWER(e.employeeFirstName) LIKE LOWER(CONCAT('%', :name, '%'))";
                Query<EmployeeEntity> query = session.createQuery(hql, EmployeeEntity.class);
                query.setParameter("name", name);
                return query.list();
            }catch (Exception e){
                return null;
            }
        }
    }
}
