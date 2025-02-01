package edu.icet.clothify.service.custom;

import edu.icet.clothify.dto.Employee;
import edu.icet.clothify.service.SupperService;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService extends SupperService {
    List<Employee> getAllEmployees() throws SQLException;
    boolean addEmployee(Employee employee);
    boolean deleteEmployee(Integer id);
    Employee getEmployee(String id);
    boolean updateEmployee(Employee id);
}
