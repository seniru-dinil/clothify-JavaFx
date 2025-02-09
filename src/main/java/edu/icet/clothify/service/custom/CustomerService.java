package edu.icet.clothify.service.custom;

import edu.icet.clothify.dto.Customer;
import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.service.SupperService;

import java.util.List;
import java.util.Map;

public interface CustomerService extends SupperService {
    List<Customer> getAllCustomer();
    boolean addCustomer(Customer customer);
    boolean deleteCustomer(Integer id);
    boolean updateCustomer(Customer customer);
    Customer getCustomer(String id);
     List<Customer> getCustomersByName(String name);
     Map<CustomerEntity,Double> getBestCustomers();
}
