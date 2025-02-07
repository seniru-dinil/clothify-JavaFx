package edu.icet.clothify.repository;

import edu.icet.clothify.repository.custom.OrderDao;
import edu.icet.clothify.repository.custom.impl.*;
import edu.icet.clothify.util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    public static DaoFactory getInstance() {
        return instance==null?instance=new DaoFactory() : instance;
    }

    public <T extends SuperDao>T getDao(DaoType type){
        return switch (type) {
            case CUSTOMER -> (T) new CustomerDaoImpl();
            case PRODUCT -> (T) new ProductDaoImpl();
            case EMPLOYEE -> (T) new EmployeeDaoImpl();
            case SUPPLIER -> (T) new SupplierDaoImpl();
            case ADMIN -> (T) new AdminDaoImpl();
            case ORDER -> (T) new OrderDaoImpl();
            case ORDER_DETAIL -> (T) new OrderDetailDaoImpl();
        };
    }
}
