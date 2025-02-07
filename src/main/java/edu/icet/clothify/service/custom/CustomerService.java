package edu.icet.clothify.service.custom;

import edu.icet.clothify.dto.Customer;
import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.service.SupperService;
import java.sql.SQLException;
import java.util.List;

public interface CustomerService extends SupperService {
    List<CustomerEntity> getAllCustomer();
    boolean addCustomer(Customer customer);
    boolean deleteCustomer(Integer id);
    boolean updateCustomer(Customer customer);
    CustomerEntity getCustomer(String id);
}
