package edu.icet.clothify.service.custom.impl;


import edu.icet.clothify.dto.Customer;
import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.repository.DaoFactory;
import edu.icet.clothify.repository.custom.CustomerDao;
import edu.icet.clothify.service.custom.CustomerService;
import edu.icet.clothify.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public List<CustomerEntity> getAllCustomer() {
        CustomerDao dao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
        return dao.getAll();
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
    public CustomerEntity getCustomer(String id) {
        CustomerDao customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
        return customerDao.get(id);
    }
}
