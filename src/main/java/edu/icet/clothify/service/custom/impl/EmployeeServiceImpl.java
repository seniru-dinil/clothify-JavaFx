package edu.icet.clothify.service.custom.impl;

import edu.icet.clothify.dto.Employee;
import edu.icet.clothify.entity.EmployeeEntity;
import edu.icet.clothify.repository.DaoFactory;
import edu.icet.clothify.repository.custom.EmployeeDao;
import edu.icet.clothify.service.custom.EmployeeService;
import edu.icet.clothify.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public List<Employee> getAllEmployees(){
        EmployeeDao dao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
        List<EmployeeEntity> all = dao.getAll();
        List<Employee> employees = new ArrayList<>();
        all.forEach(emp->employees.add(new ModelMapper().map(emp,Employee.class)));
        return employees;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        EmployeeDao dao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
        return dao.save(new ModelMapper().map(employee,EmployeeEntity.class));
    }

    @Override
    public boolean deleteEmployee(Integer id) {
        EmployeeDao dao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
        return dao.delete(id);
    }

    @Override
    public Employee getEmployee(String id) {
        EmployeeDao dao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
        EmployeeEntity employeeEntity = dao.get(id);
        return employeeEntity==null?null:new ModelMapper().map(employeeEntity,Employee.class);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        EmployeeDao dao = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
        return dao.update(new ModelMapper().map(employee, EmployeeEntity.class));
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        EmployeeDao employeeDao= DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
        List<EmployeeEntity> employeesByName = employeeDao.getEmployeesByName(name);
        List<Employee> employees = new ArrayList<>();
        employeesByName.forEach(e->employees.add(new ModelMapper().map(e,Employee.class)));
        return employees;
    }
}
