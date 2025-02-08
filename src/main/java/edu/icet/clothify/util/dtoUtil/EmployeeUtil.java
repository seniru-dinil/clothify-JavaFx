package edu.icet.clothify.util.dtoUtil;

import edu.icet.clothify.component.tableCard.EmployeeTableCard;
import edu.icet.clothify.dto.Employee;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.custom.EmployeeService;
import edu.icet.clothify.util.enums.ServiceType;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;
import java.util.List;

public class EmployeeUtil {
    @FXML
    public VBox employeeContainer;
    private static EmployeeUtil instance;

    @Setter
    @Getter
    private Employee employeeInstance;

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

    private void loadEmployees() {
        EmployeeService employeeService = ServiceFactory.getInstance().getService(ServiceType.EMPLOYEE);
        List<Employee> allEmployees = employeeService.getAllEmployees();
        populateEmployeeCards(allEmployees);
    }

    public void populateEmployeeCards(List<Employee> employees) {
        employeeContainer.getChildren().clear();
        for (Employee employee : employees) {
            AnchorPane anchorPane = EmployeeTableCard.getInstance().createAnchorPane(employee);
            employeeContainer.getChildren().add(anchorPane);
        }
    }
}