package edu.icet.clothify.service.custom;

import edu.icet.clothify.dto.Employee;
import edu.icet.clothify.service.SupperService;

import java.util.List;

public interface EmployeeService extends SupperService {
    List<Employee> getAllEmployees();
    boolean addEmployee(Employee employee);
    boolean deleteEmployee(Integer id);
    Employee getEmployee(String id);
    boolean updateEmployee(Employee id);
    List<Employee> getEmployeesByName(String name);
}
