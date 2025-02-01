package edu.icet.clothify.util;

import edu.icet.clothify.component.tableCard.EmployeeTableCard;
import edu.icet.clothify.dto.Employee;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.EmployeeService;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.List;

public class EmployeeUtil {
    @FXML
    public VBox employeeContainer;
    private static EmployeeUtil instance;

    private EmployeeUtil() {
    }

    public static EmployeeUtil getInstance() {
        return instance == null ? instance = new EmployeeUtil() : instance;
    }

    public void initializeContainer(VBox employeeContainer){
        this.employeeContainer = employeeContainer;
    }

    public void loadContainer() throws SQLException {
        loadEmployees();
    }

    private void loadEmployees() throws SQLException {
        EmployeeService employeeService = ServiceFactory.getInstance().getService(ServiceType.EMPLOYEE);
        List<Employee> allEmployees = employeeService.getAllEmployees();
        populateEmployeeCards(allEmployees);
    }

    private void populateEmployeeCards(List<Employee> employees) {
        employeeContainer.getChildren().clear();
        for (Employee employee : employees) {
            AnchorPane anchorPane = EmployeeTableCard.getInstance().createAnchorPane(employee);
            employeeContainer.getChildren().add(anchorPane);
        }
    }
}