package edu.icet.clothify.repository.custom;


import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.repository.CrudDao;

import java.util.List;

public interface CustomerDao extends CrudDao<CustomerEntity> {
     List<CustomerEntity> getCustomersByName(String name);
}
