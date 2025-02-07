package edu.icet.clothify.repository.custom;

import edu.icet.clothify.entity.EmployeeEntity;
import edu.icet.clothify.repository.CrudDao;

import java.util.List;

public interface EmployeeDao extends CrudDao<EmployeeEntity> {
    List<EmployeeEntity> getEmployeesByName(String name);
}
