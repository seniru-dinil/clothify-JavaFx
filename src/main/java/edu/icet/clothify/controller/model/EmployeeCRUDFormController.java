package edu.icet.clothify.controller.model;


import edu.icet.clothify.component.tableCard.EmployeeTableCard;
import edu.icet.clothify.dto.Employee;
import edu.icet.clothify.service.ServiceFactory;
import edu.icet.clothify.service.SupperService;
import edu.icet.clothify.service.custom.EmployeeService;
import edu.icet.clothify.service.custom.impl.EmployeeServiceImpl;
import edu.icet.clothify.util.ServiceType;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeCRUDFormController implements Initializable {
    public VBox employeeContainer;
    ArrayList<Employee> employees;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadEmployees();

    }


    private void loadEmployees() throws SQLException {
        EmployeeService employeeService = (EmployeeServiceImpl) ServiceFactory.getInstance().getService(ServiceType.EMPLOYEE);
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

    public void btnAddEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Object load = FXMLLoader.load(getClass().getResource("/view/add.forms/addEmployeeForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene((Parent) load));
        stage.show();
    }
}
