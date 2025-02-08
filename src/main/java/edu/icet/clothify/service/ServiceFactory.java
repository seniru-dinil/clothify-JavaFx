package edu.icet.clothify.service;

import edu.icet.clothify.service.custom.impl.*;
import edu.icet.clothify.util.enums.ServiceType;

public class ServiceFactory {
    private static  ServiceFactory instance;
    private ServiceFactory(){}
    public static ServiceFactory getInstance(){
        return instance==null?instance=new ServiceFactory() :instance;
    }

    public <T extends SupperService>T getService(ServiceType type){
        return switch (type) {
            case CUSTOMER -> (T) new CustomerServiceImpl();
            case PRODUCT -> (T) new ProductServiceImpl();
            case EMPLOYEE -> (T) new EmployeeServiceImpl();
            case SUPPLIER -> (T) new SupplierServiceImpl();
            case ADMIN -> (T) new AdminServiceImpl();
            case ORDER ->(T) new OrderServiceImpl();
            case ORDER_DETAIL -> (T) new OrderDetailServiceImpl();
        };
    }
}
