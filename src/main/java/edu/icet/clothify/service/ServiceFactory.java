package edu.icet.clothify.service;

import edu.icet.clothify.service.custom.impl.CustomerServiceImpl;
import edu.icet.clothify.service.custom.impl.EmployeeServiceImpl;
import edu.icet.clothify.service.custom.impl.ProductServiceImpl;
import edu.icet.clothify.service.custom.impl.SupplierServiceImpl;
import edu.icet.clothify.util.ServiceType;

public class ServiceFactory {
    private static  ServiceFactory instance;
    private ServiceFactory(){}
    public static ServiceFactory getInstance(){
        return instance==null?instance=new ServiceFactory() :instance;
    }

    public SupperService getService(ServiceType type){
            switch(type){
                case CUSTOMER :return new CustomerServiceImpl();
                case PRODUCT: return new ProductServiceImpl();
                case EMPLOYEE:return new EmployeeServiceImpl();
                case SUPPLIER:return new SupplierServiceImpl();
            }
            return null;
    }
}
