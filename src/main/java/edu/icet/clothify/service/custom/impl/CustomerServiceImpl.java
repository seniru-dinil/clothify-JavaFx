package edu.icet.clothify.service.custom.impl;


import edu.icet.clothify.dto.Customer;
import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.repository.DaoFactory;
import edu.icet.clothify.repository.custom.CustomerDao;
import edu.icet.clothify.service.custom.CustomerService;
import edu.icet.clothify.util.enums.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public List<Customer> getAllCustomer() {
        CustomerDao dao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
        List<CustomerEntity> allCustomer = dao.getAll();
        List<Customer> customers = new ArrayList<>();
        allCustomer.forEach(c -> customers.add(new ModelMapper().map(c, Customer.class)));
        return customers;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        CustomerEntity map = new ModelMapper().map(customer, CustomerEntity.class);
        CustomerDao dao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
        return dao.save(map);
    }

    @Override
    public boolean deleteCustomer(Integer id) {
        CustomerDao dao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
        boolean delete = dao.delete(id);
        return delete;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        CustomerDao dao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
        return dao.update(new ModelMapper().map(customer, CustomerEntity.class));
    }

    @Override
    public Customer getCustomer(String id) {
        CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
        CustomerEntity customerEntity = customerDao.get(id);
        return customerEntity==null?null:new ModelMapper().map(customerEntity, Customer.class);
    }

    @Override
    public List<Customer> getCustomersByName(String name) {
        CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
        List<CustomerEntity> customersByName = customerDao.getCustomersByName(name);
        List<Customer> customers = new ArrayList<>();
        customersByName.forEach(c -> customers.add(new ModelMapper().map(c, Customer.class)));
        return customers;
    }

    @Override
    public Map<CustomerEntity, Double> getBestCustomers() {
        CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
        return customerDao.getBestCustomers();
    }
}
