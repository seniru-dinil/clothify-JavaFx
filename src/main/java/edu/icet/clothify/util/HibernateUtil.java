package edu.icet.clothify.util;

import edu.icet.clothify.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = createSessionFactory();

    private static SessionFactory createSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(EmployeeEntity.class)
                .addAnnotatedClass(SupplierEntity.class)
                .addAnnotatedClass(CustomerEntity.class)
                .addAnnotatedClass(ProductEntity.class)
                .addAnnotatedClass(OrderEntity.class)
                .addAnnotatedClass(OrderDetailEntity.class)
                .addAnnotatedClass(AdminEntity.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        return metadata.getSessionFactoryBuilder().build();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
